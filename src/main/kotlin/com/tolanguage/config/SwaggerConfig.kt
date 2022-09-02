package com.tolanguage.config

import com.tolanguage.config.property.ApplicationProperty
import com.tolanguage.model.enums.Auth
import com.tolanguage.model.enums.Auth.BEARER
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
    private val property: ApplicationProperty,
) {

    @Bean
    fun openApi(): OpenAPI = OpenAPI()
        .components(
            Components()
                .addSecuritySchemes(
                    BEARER.value.trim(),
                    SecurityScheme().type(SecurityScheme.Type.HTTP)
                        .scheme(BEARER.value.trim())
                        .bearerFormat(Auth.JWT.value.trim())
                )
        )
        .info(
            Info()
                .title("ToLanguage Application")
                .description("Helper application for newbies in foreign language")
                .contact(Contact().name("krivtsov.eugene@gmail.com"))
                .version("0.0.1")
        )
        .run {
            if (property.url.isNotBlank()) {
                this.servers(listOf(Server().url(property.url + property.apiPath)))
            }
            this
        }

    @Bean
    fun publicApi(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("API")
        .pathsToMatch("/v1/**")
        .build()
}