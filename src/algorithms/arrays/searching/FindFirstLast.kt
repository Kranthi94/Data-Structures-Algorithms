package algorithms.arrays.searching

import utils.printPair

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

fun main() {
    val inputs = arrayOf(
        arrayOf(6, 6, 7, 7, 7, 7, 8, 8)
    )
    inputs.forEach { array ->
        repeat(5) {
            findPosition(array, (array[0]..array[array.size - 1]).random()).printPair()
        }
    }
}

private fun findPosition(array: Array<Int>, target: Int): Pair<Int, Int> {
    val firstIndex = findPosition(array, target, true)
    var secondIndex = firstIndex
    if (firstIndex != -1) {
        secondIndex = findPosition(array, target, false)
    }
    return Pair(firstIndex, secondIndex)
}

private fun findPosition(array: Array<Int>, target: Int, searchingFirstIndex: Boolean): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    var searchIndex = -1
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        when {
            midValue == target -> {
                searchIndex = midIndex
                if (searchingFirstIndex) endIndex = midIndex - 1 else startIndex = midIndex + 1
            }

            midValue < target -> startIndex = midIndex + 1
            else -> endIndex = midIndex - 1
        }
    }
    return searchIndex
}