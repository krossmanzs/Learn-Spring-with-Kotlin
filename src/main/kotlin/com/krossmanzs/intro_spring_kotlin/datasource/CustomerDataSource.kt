package com.krossmanzs.intro_spring_kotlin.datasource

import com.krossmanzs.intro_spring_kotlin.model.Customer

interface CustomerDataSource {
    fun retrieveCustomers(): Collection<Customer>
    fun retrieveCustomer(email: String): Customer
    fun createCustomer(customer: Customer): Customer
    fun updateCustomer(customer: Customer): Customer
    fun deleteCustomer(email: String): Customer
}