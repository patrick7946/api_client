package net.ibebu.user.core.base

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
abstract class BaseRegEntity {
    @Column(name = "REG_UUID")
    val regUuid: UUID? = null

    @CreatedDate
    @Column(name = "REG_DT", insertable = false, updatable = false)
    var regDt: LocalDateTime? = null
}