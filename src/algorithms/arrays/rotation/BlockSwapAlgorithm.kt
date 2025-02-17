package algorithms.arrays.rotation

import utils.printArray

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    doBlockSwap(arr, 2).printArray()
}

private fun doBlockSwap(arr: IntArray, swaps: Int): IntArray {
    return intArrayOf().plus(arr.copyOfRange(swaps, arr.size)).plus(arr.copyOfRange(0, swaps))
}