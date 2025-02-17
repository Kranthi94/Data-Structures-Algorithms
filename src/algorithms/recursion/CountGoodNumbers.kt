package algorithms.recursion

import utils.printValue
import kotlin.math.pow

// https://leetcode.com/problems/count-good-numbers/

fun main() {
    val modulo = 10.0.pow(9).toLong() + 7
    countGoodNumbers1(1, modulo).printValue()
    countGoodNumbers1(4, modulo).printValue()
    countGoodNumbers1(10, modulo).printValue()
    countGoodNumbers1(50, modulo).printValue()

    println()

    countGoodNumbers2(1, modulo).printValue()
    countGoodNumbers2(4, modulo).printValue()
    countGoodNumbers2(10, modulo).printValue()
    countGoodNumbers2(50, modulo).printValue()

//    5
//    400
//    3200000
//    564908303
}

private fun countGoodNumbers1(n: Long, modulo: Long): Long {
    if (n == 1L) {
        return 5
    }
    val reminder = n % 2
    return if (reminder == 1L) {
        findProductModulo(5, countGoodNumbers1(n - 1, modulo), modulo)
    } else {
        findProductModulo(4, countGoodNumbers1(n - 1, modulo), modulo)
    }
}

private fun countGoodNumbers2(n: Long, modulo: Long): Int {
    val oddCount = n / 2
    val evenCount = n - oddCount
    return (findPowerModulo(5, evenCount, modulo) * findPowerModulo(4, oddCount, modulo) % modulo).toInt()
}

private fun findProductModulo(a: Long, b: Long, modulo: Long): Long {
    return (((a % modulo) * (b % modulo)) % modulo)
}

private fun findPowerModulo(base: Long, exponent: Long, modulo: Long): Long {
    if (exponent == 0L) {
        return 1L
    }
    val powHalf = findPowerModulo(base, exponent / 2, modulo)
    val result = (powHalf * powHalf) % modulo
    return if (exponent % 2 == 1L) findProductModulo(result, base, modulo) else result
}