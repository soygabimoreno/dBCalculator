package com.appacoustic.android.dbcalculator.domain

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CommaFormatterTest {

    private lateinit var commaFormatter: CommaFormatter

    @Before
    fun setUp() {
        commaFormatter = CommaFormatter()
    }

    @Test
    fun `if input is empty, then return a zero with comma`() =
        assertTrue("0." == commaFormatter(""))

    @Test
    fun `if input is well-formed, then return the same string with a comma`() =
        assertTrue("0." == commaFormatter("0"))

    @Test
    fun `if input had a previous comma just in the previous char, then return the same string`() =
        assertTrue("0." == commaFormatter("0."))

    @Test
    fun `if input had a previous comma in the middle of the string, then return the same string with a comma`() =
        assertTrue("0.7 + 7." == commaFormatter("0.7 + 7"))

    @Test
    fun `if input had a previous comma in other previous number but the current one has not, then return the same string with a comma`() =
        assertTrue("1.5 + 2." == commaFormatter("1.5 + 2"))
}
