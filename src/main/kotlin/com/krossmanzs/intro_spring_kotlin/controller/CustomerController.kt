package com.krossmanzs.intro_spring_kotlin.controller

import com.krossmanzs.intro_spring_kotlin.model.Customer
import com.krossmanzs.intro_spring_kotlin.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val service: CustomerService) {
    @GetMapping
    fun getCustomer(): Collection<Customer> = service.getCustomers()
}