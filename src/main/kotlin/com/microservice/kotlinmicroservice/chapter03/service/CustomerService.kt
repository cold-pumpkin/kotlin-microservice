package com.microservice.kotlinmicroservice.chapter03.service

import com.microservice.kotlinmicroservice.chapter03.model.Customer

interface CustomerService {
    fun getCustomer(id: Int): Customer?     // null일 수 있음
    fun createCustomer(customer: Customer)
    fun deleteCustomer(id: Int)
    fun updateCustomer(id: Int, customer: Customer)
    fun searchCustomers(nameFilter: String): List<Customer>

}