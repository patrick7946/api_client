package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.token.JwtFilter
import net.ibebu.user.mobile.data.IdUploadDto
import net.ibebu.user.mobile.data.SplashDto
import net.ibebu.user.mobile.data.SubIntroDto
import net.ibebu.user.mobile.service.SplashService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "신분증 업로드 화면", description = "신분증 업로드 화면에 대한 API 이다.")
class IdentificationUploadController() {
    @Operation(summary = "화면 구성 정보 조회 API")
    @GetMapping("v1/id-upload/layout")
    fun getV1IdUploadLayout(): ResponseEntity<SuccessResponse<IdUploadDto.IudLayoutResponse>> {
        IdUploadDto.IudLayoutResponse.initOf().let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }
}