package com.example.ch04_basic_of_spring_boot

import org.springframework.stereotype.Service

interface SampleService {
    fun execute(): ServiceDto
}

@Service
class SampleServiceImpl(val sampleRepository: SampleRepository) : SampleService {
    override fun execute(): ServiceDto {
        val result = sampleRepository.getPersonFromDb()
        return ServiceDto(ServicePerson(result.name, result.age))
    }
}

data class ServiceDto(
    val person: ServicePerson,
)

data class ServicePerson(
    val name: String,
    val age: Int,
)