package net.ibebu.user.back.data.dtd

import net.ibebu.user.back.data.dao.MedicalCategories
import net.ibebu.user.back.data.dao.MedicalCategoryImages
import net.ibebu.user.common.data.enums.MedicalCategoryTypeEnums
import net.ibebu.user.core.base.BaseDto
import net.ibebu.user.core.enums.YesOrNo
import java.util.*

object MedicalCategoriesDtd {
    data class McdCategoriesResponse(
        val categories: List<McrCategory>
    ) {
        data class McrCategory(
            val medicalCategoryUuid: UUID? = null,
            val categoryType: MedicalCategoryTypeEnums,
            val categoryFullName: String,
            val categoryName: String,
            val categoryImage: String?,
            val subCategories: List<McSubCategory>
        ) {
            data class McSubCategory(
                val medicalCategoryUuid: UUID? = null,
                val categoryFullName: String,
                val categoryName: String
            ) {
                companion object Of : BaseDto<MedicalCategories, McSubCategory> {
                    override fun of(entity: MedicalCategories): McSubCategory {
                        return McSubCategory(
                            medicalCategoryUuid = entity.medicalCategoryUuid!!,
                            categoryFullName = entity.categoryFullName,
                            categoryName = entity.categoryName
                        )
                    }
                }
            }

            companion object Of : BaseDto<MedicalCategories, McrCategory> {
                override fun of(entity: MedicalCategories): McrCategory {
                    return McrCategory(
                        medicalCategoryUuid = entity.medicalCategoryUuid!!,
                        categoryType = entity.categoryType,
                        categoryFullName = entity.categoryFullName,
                        categoryName = entity.categoryName,
                        categoryImage = getMainCategoryImageUrlData(entity.categoryType, entity.medicalCategoryImages),
                        subCategories = entity.childMedicalCategories.map { it.toDto(McSubCategory.Of) }
                    )
                }

                private fun getMainCategoryImageUrlData(
                    categoryType: MedicalCategoryTypeEnums,
                    entities: List<MedicalCategoryImages>
                ): String? {
                    return entities.firstOrNull { categoryType == MedicalCategoryTypeEnums.PHYSICAL_ORGAN && it.delYn == YesOrNo.N }?.imageUrl
                }
            }
        }

        companion object {
            fun of(entities: List<MedicalCategories>): McdCategoriesResponse {
                return McdCategoriesResponse(
                    categories = entities.map { it.toDto(McrCategory.Of) }
                )
            }
        }
    }
}