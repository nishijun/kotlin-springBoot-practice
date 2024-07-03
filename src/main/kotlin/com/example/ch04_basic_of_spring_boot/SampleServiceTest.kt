package com.example.ch04_basic_of_spring_boot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SampleServiceTest {
    @Test
    fun diTestForSampleRepository() {
        /**
         * given:
         */
        val sampleRepository = object : SampleRepository {
            override fun getPersonFromDb(): PersonFromDb = PersonFromDb("Carol", 25)
        }
        val sampleService = SampleServiceImpl(sampleRepository)

        /**
         * when:
         */
        val actual = sampleService.execute()

        /**
         * then:
         */
        val expected = ServiceDto(ServicePerson("Carol", 25))
        assertEquals(actual, expected)
    }
}