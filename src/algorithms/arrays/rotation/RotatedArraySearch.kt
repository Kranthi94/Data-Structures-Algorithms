package algorithms.arrays.rotation

import utils.printValue

// https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/

fun main() {
    searchTarget(intArrayOf(4, 5, 6, 7, 8, 1, 2, 3, 4), 3).printValue()
}

private fun searchTarget(array: IntArray, target: Int): Int {
    val pivot = findPivot(array)
    if (pivot == -1) {
        return binarySearch(array, target, 0, array.size - 1)
    }
    if (target == array[pivot]) {
        return pivot
    }
    if (array[0] <= target) {
        return binarySearch(array, target, 0, pivot - 1)
    }
    return binarySearch(array, target, pivot + 1, array.size - 1)
}

private fun findPivot(array: IntArray): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    if (endIndex == 0) {
        return 0
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        if (midIndex < endIndex && midValue > array[midIndex + 1]) {
            return midIndex
        }
        if (startIndex < midIndex && midValue < array[midIndex - 1]) {
            return midIndex - 1
        }
        if (array[startIndex] >= midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return -1
}

private fun binarySearch(array: IntArray, target: Int, start: Int, end: Int): Int {
    var startIndex = start
    var endIndex = end
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        if (target == midValue) {
            return midIndex
        }
        if (target < midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return -1
}
