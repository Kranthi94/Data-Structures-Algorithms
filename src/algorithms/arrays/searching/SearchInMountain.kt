package algorithms.arrays.searching

import utils.printValue

// https://leetcode.com/problems/find-in-mountain-array/description/

fun main() {
    findTarget(intArrayOf(3, 4, 5, 6, 7, 9, 2, 1), 2).printValue()
}

private fun findTarget(array: IntArray, target: Int): Int {
    val peakIndex = findPeakIndex(array)
    val peakValue = array[peakIndex]
    if (peakValue == target) {
        return peakIndex
    }
    var searchIndex = binarySearch(array, target, 0, peakIndex - 1, true)
    if (searchIndex == -1) {
        searchIndex = binarySearch(array, target, peakIndex + 1, array.size - 1, false)
    }
    return searchIndex
}

private fun findPeakIndex(array: IntArray): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    while (startIndex < endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        when {
            midValue < array[midIndex + 1] -> {
                startIndex = midIndex + 1
            }
            midValue > array[midIndex + 1] -> {
                endIndex = midIndex
            }
        }
    }
    return startIndex
}

private fun binarySearch(array: IntArray, target: Int, start: Int, end: Int, ascending: Boolean): Int {
    var startIndex = start
    var endIndex = end
    if (target < array[startIndex]) {
        return -1
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        if (target == midValue) {
            return midIndex
        }
        if (target < midValue) {
            if (ascending) endIndex = midIndex - 1 else startIndex = midIndex + 1
        } else {
            if (ascending) startIndex = midIndex + 1 else endIndex = midIndex - 1
        }
    }
    return -1
}