package net.ibebu.user.mobile.controller

import net.ibebu.user.core.BaseControllerTest
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

internal class StateSelectControllerTest: BaseControllerTest() {
    @Test
    fun `50개 주 조회 API`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/state-select/state-list")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ${getToken()}")
        ).andExpect(MockMvcResultMatchers.jsonPath("\$.responseCode").value(SUCCESS_CODE))
    }

    @Test
    fun `주 설정 API`() {
        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/state-select/state-list/c53bdbaf-f097-11ed-baed-066dafd196cb")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ${getToken()}")
        ).andExpect(MockMvcResultMatchers.jsonPath("\$.responseCode").value(SUCCESS_CODE))
    }
}