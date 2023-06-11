package com.krossmanzs.intro_spring_kotlin.controller

import com.krossmanzs.intro_spring_kotlin.model.Customer
import com.krossmanzs.intro_spring_kotlin.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val service: CustomerService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getCustomer(): Collection<Customer> = service.getCustomers()

    @GetMapping("/{email}")
    fun getBank(@PathVariable email: String): Customer = service.getBank(email)
}