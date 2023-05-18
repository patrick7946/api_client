package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.PushTypeEnum
import net.ibebu.user.common.data.enums.SentTypeEnums
import net.ibebu.user.core.base.BaseDateEntity
import net.ibebu.user.core.base.BaseEntity
import net.ibebu.user.core.enums.YesOrNo
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "PUS_SENT_NOTIFICATIONS")
data class SentNotifications(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "SENT_NOTIFICATION_UUID")
    val sentNotificationUuid: UUID? = null,
    @Column(name = "USER_UUID")
    val userUuid: UUID,
    @Column(name = "PUSH_TYPE")
    @Enumerated(EnumType.STRING)
    var pushType: PushTypeEnum,
    @Column(name = "SENT_TYPE")
    @Enumerated(EnumType.STRING)
    var sentType: SentTypeEnums,
    @Column(name = "PENDING_NOTIFICATION_UUID")
    val pendingNotificationUuid: UUID? = null,
    @Column(name = "TITLE")
    var title: String,
    @Column(name = "CONTENT", columnDefinition = "text")
    var content: String,
    @Column(name = "READ_YN", columnDefinition = "bit")
    var readYn: YesOrNo = YesOrNo.N
) : BaseEntity<SentNotifications>, BaseDateEntity()