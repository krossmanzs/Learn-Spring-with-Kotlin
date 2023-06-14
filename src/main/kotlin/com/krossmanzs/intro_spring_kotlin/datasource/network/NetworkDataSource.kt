package com.krossmanzs.intro_spring_kotlin.datasource.network

import com.krossmanzs.intro_spring_kotlin.datasource.CustomerDataSource
import com.krossmanzs.intro_spring_kotlin.datasource.network.dto.CustomerList
import com.krossmanzs.intro_spring_kotlin.model.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("network")
class NetworkDataSource(
    @Autowired private val restTemplate: RestTemplate
) : CustomerDataSource {
    override fun retrieveCustomers(): Collection<Customer> {
//        val response: ResponseEntity<CustomerList> =
//            restTemplate.getForEntity<CustomerList>("http://fakestoreapi.com/users")

//        val response: ResponseEntity<Array<Customer>> =
//            restTemplate.getForEntity("http://fakestoreapi.com/users", Array<Customer>::class.java)

        val customers: MutableCollection<Customer> = mutableListOf()

        val response: ResponseEntity<Array<CustomerList>> =
            restTemplate.getForEntity<Array<CustomerList>>("http://fakestoreapi.com/users")

        if (response.body == null) throw IOException("Could not fetch banks from the network")

        response.body?.let {
            for (customerDto in it) {
                customers.add(customerDto.convertCustomer())
            }
        }

        return customers
    }

    override fun retrieveCustomer(email: String): Customer {
        TODO("Not yet implemented")
    }

    override fun createCustomer(customer: Customer): Customer {
        TODO("Not yet implemented")
    }

    override fun updateCustomer(customer: Customer): Customer {
        TODO("Not yet implemented")
    }

    override fun deleteCustomer(email: String): Customer {
        TODO("Not yet implemented")
    }
}