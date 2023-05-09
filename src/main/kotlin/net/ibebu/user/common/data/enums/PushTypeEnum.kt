package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class PushTypeEnum(val description: String) : BaseEnum<PushTypeEnum> {
    NOTIFICATION("알림 메시지에 사용되는 경우"),
    EVENT("이벤트 메시지에 사용되는 경우");

    override val value = this
}