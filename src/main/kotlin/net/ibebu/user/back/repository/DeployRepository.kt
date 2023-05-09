package net.ibebu.user.back.repository

import net.ibebu.user.back.data.dao.Deploy
import net.ibebu.user.common.data.enums.DeviceTypeEnums
import net.ibebu.user.core.enums.YesOrNo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DeployRepository : JpaRepository<Deploy, UUID> {
    fun findTopByDeviceTypeAndDelYnOrderByRegDtDesc(deviceTypeEnums: DeviceTypeEnums, delYn: YesOrNo): Deploy
}