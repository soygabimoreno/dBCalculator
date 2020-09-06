package com.appacoustic.android.dbcalculator.presentation

import com.appacoustic.android.dbcalculator.domain.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single { InputFormatter() }
    single { CalculateDBSum() }
    single { CommaFormatter() }
    single { AddFormatter() }
    single { MinusFormatter() }
    scope(named<MainActivity>()) {
        viewModel {
            MainViewModel(
                calculateDBSum = get(),
                inputFormatter = get(),
                commaFormatter = get(),
                addFormatter = get(),
                minusFormatter = get()
            )
        }
    }
}
