package com.krossmanzs.intro_spring_kotlin.service

import com.krossmanzs.intro_spring_kotlin.datasource.CustomerDataSource
import com.krossmanzs.intro_spring_kotlin.model.Customer
import org.springframework.stereotype.Service

@Service
class CustomerService(private val dataSource: CustomerDataSource) {
    fun getCustomers(): Collection<Customer> = dataSource.retrieveCustomers()
    fun getBank(email: String): Customer = dataSource.retrieveCustomer(email)
}