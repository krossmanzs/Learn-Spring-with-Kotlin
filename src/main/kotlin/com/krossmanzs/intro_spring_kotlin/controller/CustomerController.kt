package com.krossmanzs.intro_spring_kotlin.controller

import com.krossmanzs.intro_spring_kotlin.model.Customer
import com.krossmanzs.intro_spring_kotlin.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val service: CustomerService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getCustomer(): Collection<Customer> = service.getCustomers()

    @GetMapping("/{email}")
    fun getBank(@PathVariable email: String): Customer = service.getBank(email)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCustomer(@RequestBody customer: Customer): Customer = service.addCustomer(customer)

    @PatchMapping
    fun updateBank(@RequestBody customer: Customer): Customer = service.updateCustomer(customer)

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable email: String): Customer = service.deleteCustomer(email)
}