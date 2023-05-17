package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.common.data.enums.IdTypeEnums
import net.ibebu.user.core.base.BaseResponsePopup

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
}