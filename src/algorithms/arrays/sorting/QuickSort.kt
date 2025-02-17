package algorithms.arrays.sorting

import utils.printArray

// https://www.geeksforgeeks.org/quick-sort/

fun main() {
    doQuickSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9), 0, 9).printArray()
    doQuickSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0), 0, 8).printArray()
    doQuickSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0), 0, 8).printArray()
    doQuickSort(intArrayOf(1, 2, 3, 4, 5), 0, 4).printArray()
    doQuickSort(intArrayOf(5, 4, 3, 2, 1), 0, 4).printArray()
    doQuickSort(intArrayOf(5), 0, 0).printArray()
    doQuickSort(intArrayOf(), 0, -1).printArray()
}

private fun doQuickSort(arr: IntArray, startIndex: Int, endIndex: Int): IntArray {
    if (endIndex < startIndex) {
        return arr
    }
    var startPointer = startIndex
    var endPointer = endIndex
    val midPointer = startPointer + (endPointer - startPointer) / 2
    val pivot = arr[midPointer]
    while (startPointer <= endPointer) {
        while (arr[startPointer] < pivot) {
            startPointer++
        }
        while (pivot < arr[endPointer]) {
            endPointer--
        }
        if (startPointer <= endPointer) {
            val temp = arr[startPointer]
            arr[startPointer] = arr[endPointer]
            arr[endPointer] = temp
            startPointer++
            endPointer--
        }
    }
    doQuickSort(arr, startIndex, endPointer)
    doQuickSort(arr, startPointer, endIndex)
    return arr
}