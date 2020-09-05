package com.appacoustic.android.dbcalculator.domain

import arrow.core.Either
import com.appacoustic.android.dbcalculator.framework.isFilled
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.round

class CalculateDBSum(
    val commaRemover: CommaRemover
) {

    object InputNotFilledException : Exception("InputNotFilledException")

    operator fun invoke(input: String): Either<Throwable, Float> {
        return if (input.isFilled()) {
            val inputFormatted = commaRemover(input)
            if (inputFormatted.isFilled()) {
                val sources = inputFormatted.toSources()
                var linearSum = 0.0
                sources.forEach { source ->
                    linearSum += 10.0.pow(source / 10)
                }
                val rawSum = 10 * log10(linearSum)
                val formattedSum = (round(rawSum * 10) / 10).toFloat()
                Either.right(formattedSum)
            } else {
                Either.left(InputNotFilledException)
            }
        } else {
            Either.left(InputNotFilledException)
        }
    }

    private fun String.toSources(): List<Double> =
        split(CommaRemover.DELIMITER)
            .map { it.toDouble() }
}
