package com.appacoustic.android.dbcalculator.domain

import arrow.core.Either
import com.appacoustic.android.dbcalculator.framework.isFilled
import kotlin.math.log10
import kotlin.math.round

class CalculateDBSum {

    object InputNotFilledException : Exception("InputNotFilledException")

    operator fun invoke(input: CharSequence?): Either<Throwable, Float> {
        return if (input.isFilled()) {
            val rawResult = 20 * log10(input.toString().toDouble())
            val formattedResult = (round(rawResult * 10) / 10).toFloat()
            Either.right(formattedResult)
        } else {
            Either.left(InputNotFilledException)
        }
    }
}

/*
    /**
     * @param sources - Sound sources.
     * @return the logarithmic sum of the input sources.
     */
    public static float addDB(float[] sources) {
        float out = 0;
        for (float source : sources) {
            out += Math.pow(10, source / 10); // Linear sum
        }
        out = (float) (10 * Math.log10(out)); // Convert to dB
        out = (float) (Math.rint(out * 10) / 10); // Round to 1 decimal
        return out;
    }
 */
