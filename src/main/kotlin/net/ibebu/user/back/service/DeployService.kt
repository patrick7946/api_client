package net.ibebu.user.back.service

import net.ibebu.user.back.data.dtd.DeployDtd
import net.ibebu.user.back.repository.DeployRepository
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.enums.YesOrNo
import net.ibebu.user.mobile.data.SplashDto
import org.springframework.stereotype.Service

@Service
class DeployService(private val deployRepository: DeployRepository) {
    fun getDeployVersion(deviceTypeEnums: DeviceTypeEnums): DeployDtd.DdVersionResponse {
        return deployRepository.findTopByDeviceTypeAndDelYnOrderByRegDtDesc(deviceTypeEnums, YesOrNo.N).let {
            it.toDto(DeployDtd.DdVersionResponse)
        }
    }
}