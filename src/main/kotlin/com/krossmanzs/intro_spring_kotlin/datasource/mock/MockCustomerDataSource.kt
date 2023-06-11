package com.krossmanzs.intro_spring_kotlin.datasource.mock

import com.krossmanzs.intro_spring_kotlin.datasource.CustomerDataSource
import com.krossmanzs.intro_spring_kotlin.model.Customer
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockCustomerDataSource : CustomerDataSource{
    val customers = mutableListOf(
        Customer("John", "Doe", "johndoe@example.com", "123-456-7890"),
        Customer("Jane", "Smith", "janesmith@example.com", "987-654-3210"),
        Customer("Alex", "Johnson", "alexjohnson@example.com", "555-123-4567"),
    )
    override fun retrieveCustomers(): Collection<Customer> = customers

    override fun retrieveCustomer(email: String): Customer =
        customers.firstOrNull() { it.email == email }
            ?: throw NoSuchElementException("Could not find a customer with email $email")

    override fun createCustomer(customer: Customer): Customer {
        if (customers.any { it.email == customer.email }) {
            throw IllegalArgumentException("Customer with email ${customer.email} already exists!")
        }

        customers.add(customer)

        return customer
    }
}