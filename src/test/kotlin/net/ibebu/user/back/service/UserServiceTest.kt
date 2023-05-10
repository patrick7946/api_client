package net.ibebu.user.back.service

import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.common.data.enums.LoginTypeEnum
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
internal class UserServiceTest(
    @Autowired
    private val userService: UserService,
    @Autowired
    private val passwordEncoder: PasswordEncoder
) {

//    @Test
//    fun `사용자 정보 저장`() {
//        UserDtd.UdUserSave(
//            userEmail = "qk54r71@nate.com",
//            userPwd = "ibebu794613",
//            loginType = LoginTypeEnum.NORMAL
//        ).let {
//            userService.postUserData(it)
//        }
//    }

    @Test
    fun `사용자 로그인 유효성 검증`() {
        UserDtd.UdUserSave(
            userEmail = "qk54r71@nate.com",
            userPwd = "ibebu794613",
            loginType = LoginTypeEnum.NORMAL
        ).let {
            userService.getUserByEmail(it.userEmail).let { response ->
                assert(
                    passwordEncoder.matches(
                        it.userPwd,
                        response.userPwd
                    )
                )
            }
        }
    }
}