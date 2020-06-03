package com.batista.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // informa q é uma classe de configuração
@EnableSwagger2 // habilita o swagger
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
             // indica a uri da sua api, se tivesse mais de um controller ele pegaria todos os que começassem c/ "api"
                .paths(PathSelectors.ant("/api/**")) 
                .build()
                .apiInfo(apiInfo()); // chama o método abaixo, onde vc informa os dados da sua api
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("Everton B. da Silva", "url do site", "email")) // Esse contact é uma classe prórpia do swagger
                .title("Carros")
                .description("Documentação API dos Carros")
                .license("Apache Licence Version 2.0")
                .licenseUrl("https://apache.org")
                .version("1.0")
                .build();

    }
}