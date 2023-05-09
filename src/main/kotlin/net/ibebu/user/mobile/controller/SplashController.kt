package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.mobile.data.SplashDto
import net.ibebu.user.mobile.service.SplashService
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
        splashService.getSplashVersion(SplashDto.SdVersionRequest(deviceType)).let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }
}