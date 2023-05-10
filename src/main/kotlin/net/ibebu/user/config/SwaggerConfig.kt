package net.ibebu.user.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(
        @Value("\${springdoc.version}") springdocVersion: String?,
        @Value("\${springdoc.server_url}") serverUrl: String
    ): OpenAPI? {
        val info: Info = Info()
            .title("타이틀 입력")
            .version(springdocVersion)
            .description("API에 대한 설명 부분")


        val securitySchemeName = "Authorization"
        val securityScheme = SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
        val securityRequirement = SecurityRequirement().addList(securitySchemeName)

        return OpenAPI()
            .info(info)
            .components(Components().addSecuritySchemes(securitySchemeName, securityScheme))
            .addSecurityItem(securityRequirement)
            .addServersItem(Server().url(serverUrl))
    }
}