package com.krossmanzs.intro_spring_kotlin.service

import com.krossmanzs.intro_spring_kotlin.datasource.CustomerDataSource
import com.krossmanzs.intro_spring_kotlin.model.Customer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class CustomerService(@Qualifier("network") private val dataSource: CustomerDataSource) {
    fun getCustomers(): Collection<Customer> = dataSource.retrieveCustomers()
    fun getBank(email: String): Customer = dataSource.retrieveCustomer(email)
    fun addCustomer(customer: Customer): Customer = dataSource.createCustomer(customer)
    fun updateCustomer(customer: Customer): Customer = dataSource.updateCustomer(customer)
    fun deleteCustomer(email: String): Customer = dataSource.deleteCustomer(email)
}