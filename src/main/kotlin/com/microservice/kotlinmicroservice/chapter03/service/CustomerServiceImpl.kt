package com.microservice.kotlinmicroservice.chapter03.service

import com.microservice.kotlinmicroservice.chapter03.model.Customer.Telephone
import com.microservice.kotlinmicroservice.chapter03.model.Customer
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl: CustomerService {
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring", Telephone("+82", "01012345678")),
            Customer(3, "Microservice", Telephone("+81", "01099998888"))
        )
    }

    val customers = ConcurrentHashMap<Int,Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        deleteCustomer(id)
        createCustomer(customer)
    }

    override fun searchCustomers(nameFilter: String): List<Customer> = customers.filter {
        print(nameFilter)
        it.value.name.contains(nameFilter, true)
    }.map(Map.Entry<Int, Customer>:: value).toList()

}