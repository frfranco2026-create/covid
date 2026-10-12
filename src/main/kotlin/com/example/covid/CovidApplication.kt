package com.example.covid

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CovidApplication

fun main(args: Array<String>) {
	runApplication<CovidApplication>(*args)
}
