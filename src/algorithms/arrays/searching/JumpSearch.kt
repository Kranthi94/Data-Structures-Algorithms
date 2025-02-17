package algorithms.arrays.searching

import kotlin.math.sqrt

// https://www.geeksforgeeks.org/jump-search/

fun main() {
    val arr = intArrayOf(1, 4, 5, 19, 23)
    doJumpSearch(arr, 23, 0, sqrt(arr.size.toDouble()).toInt())
}

private fun doJumpSearch(arr: IntArray, num: Int, startIndex: Int, blockSize: Int) {
    if (startIndex >= arr.size) {
        println("Element not found")
    } else {
        var range = startIndex + blockSize
        if (range >= arr.size) {
            range = arr.size - 1
        }
        val first = arr[startIndex]
        val last = arr[range]
        if (num in first until last + 1) {
            for (i in startIndex until range + 1) {
                if (arr[i] == num) {
                    return println("Element found at index $i")
                }
            }
            return println("Element not found")
        } else {
            doJumpSearch(arr, num, range + 1, blockSize)
        }
    }
}