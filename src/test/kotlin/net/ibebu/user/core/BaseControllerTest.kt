package net.ibebu.user.core

import com.fasterxml.jackson.databind.ObjectMapper
import net.ibebu.user.back.data.dtd.UserDtd
import net.ibebu.user.common.data.enums.LoginTypeEnum
import net.ibebu.user.core.enums.YesOrNo
import net.ibebu.user.core.token.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
abstract class BaseControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var tokenProvider: TokenProvider

    fun <T> responseParse(result: MvcResult, responseClass: Class<T>): T {
        return objectMapper.readValue(result.response.contentAsString, responseClass)
    }

    final val SUCCESS_CODE = 2000
    final val FAIL_CODE = 4000

    fun getToken(): String {
        return UserDtd.UdLoginResponse(
            userUuid = UUID.fromString("b71f64ef-3ec4-41b6-b5eb-e37a145dd817"),
            userEmail = "qk54r71@nate.com",
            userPwd = "\$2a\$10\$.H9kXhXLy0KT5pjj/Xm5hOz50oyl1ZtxOsvSMqy1b.3ynHUYx30V6",
            loginType = LoginTypeEnum.NORMAL,
            paidMemberYn = YesOrNo.Y
        ).let {
            tokenProvider.createToken(it)
        }
    }

}