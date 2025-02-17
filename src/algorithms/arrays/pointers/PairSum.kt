package algorithms.arrays.pointers

import utils.printValue

// https://www.geeksforgeeks.org/given-a-sorted-and-rotated-array-find-if-there-is-a-pair-with-a-given-sum/

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    doFindPair(arr, 3).printValue()
    doFindPair(arr, 15).printValue()
}

private fun doFindPair(arr: IntArray, total: Int): Boolean {
    val remindersArray = Array(total) { 0 }
    arr.forEach {
        remindersArray[it % total] += 1
    }
    if (remindersArray[0] >= 2) {
        return true
    }
    var startIndex = 1
    var endIndex = total - 1
    while (startIndex <= endIndex) {
        if (minOf(remindersArray[startIndex], remindersArray[endIndex]) >= 1) {
            return true
        }
        startIndex += 1
        endIndex -= 1
    }
    return false
}