package com.ednaldo.rest_api_spring_boot_and_java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Usuários")
                        .version("v1")
                        .description("Esta API permite gerenciar usuários, incluindo criação, leitura, atualização e exclusão.")
                        .termsOfService("")
                        .license(new License().name("MIT").url("http://opensource.org/licenses/MIT")));
    }
}
