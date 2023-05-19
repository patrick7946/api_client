package net.ibebu.user.back.service

import net.ibebu.user.back.data.dtd.MedicalCategoriesDtd
import net.ibebu.user.back.repository.MedicalCategoriesRepository
import net.ibebu.user.common.data.enums.MedicalCategoryTypeEnums
import org.springframework.stereotype.Service

@Service
class MedicalCategoriesService(private val medicalCategoriesRepository: MedicalCategoriesRepository) {
    fun getMedicalCategories(
        categoryDepth: Short,
        categoryType: MedicalCategoryTypeEnums
    ): MedicalCategoriesDtd.McdCategoriesResponse {
        return medicalCategoriesRepository.findAllByCategoryDepthAndCategoryTypeAndDelYn(
            categoryDepth = categoryDepth,
            categoryType = categoryType
        ).let {
            MedicalCategoriesDtd.McdCategoriesResponse.of(it)
        }
    }
}