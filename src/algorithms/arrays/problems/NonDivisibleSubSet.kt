package algorithms.arrays.problems

import utils.printValue

// https://www.hackerrank.com/challenges/non-divisible-subset/problem

fun main() {
    findNonDivisibleSubSet(intArrayOf(1, 2, 3, 4, 5, 6), 3).printValue()
    findNonDivisibleSubSet(intArrayOf(19, 10, 12, 10, 24, 25, 22), 1).printValue()
    findNonDivisibleSubSet(intArrayOf(12 ,6 ,1, 9, 13, 15, 10, 21, 14, 32, 5, 8, 23, 19), 6).printValue()
}

private fun findNonDivisibleSubSet(array: IntArray, k: Int): Int {
    if (k == 1) {
        return 1
    }
    val reminders = IntArray(k) { 0 }
    array.forEach {
        val reminder = it % k
        reminders[reminder] = reminders[reminder] + 1
    }
    var maxCount = reminders[0].coerceAtMost(1)
    var startIndex = 1
    var endIndex = reminders.size - 1
    while (startIndex <= endIndex) {
        maxCount += if (startIndex == endIndex) {
            reminders[startIndex].coerceAtMost(1)
        } else {
            reminders[startIndex].coerceAtLeast(reminders[endIndex])
        }
        startIndex += 1
        endIndex -= 1
    }
    return maxCount
}

