package algorithms.arrays.cycle

import utils.printList
import utils.swap

// https://leetcode.com/problems/find-all-duplicates-in-an-array/

fun main() {
    findDuplicatesInArray(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)).printList()
}

private fun findDuplicatesInArray(array: IntArray): List<Int> {
    var i = 0
    while (i < array.size) {
        val index = array[i] - 1
        if (array[i] != array[index]) {
            array.swap(i, index)
        } else {
            i++
        }
    }
    val duplicateNumber = mutableListOf<Int>()
    array.forEachIndexed { index, value ->
        if (index + 1 != value) {
            duplicateNumber.add(value)
        }
    }
    return duplicateNumber
}