package algorithms.arrays.cycle

import utils.printList
import utils.swap

// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

fun main() {
    findMissingNumberRange(intArrayOf(1, 1)).printList()
    findMissingNumberRange(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)).printList()
}

private fun findMissingNumberRange(array: IntArray): List<Int> {
    var i = 0
    while (i < array.size) {
        val index = array[i] - 1
        if (array[i] != array[index]) {
            array.swap(i, index)
        } else {
            i++
        }
    }
    val missingNumbersRange = mutableListOf<Int>()
    array.forEachIndexed { index, value ->
        if (index + 1 != value) {
            missingNumbersRange.add(index + 1)
        }
    }
    return missingNumbersRange
}