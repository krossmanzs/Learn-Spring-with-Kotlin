package com.krossmanzs.intro_spring_kotlin.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    // allows to make requests to rest api without actually issuing any http requests
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return all customers`() {
        // when/then
        mockMvc.get("/api/customers")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].firstName") { value("John") }
            }
    }
}