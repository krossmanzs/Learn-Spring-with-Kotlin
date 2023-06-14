package com.krossmanzs.intro_spring_kotlin.model

import com.fasterxml.jackson.annotation.JsonProperty

// Data Transfer Object (DTO) is because its go over the network and
// serialize into json orientation
data class Customer(
    @JsonProperty("firstname")
    val firstName: String,

    @JsonProperty("lastname")
    val lastName: String,

    val email: String,
    val phone: String
)