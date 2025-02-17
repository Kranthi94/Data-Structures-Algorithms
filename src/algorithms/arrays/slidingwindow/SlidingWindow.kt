package algorithms.arrays.slidingwindow

import utils.printValue

// https://www.geeksforgeeks.org/window-sliding-technique/

fun main() {
    val inputs = arrayOf(
        arrayOf(27, 6, 15, 3, 21)
    )
    inputs.forEach {
        findMaxConsecutiveSum(it, 7).printValue()
    }
}

private fun findMaxConsecutiveSum(arr: Array<Int>, windowSize: Int): Int {
    var leftIndex = 0
    var rightIndex = windowSize - 1
    var currentSum = arr.take(windowSize).sum()
    var maxSum = currentSum
    if (windowSize > arr.size) {
        return currentSum
    }
    while (rightIndex < arr.size - 1) {
        currentSum += arr[rightIndex + 1] - arr[leftIndex]
        maxSum = maxOf(maxSum, currentSum)
        leftIndex += 1
        rightIndex += 1
    }
    return maxSum
}