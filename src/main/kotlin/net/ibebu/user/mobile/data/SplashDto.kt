package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.back.data.dtd.DeployDtd
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.base.BaseRequestValidation
import net.ibebu.user.core.base.BaseResponsePopup

object SplashDto {
    data class SdVersionRequest(
        @Schema(title = "디바이스 타입", example = "A", required = true)
        val deviceType: DeviceTypeEnums
    ) : BaseRequestValidation<SdVersionRequest, SdVersionResponse>

    data class SdVersionResponse(
        @Schema(title = "최신 버전", example = "1.0.0", required = true)
        val version: String,
        @Schema(title = "버전 업데이트 제목", example = "업데이트 제목", required = true)
        val title: String,
        @Schema(title = "버전 업데이트 내용", example = "업데이트 내용", required = true)
        val content: String
    ) : BaseResponsePopup() {
        companion object {
            fun of(response: DeployDtd.DdVersionResponse): SdVersionResponse {
                return SdVersionResponse(
                    version = response.version,
                    title = response.title,
                    content = response.content
                )
            }
        }
    }
}