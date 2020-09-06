package com.appacoustic.android.dbcalculator.domain

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class InputFormatterTest {

    private lateinit var inputFormatter: InputFormatter

    @Before
    fun setUp() {
        inputFormatter = InputFormatter()
    }

    @Test
    fun `if input is a well-formed input, then returns the correct value`() =
        assertTrue("2 + " == inputFormatter("2"))

    @Test
    fun `if input is empty, then returns also an empty string`() =
        assertTrue("" == inputFormatter(""))

    @Test
    fun `if input is a single point, then put a zero at start`() =
        assertTrue("0." == inputFormatter("."))

    @Test
    fun `if input is a single plus, then returns a empty string`() =
        assertTrue("" == inputFormatter("+"))

    @Test
    fun `if input has a plus at the end, return the same string`() {
        assertTrue("2 +" == inputFormatter("2 +"))
    }
}
