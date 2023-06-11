package com.krossmanzs.intro_spring_kotlin.datasource

import com.krossmanzs.intro_spring_kotlin.model.Customer

interface CustomerDataSource {
    fun retrieveCustomers(): Collection<Customer>
}