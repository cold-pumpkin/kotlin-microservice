package com.microservice.kotlinmicroservice.chapter03.controller

import com.microservice.kotlinmicroservice.chapter03.exception.CustomerNotFoundException
import com.microservice.kotlinmicroservice.chapter03.exception.ErrorResponse
import com.microservice.kotlinmicroservice.chapter03.model.Customer
import com.microservice.kotlinmicroservice.chapter03.service.CustomerService
import com.microservice.kotlinmicroservice.chapter03.service.CustomerServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Any> {
        val customer = customerService.getCustomer(id)
            //?: throw CustomerNotFoundException("customer '$id' not found")
        return if (customer != null)
            ResponseEntity(customer, HttpStatus.OK)
        else
            // ControllerAdvice 피하기
            ResponseEntity(ErrorResponse("Customer Not Found", "customer '$id' not found"), HttpStatus.NOT_FOUND)
    }

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit?> {
        customerService.createCustomer(customer)
        return ResponseEntity<Unit?>(null, HttpStatus.CREATED)  // Unit 메소드에서도 반환하는 내용이 없도록
    }

    @DeleteMapping("/customer/{id}")
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/customer/{id}")
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping("/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String)
            = customerService.searchCustomers(nameFilter)

}