package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.mobile.data.CommonDto
import net.ibebu.user.mobile.service.CommonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "공통 기능", description = "공통 기능에 대한 API 이다.")
class CommonController(private val commonService: CommonService) {
    @Operation(summary = "신규 알림 조회 API")
    @GetMapping("v1/common/notifications/new-count")
    fun getV1CommonNotificationsNewCount(): ResponseEntity<SuccessResponse<CommonDto.CdNewCountResponse>> {
        commonService.getNotificationNewCount(CommonDto.CdNewCountRequest.of()).let {
            return ResponseEntity.ok().body(SuccessResponse.of(it))
        }
    }
}