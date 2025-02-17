package algorithms.arrays.pointers

import utils.printArray

// https://www.geeksforgeeks.org/find-k-closest-elements-given-value/

/**
 * TC: O(logN + k)
 * SC: O(K)
 */
fun main() {
    val arr = intArrayOf(12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56)
    findKClosest(arr, 35, 10).printArray()
    findKClosest(arr, 12, 4).printArray()
    findKClosest(arr, 56, 4).printArray()
}

private fun findKClosest(arr: IntArray, num: Int, k: Int): IntArray {
    val closestArray = IntArray(k) { -1 }
    val index = binarySearch(arr, num)
    if (index == -1) {
        return closestArray
    }
    var leftPointer = index - 1
    var rightPointer = index + 1
    var insertions = 0
    while (leftPointer in arr.indices && rightPointer in arr.indices && insertions < k) {
        val leftValue = arr[leftPointer]
        val rightValue = arr[rightPointer]
        when {
            num - leftValue < rightValue - num -> {
                closestArray[insertions] = arr[leftPointer]
                leftPointer -= 1
                insertions += 1
            }
            num - leftValue > rightValue - num -> {
                closestArray[insertions] = arr[rightPointer]
                rightPointer += 1
                insertions += 1
            }
            else -> {
                closestArray[insertions] = arr[leftPointer]
                leftPointer -= 1
                insertions += 1
            }
        }
    }
    while (insertions < k && leftPointer in arr.indices) {
        closestArray[insertions] = arr[leftPointer]
        leftPointer -= 1
        insertions += 1
    }

    while (insertions < k && rightPointer in arr.indices) {
        closestArray[insertions] = arr[rightPointer]
        rightPointer += 1
        insertions += 1
    }
    return closestArray
}

private fun binarySearch(array: IntArray, target: Int): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    if (target < array[startIndex]) {
        return -1
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        if (target == midValue) {
            return midIndex
        }
        if (target < midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return -1
}