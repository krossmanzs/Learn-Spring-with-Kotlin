package com.krossmanzs.intro_spring_kotlin.model

// Data Transfer Object (DTO) is because its go over the network and
// serialize into json orientation
data class Customer(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String
)