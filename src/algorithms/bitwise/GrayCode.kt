package algorithms.bitwise

import kotlin.math.pow

// https://leetcode.com/problems/gray-code/description/

fun main() {
    println(grayCode(1))
    println(grayCode(2))
    println(grayCode(3))
}

private fun grayCode(n: Int): List<Int> {
    val size = 2.0.pow(n.toDouble()).toInt()
    val array = IntArray(size)
    for (i in 0 until size) {
        array[i] = i
    }
    return mergeSort(array, 0, array.size - 1).toList()
}

private fun mergeSort(array: IntArray, startIndex: Int, endIndex: Int): IntArray {
    if(startIndex == endIndex) {
        return intArrayOf(array[startIndex])
    }
    val midIndex = startIndex + (endIndex - startIndex) / 2
    val leftArray = mergeSort(array, startIndex, midIndex)
    val rightArray = mergeSort(array, midIndex + 1, endIndex)
    return mergeArrays(leftArray, rightArray)
}

private fun mergeArrays(leftArray: IntArray, rightArray: IntArray): IntArray {
    return leftArray.plus(rightArray.reversedArray())
}