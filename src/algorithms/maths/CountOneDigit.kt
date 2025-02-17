package algorithms.maths

import kotlin.math.pow

// https://leetcode.com/problems/number-of-digit-one/description/

fun main() {

}

private fun countOneDigits(n: Int): Int {
    var num = n
    var digits = 0
    var iterations = 1
    while (num > 0) {
        val reminder = num % 10
        digits += if (iterations > 1) {
            reminder * ((iterations) * (10.0.pow(iterations - 1).toInt()) + 1)
        } else {
            reminder * (iterations) * (10.0.pow(iterations - 1).toInt())
        }
        iterations += 1
        num /= 10
    }
    return digits
}