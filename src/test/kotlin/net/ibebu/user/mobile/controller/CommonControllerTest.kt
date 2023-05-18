package net.ibebu.user.mobile.controller

import net.ibebu.user.core.BaseControllerTest
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

internal class CommonControllerTest : BaseControllerTest() {
    @Test
    fun `신규 알림 조회`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/common/notifications/new-count")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ${getToken()}")
        ).andExpect(MockMvcResultMatchers.jsonPath("\$.responseCode").value(SUCCESS_CODE))
    }
}