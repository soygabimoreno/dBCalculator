package com.appacoustic.android.dbcalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appacoustic.android.dbcalculator.domain.CalculateDBSum
import com.appacoustic.android.dbcalculator.domain.CommaRemover
import com.appacoustic.android.dbcalculator.framework.isFilled

class MainViewModel(
    private val calculateDBSum: CalculateDBSum,
    val commaRemover: CommaRemover
) : ViewModel() {

    private var _sum = MutableLiveData<Float>()
    val sum: LiveData<Float> = _sum

    private var _input = MutableLiveData<String>()
    val input: LiveData<String> = _input

    private var _inputIndex = MutableLiveData<Int>()
    val inputIndex: LiveData<Int> = _inputIndex

    init {
        _sum.value = null
    }

    fun handleSourcesChanged(input: String) {
        calculateDBSum(input)
            .fold({
                _sum.value = null
            }, { sum ->
                _sum.value = sum
            })
    }

    fun handlePlusClicked(input: String) {
        val inputTrimmed = input.trim()
        if (inputTrimmed.isFilled() && lastFilledCharacterIsNotAPlus(inputTrimmed)) {
            val inputFormatted = commaRemover(inputTrimmed)
            _input.value = "$inputFormatted + "
            _inputIndex.value = inputFormatted!!.length + 3
        }
    }

    private fun lastFilledCharacterIsNotAPlus(input: String): Boolean =
        input.last().toString() != "+"
}
