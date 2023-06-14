package com.krossmanzs.intro_spring_kotlin.datasource.network.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.krossmanzs.intro_spring_kotlin.model.Customer

data class CustomerList (
    @JsonProperty("email")
    val email: String,
    @JsonProperty("name")
    val names: NameDto,
    @JsonProperty("phone")
    val phone: String
) {
    fun convertCustomer(): Customer = Customer(names.firstName, names.lastName, email, phone)
}
