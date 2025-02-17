package algorithms.arrays.searching

import utils.printValue

// https://www.geeksforgeeks.org/binary-search-preferred-ternary-search/

fun main() {
    val arr = intArrayOf(1, 4, 5, 19, 23, 25, 26)
    doTernarySearch(arr, 26, 0, arr.size - 1).printValue()
}

private fun doTernarySearch(arr: IntArray, num: Int, startIndex: Int, endIndex: Int): Int {
    if (startIndex == endIndex || startIndex < 0 || endIndex >= arr.size) {
        return -1
    } else {
        val pivot1 = startIndex + (endIndex - startIndex) / 3
        val pivot2 = pivot1 + (endIndex - startIndex) / 3
        return when {
            num == arr[pivot1] -> pivot1
            num == arr[pivot2] -> pivot2
            num < arr[pivot1] -> doTernarySearch(arr, num, startIndex, pivot1 - 1)
            num > arr[pivot2] -> doTernarySearch(arr, num, pivot2 + 1, endIndex)
            else -> doTernarySearch(arr, num, pivot1 + 1, pivot2 - 1)
        }
    }
}