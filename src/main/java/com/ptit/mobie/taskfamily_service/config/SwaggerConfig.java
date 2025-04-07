package com.ptit.mobie.taskfamily_service.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Family API")
                        .description("REST API cho quản lý nhiệm vụ gia đình")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Mobile service app")
                                .email("hieuhoangmadrid@gmail.com")
                        )
                );
    }
}
