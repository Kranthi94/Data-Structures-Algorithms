package algorithms.recursion

import utils.printValue

// https://www.hackerrank.com/challenges/recursive-digit-sum/problem?isFullScreen=true

fun main() {
    superDigit("9875", 4).printValue()
    superDigit("148", 3).printValue()
    superDigit("123", 3).printValue()
}

private fun superDigit(n: String, k: Int): Int {
    var index = n.length - 1
    var reminder = 0
    while (index >= 0) {
        val digit = n[index].digitToInt()
        reminder = (reminder + digit) % 9
        index -= 1
    }
    val result = findProductModulo(k, reminder, 9)
    return if (result == 0) 9 else result
}

private fun findProductModulo(a: Int, b: Int, modulo: Int): Int {
    return (((a % modulo) * (b % modulo)) % modulo)
}