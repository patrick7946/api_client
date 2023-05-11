package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.token.JwtFilter
import net.ibebu.user.mobile.data.LoginDto
import net.ibebu.user.mobile.data.SplashDto
import net.ibebu.user.mobile.service.LoginService
import net.ibebu.user.mobile.service.SplashService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "로그인 화면", description = "로그인 화면에 대한 API 이다.")
class LoginController(private val loginService: LoginService) {
    @Operation(summary = "사용자의 로그인 정보 유효성 검증 API")
    @PostMapping("v1/login/login")
    fun getV1SplashLogin(
        @RequestBody request: LoginDto.LdLogin
    ): ResponseEntity<SuccessResponse<LoginDto.LdLoginResponse>> {
        loginService.postLoginLogin(request).let { body ->
            HttpHeaders()
                .apply { this.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + body.loginToken) }
                .let { header -> return ResponseEntity.ok().headers(header).body(SuccessResponse.of(body)) }
        }
    }
}