package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class PopupTypeEnum(val description: String) : BaseEnum<PopupTypeEnum> {
    ALERT(FailMessageEnums.FAIL_ALERT.message);

    override val value = this
}