package net.ibebu.user.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(@Value("\${springdoc.version}") springdocVersion: String?): OpenAPI? {
        val info: Info = Info()
            .title("타이틀 입력")
            .version(springdocVersion)
            .description("API에 대한 설명 부분")

        val localServer = Server().url("http://localhost:3002").description("for local usages")
        val devServer = Server().url("https://dev.ibebu.net:3002").description("for testing")

        return OpenAPI()
            .components(Components())
            .info(info)
            .addServersItem(localServer)
            .addServersItem(devServer)
    }
}