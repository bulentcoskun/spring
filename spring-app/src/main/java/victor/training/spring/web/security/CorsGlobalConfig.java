package victor.training.spring.web.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsGlobalConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                        .allowedMethods("*")
//                        .allowCredentials(true) // allows receiving session cookie (if using cookies)
//                        .allowedOriginPatterns("http://localhost:8081") // eg NodeJS
////					 .allowedOriginPatterns("http://*") // Too broad
//                ;
//                // also don't forget to add .cors() to spring security
//            }
//        };
//    }
}