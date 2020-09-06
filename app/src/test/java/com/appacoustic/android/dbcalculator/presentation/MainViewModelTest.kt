package com.appacoustic.android.dbcalculator.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appacoustic.android.dbcalculator.domain.CalculateDBSum
import com.appacoustic.android.dbcalculator.domain.CommaFormatter
import com.appacoustic.android.dbcalculator.domain.InputFormatter
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val calculateDBSum = mockk<CalculateDBSum>()
    private val inputFormatter = mockk<InputFormatter>()
    private val commaFormatter = mockk<CommaFormatter>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(
            calculateDBSum = calculateDBSum,
            inputFormatter = inputFormatter,
            commaFormatter = commaFormatter
        )
    }

    @Test
    fun `when the user clicks on plus and the input text is empty, then the input value value remains equal`() {
        val initValue = viewModel.input.value

        viewModel.handlePlusClicked("")

        val endValue = viewModel.input.value
        assertTrue(initValue == endValue)
    }

    @Test
    fun `when the user clicks on plus and the input text ends by a plus, then the input value remains equal`() {
        val initValue = viewModel.input.value

        viewModel.handlePlusClicked("2 +")

        val endValue = viewModel.input.value
        assertTrue(initValue == endValue)
    }

    @Test
    fun `when the user clicks on plus and the input text ends by a plus and space, then the input value remains equal`() {
        val initValue = viewModel.input.value

        viewModel.handlePlusClicked("2 + ")

        val endValue = viewModel.input.value
        assertTrue(initValue == endValue)
    }

    @Test
    fun `when the user clicks on plus and the input text ends by a number, then the input adds a plus with spaces`() {
        viewModel.handlePlusClicked("2")

        val endValue = viewModel.input.value
        assertTrue("2 + " == endValue)
    }
}
