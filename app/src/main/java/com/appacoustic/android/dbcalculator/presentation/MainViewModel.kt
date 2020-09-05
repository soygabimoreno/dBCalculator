package com.appacoustic.android.dbcalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appacoustic.android.dbcalculator.domain.CalculateDBSum

class MainViewModel(
    private val calculateDBSum: CalculateDBSum
) : ViewModel() {

    private var _sum = MutableLiveData<Float>()
    val sum: LiveData<Float> = _sum

    init {
        _sum.value = null
    }

    fun handleSourcesChanged(input: CharSequence?) {
        calculateDBSum(input)
            .fold({
                _sum.value = null
            }, { sum ->
                _sum.value = sum
            })
    }
}
