package com.example.kotlinspringcrudwebapi

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

/**
 * Customer テーブルを操作するメソッドをまとめたインタフェース
 *
 */
interface CustomerRepository {
    fun add(firstName: String, lastName: String)
    fun find(): List<Customer>
    fun update(id: Int, firstName: String, lastName: String)
    fun delete(id: Int)
}

/**
 * CustomerRepository を実装したクラス
 *
 * @property namedParameterJdbcTemplate
 */
@Repository
class CustomerRepositoryImpl(val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : CustomerRepository {
    override fun add(firstName: String, lastName: String) {
        val sql = "INSERT INTO customer (first_name, last_name) VALUES (:first_name, :last_name);"
        val sqlParams = MapSqlParameterSource()
            .addValue("first_name", firstName)
            .addValue("last_name", lastName)
        namedParameterJdbcTemplate.update(sql, sqlParams)
    }

    override fun find(): List<Customer> {
        val sql = "SELECT id, first_name, last_name FROM customer;"
        val sqlParams = MapSqlParameterSource()
        val customers = namedParameterJdbcTemplate.queryForList(sql, sqlParams)
        return customers.map {
            Customer(
                it["id"].toString().toLong(),
                it["first_name"].toString(),
                it["last_name"].toString(),
            )
        }
    }

    override fun update(id: Int, firstName: String, lastName: String) {
        val sql = "UPDATE customer SET first_name = :first_name, last_name = :last_name WHERE id = :id;"
        val sqlParams = MapSqlParameterSource()
            .addValue("first_name", firstName)
            .addValue("last_name", lastName)
            .addValue("id", id)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }

    override fun delete(id: Int) {
        val sql = "DELETE FROM customer WHERE id = :id;"
        val sqlParams = MapSqlParameterSource()
            .addValue("id", id)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }
}