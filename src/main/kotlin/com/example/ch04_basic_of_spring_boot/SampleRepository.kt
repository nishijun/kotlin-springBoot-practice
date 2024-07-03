package com.example.ch04_basic_of_spring_boot

import org.springframework.stereotype.Repository

interface SampleRepository {
    fun getPersonFromDb(): PersonFromDb
}

@Repository
class SampleRepositoryImpl : SampleRepository {
    override fun getPersonFromDb(): PersonFromDb {
        return PersonFromDb("Alice", 23)
    }
}

data class PersonFromDb(val name: String, val age: Int)