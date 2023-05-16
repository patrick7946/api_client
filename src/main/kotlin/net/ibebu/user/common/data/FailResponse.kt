package net.ibebu.user.common.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.common.data.enums.PopupTypeEnum
import net.ibebu.user.core.base.BaseResponsePopup

class FailResponse(
    val success: String?,
    val failure: BaseResponsePopup.PopupMessage
) {
    @Schema(example = "4000", required = true)
    var responseCode: Int = 4000

    companion object {
        fun of(popup: BaseResponsePopup.PopupMessage): FailResponse {
            return FailResponse(
                success = null,
                failure = popup
            )
        }

        fun of(ex: RuntimeException?): FailResponse {
            return FailResponse(
                success = null,
                failure = BaseResponsePopup.PopupMessage(
                    PopupTypeEnum.NONE,
                    popupTitle = if (ex != null) ex::class.simpleName ?: "error" else "error",
                    popupContent = ex?.localizedMessage ?: "error"
                )
            )
        }

        fun of(ex: Exception?): FailResponse {
            return FailResponse(
                success = null,
                failure = BaseResponsePopup.PopupMessage(
                    PopupTypeEnum.NONE,
                    popupTitle = if (ex != null) ex::class.simpleName ?: "error" else "error",
                    popupContent = ex?.localizedMessage ?: "error"
                )
            )
        }
    }
}
