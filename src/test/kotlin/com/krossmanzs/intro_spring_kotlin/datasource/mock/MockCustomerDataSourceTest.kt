package com.krossmanzs.intro_spring_kotlin.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MockCustomerDataSourceTest {
    private val mockDataSource = MockCustomerDataSource()
    @Test
    fun `should provide a collection of customers`() {
        // when
        val customers = mockDataSource.retrieveCustomers()
        
        // then
        Assertions.assertThat(customers.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data`() {
        // when
        val customers = mockDataSource.retrieveCustomers()

        // then
        Assertions.assertThat(customers).anyMatch {it.firstName.isNotBlank()}
        Assertions.assertThat(customers).anyMatch {it.lastName.isNotBlank()}
        Assertions.assertThat(customers).anyMatch {it.email.isNotBlank()}
        Assertions.assertThat(customers).anyMatch {it.phone.isNotBlank()}
    }
}