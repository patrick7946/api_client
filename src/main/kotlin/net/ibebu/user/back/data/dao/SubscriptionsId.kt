package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.LoginTypeEnum
import net.ibebu.user.core.base.BaseDateEntity
import net.ibebu.user.core.base.BaseEntity
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*

data class SubscriptionsId(
    @Id
    @Column(name = "USER_UUID")
    val userUuid: UUID? = null,
    @Id
    @Column(name = "SUB_SEQ", columnDefinition = "tinyint")
    var subSeq: Short? = null,
) : Serializable