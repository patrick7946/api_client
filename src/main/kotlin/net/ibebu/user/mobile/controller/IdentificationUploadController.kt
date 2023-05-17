package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.common.data.enums.IdTypeEnums
import net.ibebu.user.mobile.data.IdUploadDto
import net.ibebu.user.mobile.service.IdentificationUploadService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@Tag(name = "신분증 업로드 화면", description = "신분증 업로드 화면에 대한 API 이다.")
class IdentificationUploadController(private val identificationUploadService: IdentificationUploadService) {
    @Operation(summary = "화면 구성 정보 조회 API")
    @GetMapping("v1/id-upload/layout")
    fun getV1IdUploadLayout(): ResponseEntity<SuccessResponse<IdUploadDto.IudLayoutResponse>> {
        IdUploadDto.IudLayoutResponse.initOf().let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }

    @Operation(summary = "신분증 업로드")
    @PostMapping(
        "v1/id-upload/identification/upload",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun postV1IdUploadIdentificationUpload(
        @RequestParam("idTypeCode") idTypeCode: IdTypeEnums,
        @RequestPart("image") image: MultipartFile
    ): ResponseEntity<SuccessResponse<IdUploadDto.IudUploadResponse>> {
        identificationUploadService.postIdImageUpload(IdUploadDto.IudUploadRequest.of(idTypeCode, image)).let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }
}