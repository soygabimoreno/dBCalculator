package com.appacoustic.android.dbcalculator.domain

import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class CalculateDBSumTest {

    private val commaRemover = CommaRemover()
    private lateinit var calculateDBSum: CalculateDBSum

    @Before
    fun setUp() {
        calculateDBSum = CalculateDBSum(commaRemover)
    }

    @Test
    fun `if input is a well-formed sum, then returns the correct value`() {
        assertCalculation("3 + 3", 6f)
    }

    @Test
    fun `if input ends with the plus with spaces delimiter, then returns the correct value`() {
        assertCalculation("3 + 3 + ", 6f)
    }

    @Test
    fun `if input has an uncompleted decimal number, then returns the correct value`() {
        assertCalculation("3. + 3 + ", 6f)
    }

    private fun assertCalculation(input: String, expectedResult: Float) =
        calculateDBSum(input)
            .fold({
                fail("")
            }, { sum ->
                assertTrue(expectedResult == sum)
            })
}
