package com.appacoustic.android.dbcalculator.presentation

import com.appacoustic.android.dbcalculator.domain.CalculateDBSum
import com.appacoustic.android.dbcalculator.domain.CommaFormatter
import com.appacoustic.android.dbcalculator.domain.InputFormatter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single { InputFormatter() }
    single { CalculateDBSum() }
    single { CommaFormatter() }
    scope(named<MainActivity>()) {
        viewModel {
            MainViewModel(
                calculateDBSum = get(),
                inputFormatter = get(),
                commaFormatter = get()
            )
        }
    }
}
