package com.appacoustic.android.dbcalculator.domain

class InputFormatter {

    companion object {
        const val DELIMITER = " + "
    }

    operator fun invoke(input: String): String {
        val inputTrimmed = input.trim()
        if (inputTrimmed.isBlank()) return ""
        if (inputTrimmed == ".") return "0."
        if (inputTrimmed == "+") return ""
        if (lastFilledCharacterIsAPlus(inputTrimmed)) return "$inputTrimmed "

        return input.split(DELIMITER)
            .map { it.ensureNumberHasNotACommaAtTheEnd() }
            .filter { it.isANumber() }
            .joinToString(DELIMITER) + DELIMITER
    }

    private fun lastFilledCharacterIsAPlus(input: String): Boolean =
        input.last().toString() == "+"

    private fun String.ensureNumberHasNotACommaAtTheEnd(): String =
        if (endsWith(".") || endsWith(",")) {
            substring(0, length - 1)
        } else {
            toString()
        }

    private fun String.isANumber() =
        matches("-?\\d+(\\.\\d+)?".toRegex())
}
