package net.ibebu.user.mobile.service

import net.ibebu.user.back.service.StateService
import net.ibebu.user.back.service.UserService
import net.ibebu.user.mobile.data.StateSelectDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class StateSelectService(private val stateService: StateService, private val userService: UserService) {
    fun getStates(): StateSelectDto.SsdStateListResponse {
        return stateService.getStatesData().let {
            StateSelectDto.SsdStateListResponse.of(it)
        }
    }

    @Transactional
    fun putStateToUser(request: StateSelectDto.SsdStateSelectRequest): StateSelectDto.SsdStateSelectResponse {
        return userService.putUserState(request.toRequestDtd()).let {
            StateSelectDto.SsdStateSelectResponse.of()
        }
    }
}