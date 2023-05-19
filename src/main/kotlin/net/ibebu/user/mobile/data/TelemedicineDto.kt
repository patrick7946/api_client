package net.ibebu.user.mobile.data

import io.swagger.v3.oas.annotations.media.Schema
import net.ibebu.user.back.data.dao.MedicalCategoryImages
import net.ibebu.user.back.data.dtd.MedicalCategoriesDtd
import net.ibebu.user.common.data.enums.MedicalCategoryTypeEnums
import net.ibebu.user.core.base.BaseResponsePopup
import net.ibebu.user.core.base.BaseValidation
import java.util.*

object TelemedicineDto {
    data class TdPhysicalRequest(
        val medicalCategoryTypeEnums: MedicalCategoryTypeEnums,
        val categoryDepth: Short
    ) : BaseValidation<TdPhysicalRequest, TdPhysicalResponse> {
        companion object {
            fun of(): TdPhysicalRequest {
                return TdPhysicalRequest(
                    medicalCategoryTypeEnums = MedicalCategoryTypeEnums.PHYSICAL_ORGAN,
                    categoryDepth = 0
                )
            }
        }
    }

    data class TdPhysicalResponse(
        @Schema(title = "신체 기관 카테고리 리스트", required = true)
        val physicalCategories: List<TprCategory>,
    ) : BaseResponsePopup() {
        data class TprCategory(
            @Schema(title = "신체 기관 카테고리 UUID", required = true)
            val categoryUuid: UUID,
            @Schema(title = "신체 기관 카테고리 이름", required = true)
            val categoryName: String,
            @Schema(title = "신체 기관 카테고리 이미지", required = true)
            val categoryImage: String,
            @Schema(title = "신체 기관 하위 카테고리 리스트", required = true)
            val subCategories: List<TcSubCategory>
        ) {
            data class TcSubCategory(
                @Schema(title = "신체 기관 하위 카테고리 UUID", required = true)
                val subCategoryUuid: UUID,
                @Schema(title = "신체 기관 하위 카테고리 이름", required = true)
                val subCategoryName: String
            ) {
                companion object {
                    fun of(response: MedicalCategoriesDtd.McdCategoriesResponse.McrCategory.McSubCategory): TcSubCategory {
                        return TcSubCategory(
                            subCategoryUuid = response.medicalCategoryUuid!!,
                            subCategoryName = response.categoryName
                        )
                    }
                }
            }

            companion object {
                fun of(response: MedicalCategoriesDtd.McdCategoriesResponse.McrCategory): TprCategory {
                    return TprCategory(
                        categoryUuid = response.medicalCategoryUuid!!,
                        categoryName = response.categoryName,
                        categoryImage = response.categoryImage!!,
                        subCategories = response.subCategories.map { TcSubCategory.of(it) }
                    )
                }
            }
        }

        companion object {
            fun of(response: MedicalCategoriesDtd.McdCategoriesResponse): TdPhysicalResponse {
                return TdPhysicalResponse(
                    physicalCategories = response.categories.map { TprCategory.of(it) }
                )
            }
        }
    }


    data class TdMedicalRequest(
        val medicalCategoryTypeEnums: MedicalCategoryTypeEnums,
        val categoryDepth: Short
    ) : BaseValidation<TdMedicalRequest, TdMedicalResponse> {
        companion object {
            fun of(): TdMedicalRequest {
                return TdMedicalRequest(
                    medicalCategoryTypeEnums = MedicalCategoryTypeEnums.MEDICAL_CONSULTATION,
                    categoryDepth = 0
                )
            }
        }
    }

    data class TdMedicalResponse(
        @Schema(title = "진료 카테고리 리스트", required = true)
        val medicalCategories: List<TmrCategory>,
    ) : BaseResponsePopup() {
        data class TmrCategory(
            val categoryUuid: UUID,
            val categoryName: String,
        ) {
            companion object {
                fun of(response: MedicalCategoriesDtd.McdCategoriesResponse.McrCategory): TmrCategory {
                    return TmrCategory(
                        categoryUuid = response.medicalCategoryUuid!!,
                        categoryName = response.categoryName
                    )
                }
            }
        }

        companion object {
            fun of(response: MedicalCategoriesDtd.McdCategoriesResponse): TdMedicalResponse {
                return TdMedicalResponse(
                    medicalCategories = response.categories.map { TmrCategory.of(it) }
                )
            }
        }
    }
}