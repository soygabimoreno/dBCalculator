package com.appacoustic.android.dbcalculator.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appacoustic.android.dbcalculator.domain.*
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val calculateDBSum = mockk<CalculateDBSum>()
    private val commaFormatter = mockk<CommaFormatter>()
    private val addFormatter = mockk<AddFormatter>()
    private val minusFormatter = mockk<MinusFormatter>()
    private val backspaceFormatter = mockk<BackspaceFormatter>()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(
            addFormatter = addFormatter,
            minusFormatter = minusFormatter,
            backspaceFormatter = backspaceFormatter,
            commaFormatter = commaFormatter,
            calculateDBSum = calculateDBSum
        )
    }

    @Test
    fun `when the user clicks on plus and the input text is empty, then the input value value remains equal`() {
        val initValue = viewModel.input.value

        viewModel.handleAddClicked()

        val endValue = viewModel.input.value
        assertTrue(initValue == endValue)
    }

    @Test
    fun `when the user clicks on plus and the input text ends by a plus, then the input value remains equal`() {
        val initValue = viewModel.input.value

        viewModel.handleAddClicked()

        val endValue = viewModel.input.value
        assertTrue(initValue == endValue)
    }

    @Test
    fun `when the user clicks on plus and the input text ends by a plus and space, then the input value remains equal`() {
        val initValue = viewModel.input.value

        viewModel.handleAddClicked()

        val endValue = viewModel.input.value
        assertTrue(initValue == endValue)
    }

    @Test
    fun `when the user clicks on plus and the input text ends by a number, then the input adds a plus with spaces`() {
        viewModel.handleAddClicked()

        val endValue = viewModel.input.value
        assertTrue("2 + " == endValue)
    }

    @Test
    fun `when the user clicks on info, then navigate to the corresponding url`() {
        val initValue = viewModel.navigateToWeb.value
        viewModel.handleInfoClicked()

        val endValue = viewModel.navigateToWeb.value
        assertTrue(initValue != endValue)
    }
}
