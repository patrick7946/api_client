package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.mobile.data.TelemedicineDto
import net.ibebu.user.mobile.service.TelemedicineService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "비대면 진료 화면", description = "비대면 진료 화면에 대한 API 이다.")
class TelemedicineController(private val telemedicineService: TelemedicineService) {
    @Operation(summary = "신체 기관 조회 API")
    @GetMapping("v1/telemedicine/categories/physical")
    fun getV1TelemedicineCategoriesPhysical(): ResponseEntity<SuccessResponse<TelemedicineDto.TdPhysicalResponse>> {
        telemedicineService.getPhysicalCategories(TelemedicineDto.TdPhysicalRequest.of()).let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }

    @Operation(summary = "진료과 조회 API")
    @GetMapping("v1/telemedicine/categories/medical")
    fun getV1TelemedicineCategoriesMedical(): ResponseEntity<SuccessResponse<TelemedicineDto.TdMedicalResponse>> {
        telemedicineService.getMedicalCategories(TelemedicineDto.TdMedicalRequest.of()).let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }
}