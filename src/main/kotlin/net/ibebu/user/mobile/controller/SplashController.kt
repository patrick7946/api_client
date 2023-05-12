package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.token.JwtFilter
import net.ibebu.user.mobile.data.LoginDto
import net.ibebu.user.mobile.data.SplashDto
import net.ibebu.user.mobile.service.SplashService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "스플래시 화면", description = "스플래시 화면에 대한 API 이다.")
class SplashController(private val splashService: SplashService) {
    @Operation(summary = "APP의 최신 버전을 조회하는 API")
    @GetMapping("v1/splash/version")
    fun getV1SplashVersion(
        @RequestParam("deviceType") deviceType: DeviceTypeEnums
    ): ResponseEntity<SuccessResponse<SplashDto.SdVersionResponse>> {
        splashService.getSplashVersion(SplashDto.SdVersion(deviceType)).let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }

    @Operation(summary = "사용자의 로그인 정보 유효성 검증 API")
    @PostMapping("v1/splash/login")
    fun postV1SplashLogin(
        @RequestBody request: LoginDto.LdLogin
    ): ResponseEntity<SuccessResponse<LoginDto.LdLoginResponse>> {
        splashService.postSplashLogin(request).let { body ->
            HttpHeaders()
                .apply { this.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + body.loginToken) }
                .let { header -> return ResponseEntity.ok().headers(header).body(SuccessResponse.of(body)) }
        }
    }
}