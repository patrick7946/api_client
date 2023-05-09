package net.ibebu.user.back.data.dtd

import net.ibebu.user.back.data.dao.Deploy
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.base.BaseDto

object DeployDtd {
    data class DdVersionResponse(
        val deviceTypeEnums: DeviceTypeEnums,
        val version: String,
        val title: String,
        val content: String
    ) {
        companion object Of : BaseDto<Deploy, DdVersionResponse> {
            override fun of(entity: Deploy): DdVersionResponse {
                return DdVersionResponse(
                    deviceTypeEnums = entity.deviceType,
                    version = entity.deployVersion,
                    title = entity.deployTitle,
                    content = entity.deployContent
                )
            }
        }
    }
}