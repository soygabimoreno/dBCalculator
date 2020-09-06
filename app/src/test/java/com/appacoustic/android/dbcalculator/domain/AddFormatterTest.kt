package com.appacoustic.android.dbcalculator.domain

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AddFormatterTest {

    private lateinit var addFormatter: AddFormatter

    @Before
    fun setUp() {
        addFormatter = AddFormatter()
    }

    @Test
    fun `if input is empty, then return an empty string`() =
        assertTrue("" == addFormatter(""))

    @Test
    fun `if input is just a comma, then add a zero at start of the string and the plus separator`() =
        assertTrue("0.${AddFormatter.PLUS_SEPARATOR}" == addFormatter("."))

    @Test
    fun `if input has the last number with just a comma, then remove it and the plus separator`() =
        assertTrue("1${AddFormatter.PLUS_SEPARATOR}" == addFormatter("1."))

    @Test
    fun `if input is well-formed, then return the same string with the plus separator`() =
        assertTrue("0.5${AddFormatter.PLUS_SEPARATOR}" == addFormatter("0.5"))

    @Test
    fun `if input already has the plus separator at the end, then return the same string`() =
        assertTrue("0.5${AddFormatter.PLUS_SEPARATOR}" == addFormatter("0.5 + "))

    @Test
    fun `if input has the plus separator in the middl, then return the same string with the plus separator`() =
        assertTrue("1 + 1${AddFormatter.PLUS_SEPARATOR}" == addFormatter("1 + 1"))
}
