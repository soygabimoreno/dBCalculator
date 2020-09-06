package com.appacoustic.android.dbcalculator.presentation

import com.appacoustic.android.dbcalculator.domain.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single { AddFormatter() }
    single { MinusFormatter() }
    single { CommaFormatter() }
    single { BackspaceFormatter() }
    single { CalculateDBSum() }
    scope(named<MainActivity>()) {
        viewModel {
            MainViewModel(
                addFormatter = get(),
                minusFormatter = get(),
                commaFormatter = get(),
                backspaceFormatter = get(),
                calculateDBSum = get()
            )
        }
    }
}
