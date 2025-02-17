package algorithms.arrays.sorting

import utils.printArray
import utils.swap

// https://www.geeksforgeeks.org/bubble-sort/

fun main() {
    doBubbleSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9)).printArray()
    doBubbleSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0)).printArray()
    doBubbleSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0)).printArray()
    doBubbleSort(intArrayOf(1, 2, 3, 4, 5)).printArray()
    doBubbleSort(intArrayOf(5, 4, 3, 2, 1)).printArray()
    doBubbleSort(intArrayOf(5)).printArray()
    doBubbleSort(intArrayOf()).printArray()

    println()

    doBubbleSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9), 9, 0).printArray()
    doBubbleSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0), 8, 0).printArray()
    doBubbleSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0), 8, 0).printArray()
    doBubbleSort(intArrayOf(1, 2, 3, 4, 5), 4, 0).printArray()
    doBubbleSort(intArrayOf(5, 4, 3, 2, 1), 4, 0).printArray()
    doBubbleSort(intArrayOf(5), 0, 0).printArray()
    doBubbleSort(intArrayOf(), -1, 0).printArray()

    println()

    doRecursiveBubbleSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9)).printArray()
    doRecursiveBubbleSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0),).printArray()
    doRecursiveBubbleSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0)).printArray()
    doRecursiveBubbleSort(intArrayOf(1, 2, 3, 4, 5)).printArray()
    doRecursiveBubbleSort(intArrayOf(5, 4, 3, 2, 1)).printArray()
    doRecursiveBubbleSort(intArrayOf(5)).printArray()
    doRecursiveBubbleSort(intArrayOf()).printArray()
}

private fun doRecursiveBubbleSort(arr: IntArray, endIndex: Int = arr.size - 1): IntArray {
    if (endIndex <= 0) {
        return arr
    }
    var startIndex = 0
    var swappingDone = false
    while (startIndex < endIndex) {
        if (arr[startIndex] > arr[startIndex + 1]) {
            val temp = arr[startIndex]
            arr[startIndex] = arr[startIndex + 1]
            arr[startIndex + 1] = temp
            swappingDone = true
        }
        startIndex += 1
    }
    if (!swappingDone) {
        return arr
    }
    return doRecursiveBubbleSort(arr, endIndex - 1)
}

private fun doBubbleSort(arr: IntArray, row: Int, col: Int): IntArray {
    if (row <= 0) {
        return arr
    }
    if (col < row) {
        if (arr[col] > arr[col + 1]) {
            val temp = arr[col]
            arr[col] = arr[col + 1]
            arr[col + 1] = temp
        }
        return doBubbleSort(arr, row, col + 1)
    }
    return doBubbleSort(arr, row - 1, 0)
}

private fun doBubbleSort(arr: IntArray): IntArray {
    var i = 0
    while (i < arr.size) {
        var j = 1
        var swapped = false
        while (j < arr.size - i) {
            if (arr[j - 1] > arr[j]) {
                arr.swap(j - 1, j)
                swapped = true
            }
            j++
        }
        if (!swapped) {
            break
        } else {
            i++
        }
    }
    return arr
}