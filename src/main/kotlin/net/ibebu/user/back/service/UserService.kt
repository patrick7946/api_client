package net.ibebu.user.back.service

import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.back.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

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

    fun postUserData(userData: UserDtd.UdUserSave) {
        userRepository.save(userData.toEntity(passwordEncoder))
    }

    fun putUserState(stateData: UserDtd.UdStateUpdateRequest) {
        userRepository.findById(stateData.userUuid).ifPresent { it.stateUuid = stateData.stateUuid }
    }
}