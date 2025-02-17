package algorithms.recursion

import utils.printValue
import kotlin.math.pow

// https://leetcode.com/problems/minimum-non-zero-product-of-the-array-elements/description/

fun main() {
    val modulo = 10.0.pow(9).toLong() + 7
    findMinimumNonZeroProduct(3, modulo).printValue()
    findMinimumNonZeroProduct(54, modulo).printValue() // 247773741
}

private fun findMinimumNonZeroProduct(n: Int, modulo: Long): Int {
    val maxValue = 2.0.pow(n).toLong() - 1
    val mid = (maxValue - 1) / 2
    return findModuloProduct(maxValue, findPowerModulo(maxValue - 1, mid, modulo), modulo).toInt()
}

private fun findPowerModulo(base: Long, exponent: Long, modulo: Long): Long {
    if (exponent == 0L) {
        return 1L
    }
    val powHalf = findPowerModulo(base, exponent / 2, modulo)
    val result = findModuloProduct(powHalf, powHalf, modulo)
    return if (exponent % 2 == 1L) findModuloProduct(result, base, modulo) else result
}

private fun findModuloProduct(a: Long, b: Long, modulo: Long): Long {
    return (((a % modulo) * (b % modulo)) % modulo)
}