package com.appacoustic.android.dbcalculator.domain

class AddFormatter {

    companion object {
        const val PLUS_SEPARATOR = " + "
    }

    operator fun invoke(input: String): String {
        if (input.isBlank()) return ""

        val currentNumber = currentNumber(input)
        val restOfTheInput = input.substring(0, input.length - currentNumber.length)

        if (currentNumber == ".") return restOfTheInput + "0." + PLUS_SEPARATOR
        if (currentNumber.isNotBlank()) {
            return if (currentNumber.last().toString() != ".") {
                input + PLUS_SEPARATOR
            } else {
                restOfTheInput + currentNumber.substring(0, currentNumber.length - 1) + PLUS_SEPARATOR
            }
        }

        if (restOfTheInput.isNotBlank() && restOfTheInput.last().toString() == " ") return input

        return input + PLUS_SEPARATOR
    }

    private fun currentNumber(input: String): String {
        return input.substringAfterLast(" ")
    }
}
