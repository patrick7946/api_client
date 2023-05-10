package net.ibebu.user.core.base

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.common.data.enums.FailMessageEnums
import net.ibebu.user.common.data.enums.PopupTypeEnum

abstract class BaseResponsePopup(
    @JsonIgnore
    @Schema(title = "Popup Data", required = true)
    var popupData: PopupMessage? = null,
) {
    data class PopupMessage(
        @Schema(
            title = "Popup Type(ALERT: Message with confirmation window)",
            example = "ALERT",
            required = true
        )
        val popupType: PopupTypeEnum,
        @Schema(title = "Popup Title", example = "Authentication Number Request Failed", required = true)
        val popupTitle: String,
        @Schema(title = "Popup Content", example = "Authentication data error", required = true)
        var popupContent: String,
    ) {
        companion object {
            fun of(popupType: PopupTypeEnum, failMessageEnums: FailMessageEnums): PopupMessage {
                return PopupMessage(
                    popupType = popupType,
                    popupTitle = failMessageEnums.title,
                    popupContent = failMessageEnums.content
                )
            }
        }
    }
}
