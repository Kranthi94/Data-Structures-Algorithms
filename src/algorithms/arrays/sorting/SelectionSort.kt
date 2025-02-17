package algorithms.arrays.sorting

import utils.printArray

// https://www.geeksforgeeks.org/selection-sort/

fun main() {
    doSelectionSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9)).printArray()
    doSelectionSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0)).printArray()
    doSelectionSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0)).printArray()
    doSelectionSort(intArrayOf(1, 2, 3, 4, 5)).printArray()
    doSelectionSort(intArrayOf(5, 4, 3, 2, 1)).printArray()
    doSelectionSort(intArrayOf(5)).printArray()
    doSelectionSort(intArrayOf()).printArray()

    println()

    doSelectionSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9), 9, 0).printArray()
    doSelectionSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0), 8, 0).printArray()
    doSelectionSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0), 8, 0).printArray()
    doSelectionSort(intArrayOf(1, 2, 3, 4, 5), 4, 0).printArray()
    doSelectionSort(intArrayOf(5, 4, 3, 2, 1), 4, 0).printArray()
    doSelectionSort(intArrayOf(5), 0, 0).printArray()
    doSelectionSort(intArrayOf(), -1, 0).printArray()
}

private fun doSelectionSort(arr: IntArray, row: Int, col: Int, maxIndex: Int = -1): IntArray {
    if (row <= 0) {
        return arr
    }
    if (col <= row) {
        if (maxIndex == -1 || arr[col] > arr[maxIndex]) {
            doSelectionSort(arr, row, col + 1, col)
        } else {
            doSelectionSort(arr, row, col + 1, maxIndex)
        }
    } else {
        if (maxIndex < row) {
            val temp = arr[maxIndex]
            arr[maxIndex] = arr[row]
            arr[row] = temp
        }
    }
    return doSelectionSort(arr, row - 1, 0, -1)
}

private fun doSelectionSort(arr: IntArray): IntArray {
    var i = 0
    while (i < arr.size - 1) {
        var minIndex = -1
        var minValue = Int.MAX_VALUE
        for (j in i until arr.size) {
            if (arr[j] < minValue) {
                minValue = arr[j]
                minIndex = j
            }
        }
        arr[minIndex] = arr[i]
        arr[i] = minValue
        i++
    }
    return arr
}