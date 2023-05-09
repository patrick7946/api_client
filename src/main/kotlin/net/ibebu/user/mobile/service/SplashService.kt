package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.DeployService
import net.ibebu.user.mobile.data.SplashDto
import org.springframework.stereotype.Service

@Service
class SplashService(private val deployService: DeployService) {
    fun getSplashVersion(request: SplashDto.SdVersionRequest): SplashDto.SdVersionResponse {
        return request.validation {
            deployService.getDeployVersion(it.deviceType).let { response ->
                SplashDto.SdVersionResponse.of(response)
            }
        }
    }
}