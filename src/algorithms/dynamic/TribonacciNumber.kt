package algorithms.dynamic

import utils.printList

// https://leetcode.com/problems/n-th-tribonacci-number/description/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    findTribonacciNumber(5).printList()
    findTribonacciNumber(10).printList()
}

private fun findTribonacciNumber(n: Int): List<Int> {
    return listOf(
        findTribonacciNumberMemoization(n),
        findTribonacciNumberMemoization(n, IntArray(n + 1) { -1 }),
        findTribonacciNumberTabulation(n)
    )
}

private fun findTribonacciNumberMemoization(n: Int): Int {
    if (n <= 1) {
        return n
    }
    if (n == 2) {
        return 1
    }
    return findTribonacciNumberMemoization(n - 1) + findTribonacciNumberMemoization(n - 2) + findTribonacciNumberMemoization(n - 3)
}

private fun findTribonacciNumberMemoization(n: Int, resultArray: IntArray): Int {
    if (n <= 1) {
        return n
    }
    if (n == 2) {
        return 1
    }
    if (resultArray[n] != -1) {
        return resultArray[n]
    }
    resultArray[n] = findTribonacciNumberMemoization(n - 1, resultArray) + findTribonacciNumberMemoization(n - 2, resultArray) + findTribonacciNumberMemoization(n - 3, resultArray)
    return resultArray[n]
}

private fun findTribonacciNumberTabulation(n: Int): Int {
    if (n <= 1) {
        return n
    }
    if (n == 2) {
        return 1
    }
    var thirdPrevious = 0
    var secondPrevious = 1
    var firstPrevious = 1
    (3 until n + 1).forEach { i ->
        val temp = thirdPrevious + secondPrevious + firstPrevious
        thirdPrevious = secondPrevious
        secondPrevious = firstPrevious
        firstPrevious = temp
    }
    return firstPrevious
}