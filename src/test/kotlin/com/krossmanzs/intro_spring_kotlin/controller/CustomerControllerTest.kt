package com.krossmanzs.intro_spring_kotlin.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
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

    val baseUrl = "/api/customers"
    
    @Nested
    @DisplayName("getCustomers()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetCustomers {
        @Test
        // dont use this type of name to production lmao
        fun `should return all customers`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].firstName") { value("John") }
                }
        }
    }
    
    @Nested
    @DisplayName("getCustomer()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetCustomer {
        @Test
        fun `should return the customer with the given email`() {
            // given
            val email = "janesmith@example.com"

            // when/then
            mockMvc.get("$baseUrl/$email")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.firstName") { value("Jane") }
                    jsonPath("$.lastName") { value("Smith") }
                }
        }

        @Test
        fun `should return Not Found if email does not exist`() {
            // given
            val email = "a@a.com"

            // when/then
            mockMvc.get("$baseUrl/$email")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}