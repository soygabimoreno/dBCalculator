package com.appacoustic.android.dbcalculator.domain

import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class CalculateDBSumTest {

    private lateinit var calculateDBSum: CalculateDBSum

    @Before
    fun setUp() {
        calculateDBSum = CalculateDBSum()
    }

    @Test
    fun `if input is a well-formed sum, then returns the correct value`() {
        assertCalculation(1.5f, "3 - 2 + 0")
    }

    @Test
    fun `if input ends with the plus with spaces delimiter, then returns the correct value`() {
        assertCalculation(6f, "3 + 3 + ")
    }

    private fun assertCalculation(expectedResult: Float, input: String) =
        calculateDBSum(input)
            .fold({
                fail("")
            }, { sum ->
                assertTrue(expectedResult == sum)
            })
}
