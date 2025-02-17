package algorithms.recursion

import utils.printValue
import kotlin.math.pow

// https://leetcode.com/problems/k-th-symbol-in-grammar/description/

fun main() {
    findKthSymbol(1, 1).printValue()
    findKthSymbol(2, 1).printValue()
    findKthSymbol(2, 2).printValue()

    println()

    findKthSymbol2(1, 1).printValue()
    findKthSymbol2(2, 1).printValue()
    findKthSymbol2(2, 2).printValue()

    println()

    findKthSymbol3(1, 1).printValue()
    findKthSymbol3(2, 1).printValue()
    findKthSymbol3(2, 2).printValue()
}

private fun findKthSymbol3(n: Int, k: Int): Int {
    var areValuesSame = false
    var num = n
    var target = k
    while (num > 0) {
        val mid = 2.0.pow(num - 2).toInt()
        if (target > mid) {
            target -= mid
            areValuesSame = !areValuesSame
        }
        num -= 1
    }
    return if(areValuesSame) 0 else 1
}

private fun findKthSymbol2(n: Int, k: Int): String {
    if (n == 1) {
        return "0"
    }
    val mid = 2.0.pow(n - 2).toInt()
    return if (k <= mid) {
        findKthSymbol2(n - 1, k)
    } else {
        invert(findKthSymbol2(n - 1, k - mid))
    }
}

private fun findKthSymbol(n: Int, k: Int): Char {
    return findNthBinaryString(n)[k - 1]
}

private fun findNthBinaryString(n: Int): String {
    if (n == 1) {
        return "0"
    }
    val binaryString = findNthBinaryString(n - 1)
    return binaryString + invert(binaryString)
}

private fun invert(string: String): String {
    return string.map {
        when(it) {
            '0' -> '1'
            '1' -> '0'
            else -> it
        }
    }.joinToString("")
}