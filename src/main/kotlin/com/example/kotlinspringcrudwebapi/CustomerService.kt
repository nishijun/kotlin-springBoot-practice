package com.example.kotlinspringcrudwebapi

import org.springframework.stereotype.Service

/**
 * Customer 操作をおこなうメソッドをまとめるインタフェース
 *
 */
interface CustomerService {
    fun insertCustomer(firstName: String, lastName: String)
    fun selectCustomer(): List<Customer>
    fun updateCustomer(id: Int, firstName: String, lastName: String)
    fun deleteCustomer(id: Int)
}

/**
 * Customer 操作をおこなうメソッドを実装するクラス
 *
 * @property customerRepository
 */

@Service
class CustomerServiceImpl(val customerRepository: CustomerRepository) : CustomerService {
    override fun insertCustomer(firstName: String, lastName: String) = customerRepository.add(firstName, lastName)

    override fun selectCustomer(): List<Customer> = customerRepository.find()

    override fun updateCustomer(id: Int, firstName: String, lastName: String) =
        customerRepository.update(id, firstName, lastName)

    override fun deleteCustomer(id: Int) = customerRepository.delete(id)
}