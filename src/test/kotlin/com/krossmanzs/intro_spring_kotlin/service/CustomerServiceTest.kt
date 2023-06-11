package com.krossmanzs.intro_spring_kotlin.service

import com.krossmanzs.intro_spring_kotlin.datasource.CustomerDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CustomerServiceTest {
    // relaxed = whenever a method is called on it will just return a default value
    private val dataSource: CustomerDataSource = mockk(relaxed = true)
    private val customerService = CustomerService(dataSource)

    @Test
    fun `should `() {
        // when
        val customers = customerService.getCustomers()
        
        // then
        // method call was being done by exactly once
        verify(exactly = 1) { dataSource.retrieveCustomers() }
    }
}