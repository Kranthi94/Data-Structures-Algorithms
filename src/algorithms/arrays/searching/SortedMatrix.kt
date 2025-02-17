package algorithms.arrays.searching

import utils.printArray

// https://leetcode.com/problems/search-a-2d-matrix/

fun main() {
    val array = arrayOf(
        arrayOf(1, 3, 5, 7).toIntArray(),
        arrayOf(10, 11, 16, 20).toIntArray(),
        arrayOf(23, 30, 34, 60).toIntArray()
    )
    findTarget(array, 13).printArray()
}

private fun findTarget(array: Array<IntArray>, target: Int): IntArray {
    val rows = array.size
    val columns = array[0].size
    if (rows == 1 && columns == 1) {
        return if (target == array[0][0]) {
            IntArray(2) { 0 }
        } else {
            IntArray(2) { -1 }
        }
    }
    if (rows == 1) {
        return binarySearch(array, target, true, 0, 0, columns - 1)
    }
    if (columns == 1) {
        return binarySearch(array, target, false, 0, 0, rows - 1)
    }
    val floorIndex = findFloor(array, target, false, 0, 0, rows - 1)
    if (floorIndex != -1) {
        return binarySearch(array, target, true, floorIndex, 0, columns - 1)
    }
    return IntArray(2) { -1 }
}

private fun findFloor(
    array: Array<IntArray>, target: Int, rowSearch: Boolean, index: Int, start: Int,
    end: Int
): Int {
    var startIndex = start
    var endIndex = end
    if (rowSearch) {
        if (target < array[index][0]) return -1
    } else {
        if (target < array[0][index]) return -1
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = if (rowSearch) {
            array[index][midIndex]
        } else {
            array[midIndex][index]
        }
        if (target == midValue) {
            return midIndex
        }
        if (target < midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return endIndex
}

private fun binarySearch(
    array: Array<IntArray>,
    target: Int,
    rowSearch: Boolean,
    index: Int,
    start: Int,
    end: Int
): IntArray {
    var startIndex = start
    var endIndex = end
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue: Int = if (rowSearch) {
            array[index][midIndex]
        } else {
            array[midIndex][index]
        }
        if (midValue == target) {
            return if (rowSearch) {
                arrayOf(index, midIndex).toIntArray()
            } else {
                arrayOf(midIndex, index).toIntArray()
            }
        }
        if (midValue < target) {
            startIndex = midIndex + 1
        } else {
            endIndex = midIndex - 1
        }
    }
    return IntArray(2) { -1 }
}