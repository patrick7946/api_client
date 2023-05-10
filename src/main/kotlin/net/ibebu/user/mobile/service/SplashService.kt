package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.DeployService
import net.ibebu.user.back.service.UserService
import net.ibebu.user.core.token.TokenProvider
import net.ibebu.user.mobile.data.SplashDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SplashService(
    private val deployService: DeployService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val tokenProvider: TokenProvider
) {
    fun getSplashVersion(request: SplashDto.SdVersion): SplashDto.SdVersionResponse {
        return request.validation {
            deployService.getDeployVersion(it.deviceType).let { response ->
                SplashDto.SdVersionResponse.of(response)
            }
        }
    }

    fun postSplashLogin(request: SplashDto.SdLogin): SplashDto.SdLoginResponse {
        return request.validation {
            userService.getUserByEmail(it.email).let { response ->
                SplashDto.SdLoginResponse.validation(
                    passwordEncoder,
                    it.password,
                    response,
                    authenticationManagerBuilder,
                    tokenProvider
                )
            }
        }
    }
}