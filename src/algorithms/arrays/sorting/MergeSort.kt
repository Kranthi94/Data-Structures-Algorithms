package algorithms.arrays.sorting

import utils.printArray

// http://geeksquiz.com/merge-sort/

fun main() {
    val arr = intArrayOf(4, 3, 1, 3, 5, 10, -7, 8, 11, 9)
    doMergeSort(arr, 0, arr.size - 1).printArray()
}

private fun doMergeSort(arr: IntArray, startIndex: Int, endIndex: Int): IntArray {
    if (startIndex == endIndex) {
        return intArrayOf(arr[startIndex])
    }
    val midIndex = startIndex + (endIndex - startIndex) / 2
    val leftyArray = doMergeSort(arr, startIndex, midIndex)
    val rightArray = doMergeSort(arr, midIndex + 1, endIndex)
    return mergeArrays(leftyArray, rightArray)
}

private fun mergeArrays(leftArray: IntArray, rightArray: IntArray): IntArray {
    val resultArray = IntArray(leftArray.size + rightArray.size)
    var i = 0
    var j = 0
    var k = 0
    while (i < leftArray.size && j < rightArray.size) {
        val left = leftArray[i]
        val right = rightArray[j]
        if (left < right) {
            resultArray[k] = left
            i++
        } else {
            resultArray[k] = right
            j++
        }
        k++
    }
    while (i < leftArray.size) {
        resultArray[k] = leftArray[i]
        i++
        k++
    }
    while (j < rightArray.size) {
        resultArray[k] = rightArray[j]
        j++
        k++
    }
    return resultArray
}