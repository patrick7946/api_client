package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class LoginTypeEnum(val description: String) : BaseEnum<LoginTypeEnum> {
    NORMAL("일반적인 방법"),
    GOOGLE("Google 이메일로 소셜 로그인"),
    APPLE("Apple 이메일로 소셜 로그인");

    override val value = this
}