package algorithms.arrays.rotation

import utils.printValue

// https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/

fun main() {
    val inputs = arrayOf(
        arrayOf(15, 18, 2, 3, 6, 12),
        arrayOf(7, 9, 11, 12, 5),
        arrayOf(16, 9, 11, 12, 15),
        arrayOf(1, 2, 3, 4)
    )
    inputs.forEach {
        findRotations(it).printValue()
    }
}

private fun findRotations(arr: Array<Int>): Int {
    return when (val peakIndex = findPeakIndex(arr)) {
        arr.size - 1 -> 0
        else -> peakIndex + 1
    }
}

private fun findPeakIndex(array: Array<Int>): Int {
    if (array.size == 1) {
        return array[0]
    }
    return when {
        array[0] < array[array.size - 1] -> array.size - 1
        array[0] > array[1] && array[0] > array[array.size - 1] -> 0
        else -> {
            var startIndex = 1
            var endIndex = array.size - 2
            var peakIndex = -1
            while (startIndex <= endIndex) {
                val midIndex = startIndex + (endIndex - startIndex) / 2
                val midValue = array[midIndex]
                val midPrevious = array[midIndex - 1]
                val midNext = array[midIndex + 1]
                if (midPrevious < midValue && midValue > midNext) {
                    peakIndex = midIndex
                    break
                }
                when {
                    midPrevious < midValue -> {
                        startIndex = midIndex + 1
                    }

                    else -> {
                        endIndex = midIndex - 1
                    }
                }
            }
            return peakIndex
        }
    }
}