package com.krossmanzs.intro_spring_kotlin.datasource.network.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NameDto(
    @JsonProperty("firstname")
    val firstName: String,
    @JsonProperty("lastname")
    val lastName: String
)