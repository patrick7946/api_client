package net.ibebu.user.external.client

import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.external.data.ExternalDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@FeignClient(name = "externalClient", url = "\${external.base}")
interface ExternalClient {
    @GetMapping(path = ["\${external.oauth2.google}"])
    fun getExternalBaseOauth2Google(
        @RequestParam("authorizationCode") authorizationCode: String,
        @RequestParam("redirectUrl") redirectUrl: String
    ): ResponseEntity<SuccessResponse<ExternalDto.EdOauth2GoogleResponse>>
}