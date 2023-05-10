package net.ibebu.user.core.base

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseDateEntity {
    @CreatedDate
    @Column(name = "REG_DT", insertable = false, updatable = false)
    var regDt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "MOD_DT", nullable = true, insertable = false)
    var modDt: LocalDateTime? = null
}