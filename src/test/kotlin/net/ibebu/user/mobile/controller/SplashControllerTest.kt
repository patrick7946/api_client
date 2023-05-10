package net.ibebu.user.mobile.controller

import net.ibebu.user.core.BaseControllerTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest
internal class SplashControllerTest : BaseControllerTest() {
    @Test
    fun `버전 조회`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/splash/version")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("deviceType", "A")
        ).andExpect(MockMvcResultMatchers.jsonPath("\$.responseCode").value(SUCCESS_CODE))
    }

    @Test
    fun `로그인 유효성 검증 - 성공`() {
        val loginData = mapOf("email" to "qk54r71@nate.com", "password" to "ibebu794613")
        val jsonRequest = objectMapper.writeValueAsString(loginData)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/splash/login")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.jsonPath("\$.responseCode").value(SUCCESS_CODE))
    }
}