package net.ibebu.user.mobile.controller

import net.ibebu.user.core.BaseControllerTest
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

internal class TelemedicineControllerTest: BaseControllerTest(){
    @Test
    fun `진료과 조회`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/telemedicine/categories/medical")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ${getToken()}")
        ).andExpect(MockMvcResultMatchers.jsonPath("\$.responseCode").value(SUCCESS_CODE))
    }

    @Test
    fun `신체 기관 조회`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/telemedicine/categories/physical")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ${getToken()}")
        ).andExpect(MockMvcResultMatchers.jsonPath("\$.responseCode").value(SUCCESS_CODE))
    }
}