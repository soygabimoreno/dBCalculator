package com.appacoustic.android.dbcalculator.domain

class BackspaceFormatter {

    operator fun invoke(input: String): String {
        if (input.isBlank()) return ""

        val length = input.length
        return if (input.last().toString() == " ") {
            input.substring(0, length - 3)
        } else {
            input.substring(0, length - 1)
        }
    }
}
