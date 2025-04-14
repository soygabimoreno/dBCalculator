package com.appacoustic.android.dbcalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appacoustic.android.dbcalculator.domain.*
import com.appacoustic.android.dbcalculator.framework.event.Event

class MainViewModel(
    val addFormatter: AddFormatter,
    val minusFormatter: MinusFormatter,
    val backspaceFormatter: BackspaceFormatter,
    val commaFormatter: CommaFormatter,
    private val calculateDBSum: CalculateDBSum
) : ViewModel() {

    private var _sum = MutableLiveData<Float?>()
    val sum: LiveData<Float?> = _sum

    private var _input = MutableLiveData<String>()
    val input: LiveData<String> = _input

    private val _navigateToWeb = MutableLiveData<Event<String>>()
    val navigateToWeb: LiveData<Event<String>> = _navigateToWeb

    init {
        _sum.value = null
        _input.value = ""
    }

    fun handleNumberClicked(input: String) {
        _input.value += input
    }

    fun handleCommaClicked() {
        val input = _input.value!!
        _input.value = commaFormatter(input)
    }

    fun handleClearClicked() {
        _input.value = ""
    }

    fun handleBackspaceClicked() {
        val input = _input.value!!
        _input.value = backspaceFormatter(input)
    }

    fun handleMinusClicked() {
        val input = _input.value!!
        _input.value = minusFormatter(input)
    }

    fun handleAddClicked() {
        val input = _input.value!!
        _input.value = addFormatter(input)
    }

    fun calculateSum(input: String) {
        calculateDBSum(input)
            .fold({
                _sum.value = null
            }, { sum ->
                _sum.value = sum
            })
    }

    fun handleInfoClicked() {
        _navigateToWeb.value = Event("http://appacoustic.com")
    }
}
