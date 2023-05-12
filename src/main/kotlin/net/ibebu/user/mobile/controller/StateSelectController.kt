package net.ibebu.user.mobile.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import net.ibebu.user.common.data.SuccessResponse
import net.ibebu.user.mobile.data.StateSelectDto
import net.ibebu.user.mobile.service.StateSelectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "주 선택 화면", description = "주 선택 화면에 대한 API 이다.")
class StateSelectController(private val stateSelectService: StateSelectService) {
    @Operation(summary = "미국의 50개 주 조회 API")
    @GetMapping("v1/state-select/state-list")
    fun getV1StateSelectStateList(): ResponseEntity<SuccessResponse<StateSelectDto.SsdStateListResponse>> {
        stateSelectService.getStates().let {
            return ResponseEntity.ok().body(SuccessResponse(it))
        }
    }
}