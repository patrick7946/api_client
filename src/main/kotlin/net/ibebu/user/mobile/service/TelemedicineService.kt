package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.MedicalCategoriesService
import net.ibebu.user.mobile.data.TelemedicineDto
import org.springframework.stereotype.Service


@Service
class TelemedicineService(
    private val medicalCategoriesService: MedicalCategoriesService,
) {
    fun getPhysicalCategories(request: TelemedicineDto.TdPhysicalRequest): TelemedicineDto.TdPhysicalResponse {
        return request.validation {
            medicalCategoriesService.getMedicalCategories(request.categoryDepth, request.medicalCategoryTypeEnums).let {
                TelemedicineDto.TdPhysicalResponse.of(it)
            }
        }
    }

    fun getMedicalCategories(request: TelemedicineDto.TdMedicalRequest): TelemedicineDto.TdMedicalResponse {
        return request.validation {
            medicalCategoriesService.getMedicalCategories(request.categoryDepth, request.medicalCategoryTypeEnums).let {
                TelemedicineDto.TdMedicalResponse.of(it)
            }
        }
    }
}