package net.ibebu.user.back.repository

import net.ibebu.user.back.data.dao.SentNotifications
import net.ibebu.user.core.enums.YesOrNo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SentNotificationsRepository : JpaRepository<SentNotifications, UUID> {
    fun findAllByUserUuidAndReadYn(userUuid: UUID, readYn: YesOrNo = YesOrNo.N): List<SentNotifications>
}