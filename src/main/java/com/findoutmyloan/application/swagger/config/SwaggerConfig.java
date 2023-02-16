package com.findoutmyloan.application.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */
@Configuration
public class SwaggerConfig {
    @Value("${application.TITLE}")
    private String APPLICATION_TITLE;
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle=String.format("%s API", StringUtils.capitalize(APPLICATION_TITLE));
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                )
                )
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title(apiTitle)
                        .description("This is a Find Out My Loan Application RESTful service using springdoc-openapi and OpenAPI 3.")
                        .version("v1"));
    }
}
