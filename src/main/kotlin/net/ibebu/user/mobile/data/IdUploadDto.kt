package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.common.data.enums.FailMessageEnums
import net.ibebu.user.common.data.enums.IdTypeEnums
import net.ibebu.user.common.data.enums.PopupTypeEnum
import net.ibebu.user.common.utils.JwtUtil.getUserUuid
import net.ibebu.user.core.base.BaseResponsePopup
import net.ibebu.user.core.base.BaseValidation
import net.ibebu.user.external.data.ExternalDto
import org.springframework.web.multipart.MultipartFile
import java.util.*

object IdUploadDto {
    data class IudLayoutResponse(
        @Schema(title = "ID TYPE 리스트", required = true)
        val idTypeList: List<IlrIdType>
    ) : BaseResponsePopup() {
        companion object {
            fun initOf(): IudLayoutResponse {
                return IudLayoutResponse(
                    IdTypeEnums.values().map {
                        IlrIdType.of(it)
                    }
                )
            }
        }

        data class IlrIdType(
            @Schema(title = "ID TYPE CODE", example = "Driving License", required = true)
            val idTypeCode: IdTypeEnums,
            @Schema(title = "ID TYPE NAME", example = "운전면허증", required = true)
            val idTypeName: String
        ) {
            companion object {
                fun of(enums: IdTypeEnums): IlrIdType {
                    return IlrIdType(
                        idTypeCode = enums,
                        idTypeName = enums.idName
                    )
                }
            }
        }
    }

    data class IudUploadRequest(
        val userUuid: UUID,
        val idTypeCode: IdTypeEnums,
        val image: MultipartFile
    ) : BaseValidation<IudUploadRequest, IudUploadResponse> {
        companion object {
            fun of(idTypeCode: IdTypeEnums, image: MultipartFile): IudUploadRequest {
                return IudUploadRequest(
                    userUuid = getUserUuid(),
                    idTypeCode = idTypeCode,
                    image = image
                )
            }
        }
    }


    data class IudUploadResponse(
        @Schema(title = "메시지", example = "신분증 검증이 완료되었습니다.", required = true)
        val message: String
    ) : BaseResponsePopup() {
        companion object {
            fun of(response: ExternalDto.EdValidationResponse): IudUploadResponse {
                return IudUploadResponse(
                    message = if (response.breakdown != null) "신분증 검증이 완료되었습니다." else "신분증 검증에 실패하였습니다."
                ).apply {
                    if (response.breakdown == null) {
                        this.popupData = PopupMessage.of(
                            PopupTypeEnum.ALERT,
                            failMessageEnums = FailMessageEnums.INVALID_IDENTIFICATION
                        )
                    }
                }
            }
        }
    }
}