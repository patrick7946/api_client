package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.TimeZoneEnums
import net.ibebu.user.core.base.BaseDateEntity
import net.ibebu.user.core.base.BaseEntity
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "CMM_STATES")
data class States(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "STATE_UUID")
    val stateUuid: UUID? = null,
    @Column(name = "STATE_NAME_KO")
    val stateNameKo: String,
    @Column(name = "STATE_NAME_EN")
    val stateNameEn: String,
    @Column(name = "STATE_CODE")
    val stateCode: String,
    @Column(name = "TIME_ZONE")
    @Enumerated(EnumType.STRING)
    val timeZone: TimeZoneEnums
) : BaseEntity<States>, BaseDateEntity()