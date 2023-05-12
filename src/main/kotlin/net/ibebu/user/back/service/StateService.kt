package net.ibebu.user.back.service

import net.ibebu.user.back.data.dtd.StateDtd
import net.ibebu.user.back.repository.StatesRepository
import org.springframework.stereotype.Service

@Service
class StateService(private val statesRepository: StatesRepository) {
    fun getStatesData(): StateDtd.SdStatesResponse {
        return statesRepository.findAll().let {
            StateDtd.SdStatesResponse.of(it)
        }
    }
}