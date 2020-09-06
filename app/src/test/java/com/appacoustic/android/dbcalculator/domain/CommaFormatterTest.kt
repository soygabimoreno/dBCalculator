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
    fun `if input is empty, then return an empty string`() =
        assertTrue("" == commaFormatter(""))

    @Test
    fun `if input is a comma, then add a zero at start of the string`() =
        assertTrue("0." == commaFormatter("."))

    @Test
    fun `if input is well-formed, then return the same string`() =
        assertTrue("0." == commaFormatter("0."))

    @Test
    fun `if input had a previous comma just in the previous char, then remove the new one`() =
        assertTrue("0." == commaFormatter("0.."))

    @Test
    fun `if input had a previous comma in the middle of the string, then remove the new one`() =
        assertTrue("0.7" == commaFormatter("0.7."))

    @Test
    fun `if input had a previous comma in other previous number but the current one has not, then return the same string`() =
        assertTrue("1.5 + 2." == commaFormatter("1.5 + 2."))
}
