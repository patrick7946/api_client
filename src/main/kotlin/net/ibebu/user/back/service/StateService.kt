package net.ibebu.user.back.service

import net.ibebu.user.back.data.dtd.StateDtd
import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.back.repository.StatesRepository
import net.ibebu.user.back.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class StateService(private val statesRepository: StatesRepository) {
    fun getStatesData(): StateDtd.SdStates {
        return statesRepository.findAll().let {
            StateDtd.SdStates.of(it)
        }
    }
}