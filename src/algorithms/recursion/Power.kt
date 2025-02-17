package algorithms.recursion

import utils.printValue

// https://leetcode.com/problems/powx-n/description/

fun main() {
    findPow(2.0, 10).printValue()
    findPow(2.1, 3).printValue()
    findPow(1.0 / 2, 2).printValue()
}

private fun findPow(base: Double, exponent: Int): Double {
    if (exponent == 0) {
        return 1.0
    }
    val half = exponent / 2
    val halfPow = findPow(base, half)
    return if(exponent % 2 == 0) halfPow * halfPow else halfPow * halfPow * base
}