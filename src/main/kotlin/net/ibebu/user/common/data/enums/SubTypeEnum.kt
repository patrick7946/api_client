package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class SubTypeEnum(val description: String) : BaseEnum<SubTypeEnum> {
    MONTHLY("월 구독"),
    ANNUAL("년 구독");

    override val value = this

    companion object {
        fun String.toLoginTypeEnum(): SubTypeEnum {
            return SubTypeEnum.valueOf(this)
        }
    }
}