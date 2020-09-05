package com.appacoustic.android.dbcalculator.domain

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CommaRemoverTest {

    private lateinit var commaRemover: CommaRemover

    @Before
    fun setUp() {
        commaRemover = CommaRemover()
    }

    @Test
    fun `if input is a well-formed sum, then returns the correct value`() {
        assertTrue("2" == commaRemover("2"))
    }

    @Test
    fun `if input is empty, then returns also an empty string`() {
        assertTrue("" == commaRemover(""))
    }

    @Test
    fun `if input is a single point, then returns an empty string`() {
        assertTrue("" == commaRemover("."))
    }
}
