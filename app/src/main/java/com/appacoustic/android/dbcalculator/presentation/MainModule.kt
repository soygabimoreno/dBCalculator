package com.appacoustic.android.dbcalculator.presentation

import com.appacoustic.android.dbcalculator.domain.CalculateDBSum
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single { CalculateDBSum() }
    scope(named<MainActivity>()) {
        viewModel {
            MainViewModel(
                calculateDBSum = get()
            )
        }
    }
}
