package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class PopupTypeEnum(val description: String) : BaseEnum<PopupTypeEnum> {
    NONE("server error"),
    ALERT("Alert 형태로 보여주기 위한 타입"),
    TOAST("Toast 형태로 보여주기 위한 타입");

    override val value = this
}