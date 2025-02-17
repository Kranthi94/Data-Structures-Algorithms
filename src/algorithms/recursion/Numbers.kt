package algorithms.recursion

import kotlin.math.log10
import kotlin.math.pow

fun main() {
    printNumberFromNto1(5)
    println()
    printNumbersFrom1toN(5)
    println()
    printNumbersReverse(5)
    println()
    println(getNumbersSum(5))
    println(getNumbersProduct(5))
    println(digitsSum(371093))
    println(digitsProduct(100))
    println(reverseNumber1(1234, log10(1234.0).toInt() + 1))
    println(reverseNumber2(123451000, 0))
    println(checkForPalindrome(101001))
}

private fun printNumberFromNto1(n: Int) {
    if (n == 0) {
        return
    }
    print("$n ")
    printNumberFromNto1(n- 1)
}

private fun printNumbersFrom1toN(n: Int) {
    if(n == 0) {
        return
    }
    printNumbersFrom1toN(n - 1)
    print("$n ")
}

private fun printNumbersReverse(n: Int) {
    if (n == 0) {
        return
    }
    print("$n ")
    printNumbersReverse(n - 1)
    print("$n ")
}

private fun getNumbersSum(n: Int): Int {
    if (n == 1) {
        return n
    }
    return n + getNumbersSum(n - 1)
}

private fun getNumbersProduct(n: Int): Int {
    if (n == 1) {
        return n
    }
    return n * getNumbersProduct(n - 1)
}


private fun digitsSum(n: Int): Int {
    if (n % 10 == n) {
        return n
    }
    return n % 10 + digitsSum(n / 10)
}

private fun digitsProduct(n: Int): Int {
    val reminder = n % 10
    if (reminder == 0) {
        return 0
    }
    if (reminder == n) {
        return n
    }
    return reminder * digitsProduct(n / 10)
}

private fun reverseNumber1(n: Int, digitsCount: Int): Int {
    val reminder = n % 10
    if (reminder == n) {
        return n
    }
    return reminder * 10.0.pow(digitsCount - 1).toInt() + reverseNumber1(n / 10, digitsCount - 1)
}

private fun reverseNumber2(n: Int, sum: Int): Int {
    val reminder = n % 10
    if (reminder == n) {
        return sum * 10 + reminder
    }
    val updatedSum = sum * 10 + reminder
    return reverseNumber2(n / 10, updatedSum)
}

private fun checkForPalindrome(n: Int): Boolean {
    return reverseNumber2(n, 0) == n
}


