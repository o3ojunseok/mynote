package com.note.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("My note Application API")
                        .description("The best way to manage my goals.")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact().name("villainscode").url("").email("o3ojunseok@gmail.com"))
                        .license(new License().name("MIT"))
                        .version("1.0")
                );
    }
}