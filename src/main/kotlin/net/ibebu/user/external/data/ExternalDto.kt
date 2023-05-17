package net.ibebu.user.external.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.common.data.enums.IdTypeEnums
import net.ibebu.user.core.base.BaseResponsePopup
import net.ibebu.user.mobile.data.IdUploadDto
import java.util.*

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

    data class EdValidationRequest(
        @Schema(title = "대상 이메일 주소", example = "patrick@ibebu.net", required = true)
        val targetEmailAddress: String,
        @Schema(title = "이름", example = "Patrick", required = true)
        val firstName: String,
        @Schema(title = "성", example = "Park", required = true)
        val lastName: String,
        @Schema(title = "BASE 64 이미지 데이터", required = true)
        val data: String,
        @Schema(title = "File 이름", example = "front-test.jpg", required = true)
        val fileName: String,
        @Schema(title = "신분증 종류", example = "passport", required = true)
        val idType: IdTypeEnums,
    ) {
        companion object {
            fun of(
                request: IdUploadDto.IudUploadRequest,
                userDtd: UserDtd.UdIdentificationResponse
            ): EdValidationRequest {
                val (firstName, lastName) = splitName(userDtd.userName)
                return EdValidationRequest(
                    data = Base64.getEncoder().encodeToString(request.image.bytes),
                    fileName = request.image.originalFilename.toString(),
                    idType = request.idTypeCode,
                    targetEmailAddress = userDtd.userEmail,
                    firstName = firstName,
                    lastName = lastName
                )
            }

            private fun splitName(name: String): Pair<String, String> {
                val nameParts = name.split(" ")
                val lastName = nameParts.last()
                val firstName = nameParts.dropLast(1).joinToString(" ")
                return Pair(firstName, lastName)
            }
        }
    }

    data class EdValidationResponse(
        @field:Schema(title = "상세 정보")
        val breakdown: Breakdown?
    ) : BaseResponsePopup() {
        data class Date(
            @field:Schema(title = "일", example = "1")
            val day: Int,
            @field:Schema(title = "월", example = "1")
            val month: Int,
            @field:Schema(title = "년", example = "2015")
            val year: Int
        )

        data class Breakdown(
            @field:Schema(title = "추출된 데이터")
            val extractedData: ExtractedData
        )

        data class ExtractedData(
            @field:Schema(title = "문서 세부 정보")
            val documentDetails: DocumentDetails,
            @field:Schema(title = "소지자 세부 정보")
            val holderDetails: HolderDetails
        )

        data class DocumentDetails(
            @field:Schema(title = "문서 유형", example = "driving_license")
            val documentType: String,
            @field:Schema(title = "양면 여부", example = "true")
            val hasTwoSides: Boolean,
            @field:Schema(title = "발행 국가", example = "GB")
            val issuingCountry: String,
            @field:Schema(title = "발행일")
            val issuingDate: Date,
            @field:Schema(title = "만료일")
            val expirationDate: Date,
            @field:Schema(title = "문서 번호", example = "123456790")
            val documentNumber: String,
            @field:Schema(title = "개인 번호", example = "123456790")
            val personalNumber: String
        )

        data class HolderDetails(
            @field:Schema(title = "성")
            val lastName: List<String>,
            @field:Schema(title = "이름")
            val firstName: List<String>,
            @field:Schema(title = "생년월일")
            val dob: Date,
            @field:Schema(title = "주소")
            val address: Address
        )

        data class Address(
            @field:Schema(title = "주소 텍스트", example = "110 MAPLE ROAD, SAMPLE CITY, NC 10000-0008")
            val addressText: String,
            @field:Schema(title = "주소 라인", example = "110 MAPLE ROAD")
            val line: String,
            @field:Schema(title = "도시", example = "SAMPLE CITY")
            val city: String,
            @field:Schema(title = "주", example = "North Carolina")
            val state: String,
            @field:Schema(title = "우편번호", example = "10000-0008")
            val postalCode: String,
            @field:Schema(title = "국가", example = "US")
            val country: String
        )
    }
}