package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum


enum class DeviceTypeEnums(val description: String) : BaseEnum<DeviceTypeEnums> {
    A("Android"),
    i("iOS");

    override val value = this
}