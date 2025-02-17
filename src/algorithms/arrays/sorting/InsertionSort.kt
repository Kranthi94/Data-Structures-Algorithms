package algorithms.arrays.sorting

import utils.printArray
import utils.swap

// https://www.geeksforgeeks.org/insertion-sort/

fun main() {
    doInsertionSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9)).printArray()
    doInsertionSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0)).printArray()
    doInsertionSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0)).printArray()
    doInsertionSort(intArrayOf(1, 2, 3, 4, 5)).printArray()
    doInsertionSort(intArrayOf(5, 4, 3, 2, 1)).printArray()
    doInsertionSort(intArrayOf(5)).printArray()
    doInsertionSort(intArrayOf()).printArray()

    println("#################################################################")

    doRecursiveInsertionSort(intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9)).printArray()
    doRecursiveInsertionSort(intArrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0)).printArray()
    doRecursiveInsertionSort(intArrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0)).printArray()
    doRecursiveInsertionSort(intArrayOf(1, 2, 3, 4, 5)).printArray()
    doRecursiveInsertionSort(intArrayOf(5, 4, 3, 2, 1)).printArray()
    doRecursiveInsertionSort(intArrayOf(5)).printArray()
    doRecursiveInsertionSort(intArrayOf()).printArray()
}

private fun doRecursiveInsertionSort(arr: IntArray, currentIndex: Int = 0): IntArray {
    if (currentIndex == arr.size) {
        return arr
    }
    var startPointer = currentIndex - 1
    var endPointer = currentIndex
    abc@ while (startPointer >= 0) {
        val startValue = arr[startPointer]
        val endValue = arr[endPointer]
        if (startValue > endValue) {
            arr[startPointer] = endValue
            arr[endPointer] = startValue
        } else {
            break@abc
        }
        startPointer -= 1
        endPointer -= 1
    }
    return doRecursiveInsertionSort(arr, currentIndex + 1)
}

private fun doInsertionSort(arr: IntArray): IntArray {
    var i = 0
    while (i < arr.size - 1) {
        var j = i + 1
        while (j > 0) {
            if (arr[j - 1] > arr[j]) {
                arr.swap(j - 1, j)
            } else {
                break
            }
            j--
        }
        i++
    }
    return arr
}