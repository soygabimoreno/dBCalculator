package com.appacoustic.android.dbcalculator.domain

import arrow.core.Either
import com.appacoustic.android.dbcalculator.framework.isFilled
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.round

class CalculateDBSum {

    operator fun invoke(input: String): Either<Throwable, Float> {
        return if (input.isFilled()) {
            val inputFormatted = if (input.last().toString() == " ") {
                input.substring(0, input.length - 3)
            } else {
                input
            }

            val sources = inputFormatted.toSources()
            val signs = inputFormatted.toSigns()
            var linearSum = 0.0
            sources.forEachIndexed { index, source ->
                if (signs[index].toString() == "+") {
                    linearSum += 10.0.pow(source / 10)
                } else {
                    linearSum -= 10.0.pow(source / 10)
                }
            }
            val rawSum = 10 * log10(linearSum)
            val formattedSum = (round(rawSum * 10) / 10).toFloat()
            Either.right(formattedSum)
        } else {
            Either.left(InputNotFilledException)
        }
    }

    private fun String.toSources(): List<Double> =
        split(
            OperatorFormatter.Separator.PLUS.value,
            OperatorFormatter.Separator.MINUS.value
        ).map { it.toDouble() }

    private fun String.toSigns(): String =
        "+" + replace(" ", "").replace("[0-9]".toRegex(), "")
}

