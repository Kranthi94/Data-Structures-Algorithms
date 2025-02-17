package algorithms.recursion

import utils.printList

fun main() {
    val arr = intArrayOf(0, 1, 2, 3, 3, 5, 8)
    println(checkArraySorted(arr, arr.size - 1))
    println(linearSearch(arr, -1, 0))
    findAllIndex(arr, 3, 0, mutableListOf()).printList()
    findAllIndex(arr, 3, 0).printList()
    val rotatedArray = intArrayOf(5, 6, 7, 9, 10, 12, 13)
    println(findInRotatedArray(rotatedArray, 14, 0, rotatedArray.size - 1))
}

private fun checkArraySorted(arr: IntArray, index: Int): Boolean {
    if (index == 1) {
        return arr[0] < arr[1]
    }
    val result = arr[index] >= arr[index - 1]
    return result && checkArraySorted(arr, index - 1)
}

private fun linearSearch(arr: IntArray, target: Int, index: Int): Int {
    if (index == arr.size) {
        return -1
    }
    if (arr[index] == target) {
        return index
    }
    return linearSearch(arr, target, index + 1)
}

private fun findAllIndex(arr: IntArray, target: Int, index: Int, list: MutableList<Int>): List<Int> {
    if (index == arr.size) {
        return list
    }
    if (arr[index] == target) {
        list.add(index)
    }
    return findAllIndex(arr, target, index + 1, list)
}

private fun findAllIndex(arr: IntArray, target: Int, index: Int): List<Int> {
    val list = mutableListOf<Int>()
    if (index == arr.size) {
        return list
    }
    if (arr[index] == target) {
        list.add(index)
    }
    list.addAll(findAllIndex(arr, target, index + 1))
    return list
}

private fun findInRotatedArray(arr: IntArray, target: Int, startIndex: Int, endIndex: Int): Int {
    if (startIndex > endIndex) {
        return -1
    }
    val midIndex = startIndex + (endIndex - startIndex) / 2
    if (arr[midIndex] == target) {
        return midIndex
    }
    if (arr[startIndex] < arr[midIndex]) {
        return if (arr[startIndex] <= target && arr[midIndex] > target) {
            findInRotatedArray(arr, target, startIndex, midIndex - 1)
        } else {
            findInRotatedArray(arr, target, midIndex + 1, endIndex)
        }
    }
    if (arr[startIndex] > arr[midIndex]) {
        return if (arr[midIndex] < target && target <= arr[endIndex]) {
            findInRotatedArray(arr, target, midIndex + 1, endIndex)
        } else {
            findInRotatedArray(arr, target, startIndex, midIndex - 1)
        }
    }
    return -1
}