package net.ibebu.user.external.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.core.base.BaseResponsePopup

object ExternalDto {
    data class EdOauth2GoogleResponse(
        @Schema(title = "id", required = true)
        val id: String,
        @Schema(title = "google email", example = "patrick@ibebu.net", required = true)
        val email: String,
        @Schema(title = "name", example = "Patrick Park", required = true)
        val name: String
    ) : BaseResponsePopup() {
        fun toTestString(): String {
            return "id : $id, email : $email, name : $name"
        }

    }
}