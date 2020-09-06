package com.appacoustic.android.dbcalculator.domain

abstract class OperatorFormatter(private val separator: Separator) {

    enum class Separator(val value: String) {
        PLUS(" + "),
        MINUS(" - ")
    }

    operator fun invoke(input: String): String {
        if (input.isBlank()) return ""

        val currentNumber = currentNumber(input)
        val restOfTheInput = input.substring(0, input.length - currentNumber.length)

        val separator = separator.value
        if (currentNumber == ".") return restOfTheInput + "0." + separator
        if (currentNumber.isNotBlank()) {
            return if (currentNumber.last().toString() != ".") {
                input + separator
            } else {
                restOfTheInput + currentNumber.substring(0, currentNumber.length - 1) + separator
            }
        }

        if (restOfTheInput.isNotBlank() && restOfTheInput.last().toString() == " ") return input

        return input + separator
    }

    private fun currentNumber(input: String): String {
        return input.substringAfterLast(" ")
    }
}

class AddFormatter : OperatorFormatter(Separator.PLUS)
class MinusFormatter : OperatorFormatter(Separator.MINUS)
