package com.frnz7.restSpring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful from 0 with java, Spring boot, Kubernets and docker")
                        .version("v1")
                        .description("Curso de REST API's RESTful do 0 con java, Spring boot, Kubernets e docker")
                );
    }

}
