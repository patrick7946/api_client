package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class MedicalCategoryTypeEnums(val description: String) : BaseEnum<MedicalCategoryTypeEnums> {
    PHYSICAL_ORGAN("신체기관"),
    MEDICAL_CONSULTATION("진료"),
    GENERAL_COUNSELING("일반 상담"),
    PSYCHOLOGICAL_COUNSELING("심리 상담");

    override val value = this
}