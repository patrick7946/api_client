package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.StateService
import net.ibebu.user.mobile.data.StateSelectDto
import org.springframework.stereotype.Service


@Service
class StateSelectService(private val stateService: StateService) {
    fun getStates(): StateSelectDto.SsdStateListResponse {
        return stateService.getStatesData().let {
            StateSelectDto.SsdStateListResponse.of(it)
        }
    }
}