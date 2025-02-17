package algorithms.maths

import utils.printArray

// https://leetcode.com/problems/the-kth-factor-of-n/description/

fun main() {
    findFactors(7).printArray()
    findFactors(36).printArray()
    println(kthFactor(7, 2))
    println(findKthFactor(7, 2))
}

private fun kthFactor(n: Int, k: Int): Int {
    val factors = findFactors(n)
    if (k <= factors.size) {
        return factors[k - 1]
    }
    return -1
}

private fun findKthFactor(n: Int, k: Int): Int {
    var first = intArrayOf()
    var second = intArrayOf()
    var i = 1
    while (i * i <= n) {
        val quotient = n / i
        val reminder = n % i
        if (reminder == 0) {
            if (quotient == i) {
                first = first.plus(i)
            } else {
                first = first.plus(i)
                second = second.plus(quotient)
            }
            if (k == first.size) {
                return first[k - 1]
            }
        }
        i++
    }
    second.reverse()
    val result = first.plus(second)
    if (k <= result.size) {
        return result[k - 1]
    }
    return -1
}

private fun findFactors(n: Int): IntArray {
    var first = intArrayOf()
    var second = intArrayOf()
    var i = 1
    while (i * i <= n) {
        val quotient = n / i
        val reminder = n % i
        if (reminder == 0) {
            if (quotient == i) {
                first = first.plus(i)
            } else {
                first = first.plus(i)
                second = second.plus(quotient)
            }
        }
        i++
    }
    second.reverse()
    return first.plus(second)
}