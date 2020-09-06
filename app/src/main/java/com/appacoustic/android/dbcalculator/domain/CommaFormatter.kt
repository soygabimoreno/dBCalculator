package com.appacoustic.android.dbcalculator.domain

class CommaFormatter {

    operator fun invoke(input: String): String {
        if (input.isBlank()) return "0."

        val currentNumber = currentNumber(input)
        val restOfTheInput = input.substring(0, input.length - currentNumber.length)

        if (currentNumber.isBlank()) return restOfTheInput + "0."

        if (currentNumber.isNotBlank()) {
            if (currentNumber.last().toString() == ".") {
                return input
            }

            val previousCharsOfTheCurrentNumber = currentNumber.substring(0, currentNumber.length - 1)
            if (previousCharsOfTheCurrentNumber.contains(".")) return restOfTheInput + previousCharsOfTheCurrentNumber
        }

        return "$input."
    }

    private fun currentNumber(input: String): String {
        return input.substringAfterLast(" ")
    }
}
