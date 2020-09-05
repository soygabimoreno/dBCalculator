package com.appacoustic.android.dbcalculator.domain

import com.appacoustic.android.dbcalculator.framework.isFilled

class CommaRemover {

    companion object {
        const val DELIMITER = " + "
    }

    operator fun invoke(input: String): String {
        val inputTrimmed = input.trim()
        if (inputTrimmed.isFilled() && lastFilledCharacterIsNotAPlus(inputTrimmed)) {
            return input.split(DELIMITER)
                .map { it.ensureNumberHasNotACommaAtTheEnd() }
                .filter { it.isANumber() }
                .joinToString(DELIMITER)
        }
        return ""
    }

    private fun lastFilledCharacterIsNotAPlus(input: String): Boolean =
        input.last().toString() != "+"

    private fun String.ensureNumberHasNotACommaAtTheEnd(): String =
        if (endsWith(".") || endsWith(",")) {
            substring(0, length - 1)
        } else {
            toString()
        }

    private fun String.isANumber() =
        matches("-?\\d+(\\.\\d+)?".toRegex())
}
