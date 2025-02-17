package algorithms.arrays.cycle

import utils.swap

// https://leetcode.com/problems/missing-number/submissions/1378181024/

fun main() {
    println(findMissingNumberRange(intArrayOf(0, 1, 3, 4)))
    println(findMissingNumberRange(intArrayOf(2, 1, 3, 4, 5)))
    println(findMissingNumberRange(intArrayOf(3, 0, 1)))
    println(findMissingNumberRange(intArrayOf(3, 0, 10, 2, 8, 1, 7, 6, 4, 9)))
}

private fun findMissingNumberRange(array: IntArray): Int {
    var i = 0
    while (i < array.size - 1) {
        if (array[i] < array.size && array[i] != i) {
            array.swap(i, array[i])
        } else {
            i++
        }
    }
    var missingNumber = array.size
    array.forEachIndexed { index, value ->
        if (index != value) {
            missingNumber = index
        }
    }
    return missingNumber
}