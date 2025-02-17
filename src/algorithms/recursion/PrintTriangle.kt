package algorithms.recursion

import utils.printArray

// https://www.geeksforgeeks.org/sum-triangle-from-array/

fun main() {
    printTriangle(intArrayOf(1, 2, 3, 4, 5))
}

private fun printTriangle(array: IntArray) {
    if (array.isEmpty()) {
        return
    }
    val arr = IntArray(array.size - 1)
    for (i in 0 until array.size - 1) {
        arr[i] = array[i] + array[i + 1]
    }
    printTriangle(arr)
    array.printArray()
}