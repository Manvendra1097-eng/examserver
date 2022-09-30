package com.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(getApiInfo()).
                 securityContexts(getSecurityContext())
                         .securitySchemes(Arrays.asList(getSecuritySchemes())).
                select().
                apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiKey getSecuritySchemes() {
        return new ApiKey("JWT","Authorization","header");
    }

    private List<SecurityContext> getSecurityContext() {
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReference()).build());
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope scope = new AuthorizationScope("global","accessEverthing");
        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{scope}));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Exam Portal Backend", "Api for exam portal", "1.0",
                "Term of Service", new Contact("Manvendra", "www.examportal.com",
                "manvendra1097@gmail.com"),
                "License of Exam portal", "Url for License", Collections.emptyList());
    }
}
