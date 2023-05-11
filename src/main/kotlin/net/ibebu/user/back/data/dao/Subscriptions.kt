package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.SubTypeEnum
import net.ibebu.user.core.base.BaseDateEntity
import net.ibebu.user.core.base.BaseEntity
import net.ibebu.user.core.enums.YesOrNo
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "USR_SUBSCRIPTIONS")
@IdClass(SubscriptionsId::class)
data class Subscriptions(
    @Id
    @Column(name = "USER_UUID")
    val userUuid: UUID? = null,
    @Id
    @Column(name = "SUB_SEQ", columnDefinition = "tinyint")
    var subSeq: Short? = null,
    @Column(name = "SUB_START")
    val subStart: LocalDateTime,
    @Column(name = "SUB_END")
    val subEnd: LocalDateTime,
    @Column(name = "SUB_TYPE")
    @Enumerated(EnumType.STRING)
    val subType: SubTypeEnum,
    @Column(name = "DEL_YN", columnDefinition = "bit")
    var delYn: YesOrNo = YesOrNo.N
) : BaseEntity<Subscriptions>, BaseDateEntity() {
    @ManyToOne
    @JoinColumn(name = "USER_UUID", insertable = false, updatable = false)
    val user: User? = null

}