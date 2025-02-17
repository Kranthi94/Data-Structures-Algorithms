package algorithms.arrays.rotation

import utils.printValue

// https://www.geeksforgeeks.org/find-a-rotation-with-maximum-hamming-distance/

/**
 * TC: O(n^2)
 */

fun main() {
    findMaxHammingDistance(intArrayOf(1, 4, 1)).printValue()
    findMaxHammingDistance(intArrayOf(2, 4, 8, 0)).printValue()
    findMaxHammingDistance(intArrayOf(3, 0, 6, 4, 3)).printValue()
}

private fun findMaxHammingDistance(arr: IntArray): Int {
    var maxHammingDistance = 0
    val arraySize = arr.size
    for (i in 1 until arraySize - 1) {
        var distance = 0
        for (index in arr.indices) {
            val rotatedIndex = (i + index) % arraySize
            if (arr[index] != arr[rotatedIndex]) {
                distance += 1
            }
        }
        maxHammingDistance = maxOf(maxHammingDistance, distance)
        if (maxHammingDistance == arraySize) {
            return maxHammingDistance
        }
    }
    return maxHammingDistance
}