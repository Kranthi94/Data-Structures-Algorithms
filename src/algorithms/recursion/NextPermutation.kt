package algorithms.recursion

import utils.printArray
import utils.swap

// https://www.naukri.com/code360/problems/next-greater-permutation_6929564

fun main() {
    val inputs = arrayOf(
        arrayOf(3, 1, 2),
        arrayOf(2, 1, 5, 4, 3, 0, 0),
        arrayOf(9, 7, 6, 5),
        arrayOf(1, 2, 3, 6, 5)
    )
    inputs.forEach {
        findNextPermutation(it).printArray()
    }
}

private fun findNextPermutation(array: Array<Int>): Array<Int> {
    var found = false
    for (j in array.size - 1 downTo 1) {
        if (array[j - 1] < array[j]) {
            val minIndex = findNextGreaterIndex(array, array[j - 1], j, array.size - 1)
            array.swap(j - 1, minIndex)
            array.reverse(j, array.size)
            found = true
            break
        }
    }
    if (!found) {
        array.reverse()
    }
    return array
}

private fun findNextGreaterIndex(array: Array<Int>, target: Int, startIndex: Int, endIndex: Int): Int {
    var minIndex = startIndex
    for (i in startIndex + 1 until minOf(array.size, endIndex + 1)) {
        if (array[i] > target && array[i] < array[minIndex]) {
            minIndex = i
        } else {
            break
        }
    }
    return minIndex
}