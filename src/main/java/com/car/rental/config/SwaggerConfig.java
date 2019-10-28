package com.car.rental.config;

import com.car.rental.constants.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String DEFAULT_INCLUDE_PATTERN = AppConstants.PATH + "/.*";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.car.rental.restcontroller"))
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Car Rental REST API",
                "Challenge Samsung API.",
                "v1.0",
                "Terms of service",
                new Contact("Ezequias Monteiro", "", "esequias.m.oliveira@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }


}
