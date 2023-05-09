package net.ibebu.user.core.base

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.common.data.enums.PopupTypeEnum

abstract class BaseResponsePopup(
    @JsonIgnore
    @Schema(title = "Popup Data", required = true)
    var popData: PopupMessage? = null,
) {
    data class PopupMessage(
        @Schema(
            title = "Popup Type(ALERT: Message with confirmation window)",
            example = "ALERT",
            required = true
        )
        val popType: PopupTypeEnum,
        @Schema(title = "Popup Title", example = "Authentication Number Request Failed", required = true)
        val popTit: String,
        @Schema(title = "Popup Content", example = "Authentication data error", required = true)
        var popCntn: String,
    )
}
