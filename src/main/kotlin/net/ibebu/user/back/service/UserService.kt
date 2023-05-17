package net.ibebu.user.back.service

import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.back.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    @Autowired
    private val passwordEncoder: PasswordEncoder
) {
    fun getUserByEmail(email: String): UserDtd.UdLoginResponse {
        return userRepository.findTopByUserEmailOrderByRegDt(email).let {
            UserDtd.UdLoginResponse.of(it)
        }
    }

    fun getUserByUuid(userUuid: UUID): UserDtd.UdIdentificationResponse {
        return userRepository.findById(userUuid).get().let { it.toDto(UserDtd.UdIdentificationResponse.Of) }
    }

    fun postUserData(userData: UserDtd.UdUserSave) {
        userRepository.save(userData.toEntity(passwordEncoder))
    }

    fun putUserState(stateData: UserDtd.UdStateUpdateRequest) {
        userRepository.findById(stateData.userUuid).ifPresent { it.stateUuid = stateData.stateUuid }
    }
}