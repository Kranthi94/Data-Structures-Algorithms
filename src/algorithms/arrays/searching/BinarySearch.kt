package algorithms.arrays.searching

import utils.printValue

fun main() {
    val array = intArrayOf(2, 4, 6, 7, 9)
    binarySearch(array, 0).printValue()
    binarySearch(array, 6).printValue()
    binarySearch(array, 8).printValue()
    binarySearch(array, 9).printValue()
    binarySearch(array, 10).printValue()
}

private fun binarySearch(array: IntArray, target: Int): Int {
    if (target < array[0] || target > array[array.size - 1]) {
        return -1
    }
    var startIndex = 0
    var endIndex = array.size - 1
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