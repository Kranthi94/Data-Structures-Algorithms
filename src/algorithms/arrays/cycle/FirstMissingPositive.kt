package algorithms.arrays.cycle

import utils.swap

// https://leetcode.com/problems/first-missing-positive/

fun main() {
    println(findFirstMissingPositive(intArrayOf(1, 2, 0)))
    println(findFirstMissingPositive(intArrayOf(3, 4, -1, 1)))
    println(findFirstMissingPositive(intArrayOf(7, 8, 9, 11, 12)))
}

private fun findFirstMissingPositive(array: IntArray): Int {
    var i = 0
    while(i < array.size) {
        val index = array[i] - 1
        if (array[i] > 0 && array[i] <= array.size && array[i] != array[index]) {
            array.swap(i, index)
        } else {
            i++
        }
    }
    var missingNumber = array.size + 1
    run breaking@ {
        array.forEachIndexed { index, value ->
            if (index + 1 != value) {
                missingNumber = index + 1
                return@breaking
            }
        }
    }
    return missingNumber
}