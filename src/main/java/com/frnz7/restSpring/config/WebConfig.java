package com.frnz7.restSpring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer  {
    @Value("${cors.originPatterns}")
    private String corsOriginPatters = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowerdOrigins = corsOriginPatters.split(",");
        registry.addMapping("/**")
                .allowedOriginPatterns(allowerdOrigins)
                .allowedMethods("*")//da para filtrar, so passar aqui dentro. "GET, PUT, DELETE"...
                .allowCredentials(true);

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {





        //via extension .xml,.json, are deprecated on spring 2.6
       /* configurer.favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
        */

        //header parameter
            configurer.favorParameter(false)
                    .ignoreAcceptHeader(false)
                    .useRegisteredExtensionsOnly(false)
                    .defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML)
                    .mediaType("yaml", MediaType.APPLICATION_YAML);

    }


}
