package com.nesterov.veld.helpers

import kotlin.math.pow
import kotlin.math.sqrt

fun pythagorasTheorem(firstLeg: Double, secondLeg: Double): Double {
    return sqrt(x = firstLeg.pow(2) + secondLeg.pow(2))
}

fun pythagorasTheorem(firstLeg: Float, secondLeg: Float): Float {
    return sqrt(x = firstLeg.pow(2) + secondLeg.pow(2))
}