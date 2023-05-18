package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class SentTypeEnums(val description: String) : BaseEnum<SentTypeEnums> {
    IMMEDIATELY("즉시 발송"),
    PENDING("예약 발송");

    override val value = this
}