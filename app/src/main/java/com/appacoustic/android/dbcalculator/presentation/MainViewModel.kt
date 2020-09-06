package com.appacoustic.android.dbcalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appacoustic.android.dbcalculator.domain.*

class MainViewModel(
    private val calculateDBSum: CalculateDBSum,
    val inputFormatter: InputFormatter,
    val commaFormatter: CommaFormatter,
    val addFormatter: AddFormatter,
    val minusFormatter: MinusFormatter
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
//        calculateDBSum(input)
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
        _input.value = commaFormatter(input)
    }

    fun handleClearClicked() {
        _input.value = ""
    }

    fun handleBackspaceClicked() {
        val input = _input.value!!
        val length = input.length
        if (length > 0) {
            _input.value = input.substring(0, length - 1)
        }
    }

    fun handleMinusClicked() {
        val input = _input.value!!
        _input.value = minusFormatter(input)
    }

    fun handleAddClicked() {
        val input = _input.value!!
        _input.value = addFormatter(input)
    }
}
