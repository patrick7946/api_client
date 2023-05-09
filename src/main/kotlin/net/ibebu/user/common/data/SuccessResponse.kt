package net.ibebu.user.common.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.core.base.BaseResponsePopup

class SuccessResponse<T>(
    val success: T?,
    val failure: BaseResponsePopup.PopupMessage? = null
) {
    @Schema(example = "2000", required = true)
    var responseCode: Int = 2000

    companion object {
        fun <T : BaseResponsePopup> of(response: T): SuccessResponse<T> {
            response.popData.isBoolean().let {
                return SuccessResponse(
                    if (!it) response.apply { this.popData = null } else null,
                    if (it) response.popData else null
                ).apply {
                    if (it) {
                        this.responseCode = 4000
                    }
                }
            }
        }

        private fun BaseResponsePopup.PopupMessage?.isBoolean(): Boolean {
            return this != null
        }
    }
}
