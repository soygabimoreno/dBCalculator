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
        assertCalculation(6f, "3 + 3")
    }

    @Test
    fun `if input ends with the plus with spaces delimiter, then returns the correct value`() {
        assertCalculation(6f, "3 + 3 + ")
    }

    @Test
    fun `if input has an uncompleted decimal number, then returns the correct value`() {
        assertCalculation(7f, "3 + 3 + 0")
    }

    private fun assertCalculation(expectedResult: Float, input: String) =
        calculateDBSum(input)
            .fold({
                fail("")
            }, { sum ->
                assertTrue(expectedResult == sum)
            })
}
