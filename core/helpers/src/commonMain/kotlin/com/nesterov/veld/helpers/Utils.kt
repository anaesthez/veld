package com.nesterov.veld.helpers

import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

fun Float.toRadian(): Float = (this / 180 * PI).toFloat()

fun Float.toDegree(): Float = (this * 180 / PI).toFloat()

fun Float.toSin(): Float = sqrt(1 - this.pow(2))

inline fun Int?.orZero(): Int = this ?: 0
inline fun Float?.orZero(): Float = this ?: 0f

inline fun Boolean?.orFalse(): Boolean = this ?: false
