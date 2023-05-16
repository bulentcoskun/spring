package victor.training.spring.first;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// For immutable version, replace below @Data and @Component with
// @Value @ConstructorBinding
//  and @ConfigurationPropertiesScan on a @Configuration
// https://stackoverflow.com/questions/26137932/immutable-configurationproperties

@Slf4j
@Data // generates getters + setters
@Component
public class WelcomeInfo {
  int gate;
  String welcomeMessage; // TODO 4a validate is not null and size >= 4
  List<URL> supportUrls; // TODO 4b validate list contains at least 1 element
  Map<Locale, String> localContactPhone;
  HelpInfo help;

  @Data
  public static class HelpInfo {
    Integer appId;
    File file; // TODO 4c validate exists on disk
  }

  @PostConstruct
  public void printMyselfAtStartup() throws JsonProcessingException {
    String jsonToString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    log.info("WelcomeInfo:\n" + jsonToString);
  }
}
// TODO 1 inject welcome.welcomeMessage property in 'welcomeMessage' field
//   Hint: @Value("${
//   Question: what happens if the property is NOT defined
//     TODO 1* provide a default value by @Value("${...:defaultvalue}")
// TODO 2 inject welcome.gate in the 'gate' field'
//   Question: what happens if the value of the property is not an INT ?
// TODO 3 inject all properties at once using @ConfigurationProperties(prefix=...)
//   Ref: https://www.google.com/search?q=configuration+properties+baledung
// TODO 4 validate (see above)
//   Trick: provide a default to a property by assigning the field = "defaultvalue"
// TODO 5[PRO] make this class immutable: lombok.@Value + @ConstructorBinding + @ConfigurationPropertiesScan on a @Configuration
//  Hint: https://stackoverflow.com/questions/26137932/immutable-configurationproperties
