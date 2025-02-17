package algorithms.linkedlists.single

import utils.printValue

// https://leetcode.com/problems/happy-number/submissions/

fun main() {
    isHappy(19).printValue()
    isHappy(12).printValue()
}

private fun isHappy(n: Int): Boolean {
    if (n == 1) {
        return true
    }
    var slowPointer = findSquare(n)
    var fastPointer = findSquare(findSquare(n))
    while(slowPointer != 1 || fastPointer != 1) {
        if (slowPointer == fastPointer) {
            return false
        }
        slowPointer = findSquare(slowPointer)
        fastPointer = findSquare(findSquare(fastPointer))
    }
    return true
}

private fun findSquare(n: Int): Int {
    var num = n
    var sum = 0
    while(num > 0) {
        val reminder = num % 10
        num /= 10
        sum += reminder * reminder
    }
    return sum
}