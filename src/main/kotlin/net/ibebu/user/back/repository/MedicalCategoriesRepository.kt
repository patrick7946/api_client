package net.ibebu.user.back.repository

import net.ibebu.user.back.data.dao.MedicalCategories
import net.ibebu.user.common.data.enums.MedicalCategoryTypeEnums
import net.ibebu.user.core.enums.YesOrNo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MedicalCategoriesRepository : JpaRepository<MedicalCategories, UUID> {
    fun findAllByCategoryDepthAndCategoryTypeAndDelYn(
        categoryDepth: Short = 0,
        categoryType: MedicalCategoryTypeEnums,
        delYn: YesOrNo = YesOrNo.N
    ): List<MedicalCategories>
}