package com.krossmanzs.intro_spring_kotlin.datasource.mock

import com.krossmanzs.intro_spring_kotlin.datasource.CustomerDataSource
import com.krossmanzs.intro_spring_kotlin.model.Customer
import org.springframework.stereotype.Repository

@Repository
class MockCustomerDataSource : CustomerDataSource{
    val customers = listOf(
        Customer("John", "Doe", "johndoe@example.com", "123-456-7890"),
        Customer("Jane", "Smith", "janesmith@example.com", "987-654-3210"),
        Customer("Alex", "Johnson", "alexjohnson@example.com", "555-123-4567"),
    )
    override fun retrieveCustomers(): Collection<Customer> = customers
}