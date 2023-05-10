package net.ibebu.user.back.data.dao

import jakarta.persistence.*
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.base.BaseEntity
import net.ibebu.user.core.base.BaseRegHistoryEntity
import net.ibebu.user.core.enums.YesOrNo
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "CMM_DEPLOY")
data class Deploy(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "DEPLOY_UUID")
    val deployUuid: UUID? = null,
    @Column(name = "DEVICE_TYPE")
    @Enumerated(EnumType.STRING)
    var deviceType: DeviceTypeEnums,
    @Column(name = "DEPLOY_TITLE")
    var deployTitle: String,
    @Column(name = "DEPLOY_CONTENT")
    var deployContent: String,
    @Column(name = "DEPLOY_VERSION")
    var deployVersion: String,
    @Column(name = "DEL_YN", columnDefinition = "bit")
    var delYn: YesOrNo = YesOrNo.N
) : BaseEntity<Deploy>, BaseRegHistoryEntity()