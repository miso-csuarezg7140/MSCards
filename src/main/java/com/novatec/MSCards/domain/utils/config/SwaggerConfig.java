package com.novatec.MSCards.domain.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API del microservicio de Tarjetas")
                        .version("1.0")
                        .description("API del microservicio de tarjetas de BankInc")
                        .contact(new Contact()
                                .name("Bank Inc")
                                .url("https://www.bankinc.com/")
                                .email("crhistian.suarez@bankinc.com")));
    }
}