package com.krossmanzs.intro_spring_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IntroApplication

fun main(args: Array<String>) {
    runApplication<IntroApplication>(*args)
}
