package com.github.valentina810.diaryofemotions.config;

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
                .info(
                        new Info()
                                .title("Emotion diary System Api")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("valentinavasileva34@gmail.com")
                                                .url("https://github.com/Valentina810")
                                                .name("Valentina Kolesnikova")
                                )
                );
    }
}