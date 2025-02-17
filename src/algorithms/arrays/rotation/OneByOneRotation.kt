package algorithms.arrays.rotation

import utils.printArray

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    doOneByOneRotation(arr, 2)
}

private fun doOneByOneRotation(arr: IntArray, d: Int) {
    (0 until d).forEach { i ->
        val temp = arr[0]
        for (j in arr.indices) {
            if (j < arr.size - 1) {
                arr[j] = arr[j + 1]
            } else {
                arr[j] = temp
            }
        }
    }
    arr.printArray()
}