package com.microservice.kotlinmicroservice

import com.microservice.kotlinmicroservice.chapter03.model.Customer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class KotlinMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<KotlinMicroserviceApplication>(*args)
}
