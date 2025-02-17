package algorithms.arrays.sorting

import utils.printArray
import utils.swap

// https://www.geeksforgeeks.org/cycle-sort/

fun main() {
    doCycleSort(intArrayOf(4, 3, 1, 2, 5)).printArray()
    doCycleSort(intArrayOf(4, 3, 6, 2, 5, 1)).printArray()
    doCycleSort(intArrayOf(1, 2, 3, 4, 5, 6)).printArray()
    doCycleSort(intArrayOf(2, 1)).printArray()
    doCycleSort(intArrayOf()).printArray()
}

private fun doCycleSort(arr: IntArray): IntArray {
    var i = 0
    while (i < arr.size - 1) {
        var value = arr[i]
        while (value != i + 1) {
            arr.swap(i, value - 1)
            value = arr[i]
        }
        i++
    }
    return arr
}