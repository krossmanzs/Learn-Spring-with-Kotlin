package com.krossmanzs.intro_spring_kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {
    @GetMapping
    fun helloWorld(): String {
        return "Hello, this is me Kotlin with Spring Boot"
    }
}