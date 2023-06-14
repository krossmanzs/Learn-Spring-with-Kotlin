package com.krossmanzs.intro_spring_kotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.krossmanzs.intro_spring_kotlin.model.Customer
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest @Autowired constructor(
    private val mockMvc: MockMvc, // allows to make requests to rest api without actually issuing any http requests
    private val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/customers"
    
    @Nested
    @DisplayName("GET /api/customers")
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
    @DisplayName("GET /api/customers/{email}")
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
    
    @Nested
    @DisplayName("POST /api/customers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewCustomer {
        @Test
        fun `should add the new customer`() {
            // given
            val newCustomer = Customer(
                "Cornelius",
                "Linux",
                "abc@gmail.com",
                "081234565"
            )

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newCustomer)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.firstName") { value("Cornelius") }
                    jsonPath("$.lastName") { value("Linux") }
                    jsonPath("$.email") { value("abc@gmail.com") }
                    jsonPath("$.phone") { value("081234565") }
                }

            mockMvc.get("$baseUrl/${newCustomer.email}")
                .andExpect{ content { objectMapper.writeValueAsString(newCustomer) } }
        }

        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`() {
            // given
            val invalidBank = Customer(
                "invalid",
                "name",
                "johndoe@example.com",
                "32323232"
            )

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    @DisplayName("PATCH /api/customers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class patchExistingCustomer {
        @Test
        fun `should update a customer`() {
            // given
            val customer = Customer(
                "Jane",
                "Smith",
                "janesmith@example.com",
                "987-654-3210")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(customer)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        content { MediaType.APPLICATION_JSON }
                        json(objectMapper.writeValueAsString(customer))
                    }
                }

            mockMvc.get("$baseUrl/${customer.email}")
                .andExpect { content { json(objectMapper.writeValueAsString(customer)) } }
        }
        @Test
        fun `should return NOT FOUND if email does not exist`() {
            // given
            val customer = Customer(
                "Jane",
                "Smith",
                "awikwok.com",
                "987-654-3210")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(customer)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("DELETE /api/customers/{email}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class RemoveCustomer {
        @Test
        @DirtiesContext
        fun `should remove a customer with given email`() {
            // given
            val email = "janesmith@example.com"

            // when/then
            mockMvc.delete("$baseUrl/$email")
                .andDo { print() }
                .andExpect { status { isNoContent() } }

            mockMvc.get("$baseUrl/${email}")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return NOT FOUND if email does not exist`() {
            // given
            val email = "anjay@gmail.com"

            // when
            mockMvc.delete("$baseUrl/$email")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}