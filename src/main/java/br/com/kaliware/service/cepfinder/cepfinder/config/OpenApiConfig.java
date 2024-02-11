package br.com.kaliware.service.cepfinder.cepfinder.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI dsmovieAPI() {
    return new OpenAPI()
       .info(new Info()
          .title("CEP Finder API")
          .description(
             "A API `CEP Finder API` é disponibilizada através de serviço `HTTP REST`." +
                " O objetivo da API CEP Finder é disponibilizar dados completo dos endereços a partir do CEP ou endereço especifico"
          )
          .version("0.0.1-SNAPSHOT")
          .contact(new Contact()
             .name("Noah Lourenço")
             .email("noah.damian.lourenco@gmail.com").url("https://github.com/Kaliware/")
          )
       );
  }
}
