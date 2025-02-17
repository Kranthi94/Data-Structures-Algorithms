package algorithms.arrays.cycle

import utils.printArray
import utils.swap

// https://leetcode.com/problems/set-mismatch/description/

fun main() {
    findDuplicatesAndMissing(intArrayOf(1, 2, 2, 4)).printArray()
}

private fun findDuplicatesAndMissing(array: IntArray): IntArray {
    var i = 0
    while (i < array.size) {
        val index = array[i] - 1
        if (array[i] != array[index]) {
            array.swap(i, index)
        } else {
            i++
        }
    }
    val result = IntArray(2)
    array.forEachIndexed { index, value ->
        if (index + 1 != value) {
            result[0] = value
            result[1] = index + 1
            return@forEachIndexed
        }
    }
    return result
}