package com.example.kotlinspringcrudwebapi

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.*

/**
 * Customer テーブルの CRUD をおこなう Web API のエンドポイントを定義するクラス
 *
 * @property customerService カスタマーサービス
 */
@RestController
class CustomerController(val customerService: CustomerService) {
    @PostMapping("/customers")
    fun insert(@RequestBody request: CustomerRequest): String {
        println("aaaaaaaaaaaaaaaaa")
        customerService.insertCustomer(request.firstName, request.lastName)
        return """
            {
                "message": "success"
            }
        """.trimIndent()
    }

    @GetMapping("/customers")
    fun read(): CustomerResponse = CustomerResponse(customerService.selectCustomer())

    @PutMapping("/customers/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody request: CustomerRequest): String {
        customerService.updateCustomer(id, request.firstName, request.lastName)
        return """
            {
                "message": "success"
            }
        """.trimIndent()
    }

    @DeleteMapping("/customers/{id}")
    fun delete(@PathVariable("id") id: Int): String {
        customerService.deleteCustomer(id)
        return """
            {
                "message": "success"
            }
        """.trimIndent()
    }
}

data class CustomerRequest(
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String,
)

data class CustomerResponse(
    val customers: List<Customer>
)