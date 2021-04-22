package victor.training.spring.web.performance;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Configuration
@EnableAsync
public class MonitoredThreadPoolConfig {
   private final MeterRegistry meterRegistry;

   @Bean(initMethod = "initialize")
   @ConfigurationProperties(prefix = "custom-executor")
   public ThreadPoolTaskExecutor customExecutor() {
      Timer timer = meterRegistry.timer("custom-executor-queue-wait");
      // configured via custom-executor.* in application.properties
      ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
      executor.setTaskDecorator(runnable -> {
         long t0 = System.currentTimeMillis(); // runs at submit() time
         return () -> {
            long t1 = System.currentTimeMillis(); // runs when task start being executed
            timer.record(t1 - t0, TimeUnit.MILLISECONDS);
            runnable.run();
         };
      });
      return executor;
   }

   @Bean
   public TimedAspect timedAspect() {
      return new TimedAspect(meterRegistry);
   }
}
