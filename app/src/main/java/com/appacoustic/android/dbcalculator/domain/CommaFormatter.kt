package com.appacoustic.android.dbcalculator.domain

class CommaFormatter {

    operator fun invoke(input: String): String {
        if (input.isBlank()) return ""

        val currentNumber = currentNumber(input)
        val restOfTheInput = input.substring(0, input.length - currentNumber.length)


        if (currentNumber == ".") return restOfTheInput + "0."

        val previousCharsOfTheCurrentNumber = currentNumber.substring(0, currentNumber.length - 1)
        if (previousCharsOfTheCurrentNumber.contains(".")) return restOfTheInput + previousCharsOfTheCurrentNumber



        return input
    }

    private fun currentNumber(input: String): String {
        return input.substringAfterLast(" ")
    }
}
