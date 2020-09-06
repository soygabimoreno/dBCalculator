package com.appacoustic.android.dbcalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appacoustic.android.dbcalculator.domain.CalculateDBSum
import com.appacoustic.android.dbcalculator.domain.CommaFormatter
import com.appacoustic.android.dbcalculator.domain.InputFormatter

class MainViewModel(
    private val calculateDBSum: CalculateDBSum,
    val inputFormatter: InputFormatter,
    val commaFormatter: CommaFormatter
) : ViewModel() {

    private var _sum = MutableLiveData<Float>()
    val sum: LiveData<Float> = _sum

    private var _input = MutableLiveData<String>()
    val input: LiveData<String> = _input

    init {
        _sum.value = null
        _input.value = ""
    }

    fun handleSourcesChanged(input: String) {
        val inputFormatted = ensureASingleComma(input)
        _input.value = inputFormatted

//        calculateDBSum(inputFormatted)
//            .fold({
//                _sum.value = null
//            }, { sum ->
//                _sum.value = sum
//            })
    }

    fun handleNumberClicked(input: String) {
        _input.value = _input.value + input
    }

    fun handleCommaClicked() {
        val input = _input.value!!
        _input.value = commaFormatter("$input.")
    }

    fun handleBackspaceClicked() {
        val input = _input.value!!
        val length = input.length
        if (length > 0) {
            _input.value = input.substring(0, length - 1)
        }
    }

    fun handlePlusClicked(input: String) {
        val inputFormatted = inputFormatter(input)
        _input.value = inputFormatted
    }

    private fun ensureASingleComma(input: String): String =
        if (input.length > 1) {
            input[input.length - 2].toString() == "."
            input.substring(0, input.length - 2)
        } else {
            input
        }
}
