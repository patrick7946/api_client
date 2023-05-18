package net.ibebu.user.back.service

import net.ibebu.user.back.repository.SentNotificationsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SentNotificationsService(private val sentNotificationsRepository: SentNotificationsRepository) {
    fun getNoneReadNotifications(userUuid: UUID): Int {
        sentNotificationsRepository.findAllByUserUuidAndReadYn(userUuid).let {
            return it.size
        }
    }
}