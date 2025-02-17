package algorithms.arrays.rotation

import utils.printArray

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    doReversal(arr, 2)
}

private fun doReversal(arr: IntArray, d: Int) {
    var i = 0
    while (i < d - 1 - i) {
        val temp = arr[i]
        arr[i] = arr[d - 1 - i]
        arr[d - 1 - i] = temp
        i++
    }
    var j = d
    while (j < arr.size - 1 - j) {
        val temp = arr[j]
        arr[j] = arr[arr.size - 1 + d - j]
        arr[arr.size - 1 + d - j] = temp
        j++
    }
    var k = 0
    while (k < arr.size - 1 - k) {
        val temp = arr[k]
        arr[k] = arr[arr.size - 1 - k]
        arr[arr.size - 1 - k] = temp
        k++
    }
    arr.printArray()
}