package net.ibebu.user.core.token

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

// 참조 링크: https://github.com/SilverNine/spring-boot-jwt-tutorial-kotlin
class JwtSecurityConfig(private val tokenProvider: TokenProvider) :
    SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(
            JwtFilter(tokenProvider),
            UsernamePasswordAuthenticationFilter::class.java
        )
    }
}