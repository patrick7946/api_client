package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.UserService
import net.ibebu.user.core.token.TokenProvider
import net.ibebu.user.external.client.ExternalClient
import net.ibebu.user.mobile.data.LoginDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class LoginService(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
    @Value("\${user.base}")
    private val baseHost: String,
    @Value("\${oauth2.google.redirect-path}")
    private val redirectPath: String,
    private val externalClient: ExternalClient
) {
    fun postLoginLogin(request: LoginDto.LdLogin): LoginDto.LdLoginResponse {
        return request.validation {
            userService.getUserByEmail(it.email).let { response ->
                LoginDto.LdLoginResponse.validation(
                    passwordEncoder,
                    it.password,
                    response,
                    tokenProvider
                )
            }
        }
    }

    fun validationOauth2GoogleLogin(code: String): String {
        externalClient.getExternalOauth2Google(
            authorizationCode = code,
            redirectUrl = "$baseHost/$redirectPath"
        ).body!!.let {
            return it.success!!.toTestString()
        }
    }
}