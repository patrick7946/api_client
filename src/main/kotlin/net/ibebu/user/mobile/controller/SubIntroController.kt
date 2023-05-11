package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.token.JwtFilter
import net.ibebu.user.mobile.data.SplashDto
import net.ibebu.user.mobile.data.SubIntroDto
import net.ibebu.user.mobile.service.SplashService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "구독 소개 화면", description = "구독 소개 화면에 대한 API 이다.")
class SubIntroController() {
    @Operation(summary = "화면 구성 정보 조회 API")
    @GetMapping("v1/sub-intro/layout")
    fun getV1SplashVersion(): ResponseEntity<SuccessResponse<SubIntroDto.SidLayoutResponse>> {
        SubIntroDto.SidLayoutResponse.init().let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }
}