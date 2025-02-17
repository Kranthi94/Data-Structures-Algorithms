package algorithms.arrays.searching

import utils.printValue

// https://www.geeksforgeeks.org/linear-search/

fun main() {
    doLinearSearch(intArrayOf(1, 4, 5, 19, 23), 3).printValue()
    doLinearSearch(intArrayOf(1, 4, 5, 19, 23), 4).printValue()
}

private fun doLinearSearch(arr: IntArray, num: Int): Int {
    for (i in arr.indices) {
        if (arr[i] == num) {
            return i
        }
    }
    return -1
}