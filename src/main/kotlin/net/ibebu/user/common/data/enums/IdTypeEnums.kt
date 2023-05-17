package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class IdTypeEnums(val description: String, val idName: String) : BaseEnum<IdTypeEnums> {
    passport("Passport", "여권"),
    driving_license("Driving License", "운전 면허증"),
    residence_permit("Residence Permit", "체류허가");

    override val value = this
}