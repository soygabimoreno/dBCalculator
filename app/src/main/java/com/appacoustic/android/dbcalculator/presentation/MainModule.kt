package com.appacoustic.android.dbcalculator.presentation

import com.appacoustic.android.dbcalculator.domain.CalculateDBSum
import com.appacoustic.android.dbcalculator.domain.CommaRemover
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single { CommaRemover() }
    single { CalculateDBSum(commaRemover = get()) }
    scope(named<MainActivity>()) {
        viewModel {
            MainViewModel(
                calculateDBSum = get(),
                commaRemover = get()
            )
        }
    }
}
