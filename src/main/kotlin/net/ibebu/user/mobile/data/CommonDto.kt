package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.common.utils.JwtUtil.getUserUuid
import net.ibebu.user.core.base.BaseResponsePopup
import net.ibebu.user.core.base.BaseValidation
import net.ibebu.user.core.enums.YesOrNo
import java.util.*

object CommonDto {
    data class CdNewCountRequest(
        @Schema(title = "사용자 범용 식별자", example = "b71f64ef-3ec4-41b6-b5eb-e37a145dd817", required = true)
        val userUuid: UUID
    ) : BaseValidation<CdNewCountRequest, CdNewCountResponse> {
        companion object {
            fun of(): CdNewCountRequest {
                return CdNewCountRequest(
                    userUuid = getUserUuid()
                )
            }
        }
    }

    data class CdNewCountResponse(
        @Schema(title = "신규 알림 여부", example = "N", required = true)
        val newYn: YesOrNo,
    ) : BaseResponsePopup() {
        companion object {
            fun of(count: Int): CdNewCountResponse {
                return CdNewCountResponse(
                    newYn = YesOrNo.find(count != 0)
                )
            }
        }
    }
}