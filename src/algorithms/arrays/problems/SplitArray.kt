package algorithms.arrays.problems

import utils.printValue

// https://leetcode.com/problems/split-array-largest-sum/description/

fun main() {
    val array = arrayOf(7, 2, 5, 10, 8).toIntArray()
    findSplitArray(array, 2).printValue()
    findSplitArray(array, 3).printValue()
}

private fun findSplitArray(array: IntArray, num: Int): Int {
    var maxNumber = Int.MIN_VALUE
    var totalSum = 0
    array.forEach {
        maxNumber = it.coerceAtLeast(maxNumber)
        totalSum += it
    }
    while (maxNumber < totalSum) {
        var pieces = 1
        val midSum = maxNumber + (totalSum - maxNumber) / 2
        var currentSum = 0
        array.forEach {
            currentSum += it
            if (currentSum > midSum) {
                currentSum = it
                pieces += 1
            }
        }
        if (currentSum == array[array.size - 1]) {
            pieces += 1
        }
        if (pieces <= num) {
            totalSum = midSum
        } else {
            maxNumber = midSum + 1
        }
    }
    return maxNumber
}

