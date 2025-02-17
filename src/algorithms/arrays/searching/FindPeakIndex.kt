package algorithms.arrays.searching

import utils.printValue

// https://leetcode.com/problems/peak-index-in-a-mountain-array/description/

// It should be a mountain array strictly increasing in the first and then may be decreasing.

fun main() {
    val inputs = arrayOf(
        intArrayOf(2, 3, 4, 5),
        intArrayOf(3, 4, 5, 1),
        intArrayOf(3, 4, 5, 6, 7, 9, 2, 1),
    )
    inputs.forEach {
        findPeakIndex(it).printValue()
    }
}

private fun findPeakIndex(array: IntArray): Int {
    if (array.size == 1) {
        return array[0]
    }
    return when {
        array[array.size - 1] > array[array.size - 2] -> array.size - 1
        else -> {
            var startIndex = 1
            var endIndex = array.size - 1
            while (startIndex <= endIndex) {
                val midIndex = startIndex + (endIndex - startIndex) / 2
                val midValue = array[midIndex]
                when {
                    array[0] <= midValue  -> {
                        startIndex = midIndex + 1
                    }
                    else -> {
                        endIndex = midIndex - 1
                    }
                }
            }
            return endIndex
        }
    }
}