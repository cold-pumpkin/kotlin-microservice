package com.microservice.kotlinmicroservice.chapter03.exception

import org.springframework.web.bind.annotation.ExceptionHandler

class CustomerNotFoundException(message: String) : Exception(message)