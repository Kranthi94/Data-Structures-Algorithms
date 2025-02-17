package algorithms.arrays.rotation

import utils.printArray

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    doSimpleRotation(arr, 2)
}

private fun doSimpleRotation(arr: IntArray, d: Int) {
    val subArray = IntArray(d)
    for (i in subArray.indices) {
        subArray[i] = arr[i]
    }
    for (j in arr.indices) {
        if (j >= arr.size - d) {
            arr[j] = subArray[j - arr.size + d]
        } else {
            arr[j] = arr[j + d]
        }
    }
    arr.printArray()
}