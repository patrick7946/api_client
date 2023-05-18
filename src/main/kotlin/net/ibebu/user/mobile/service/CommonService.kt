package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.SentNotificationsService
import net.ibebu.user.back.service.UserService
import net.ibebu.user.mobile.data.CommonDto
import org.springframework.stereotype.Service


@Service
class CommonService(
    private val sentNotificationsService: SentNotificationsService,
) {
    fun getNotificationNewCount(request: CommonDto.CdNewCountRequest): CommonDto.CdNewCountResponse {
        return sentNotificationsService.getNoneReadNotifications(request.userUuid).let {
            CommonDto.CdNewCountResponse.of(it)
        }
    }
}