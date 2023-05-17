package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.UserService
import net.ibebu.user.external.client.ExternalClient
import net.ibebu.user.external.data.ExternalDto
import net.ibebu.user.mobile.data.IdUploadDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class IdentificationUploadService(
    private val userService: UserService,
    private val externalClient: ExternalClient
) {
    @Transactional
    fun postIdImageUpload(request: IdUploadDto.IudUploadRequest): IdUploadDto.IudUploadResponse {
        return request.validation {
            ExternalDto.EdValidationRequest.of(it, userService.getUserByUuid(it.userUuid)).let { validationRequest ->
                externalClient.postExternalIdentificationValidation(validationRequest).body!!.success!!
            }.let { validationResponse ->
                IdUploadDto.IudUploadResponse.of(validationResponse)
            }
        }
    }
}