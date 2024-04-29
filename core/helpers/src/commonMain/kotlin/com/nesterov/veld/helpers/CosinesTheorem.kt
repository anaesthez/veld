package com.nesterov.veld.helpers

import kotlin.math.pow

fun Float.cosinesTheorem(x: Float, y: Float): Float {
    return (x.pow(2) + y.pow(2) - this.pow(2)) / (2 * x * y)
}