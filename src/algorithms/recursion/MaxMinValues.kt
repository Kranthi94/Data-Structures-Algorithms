package algorithms.recursion

import utils.printPair

fun main() {
    findMinAndMax(intArrayOf(1, 4, 3, -5, -4, 8, 6)).printPair()
    findMinAndMax(intArrayOf(1, 4, 45, 6, 10, -8)).printPair()
}

private fun findMinAndMax(array: IntArray, currentIndex: Int = 0, currentMin: Int = Integer.MAX_VALUE, currentMax: Int = Integer.MIN_VALUE): Pair<Int, Int> {
    if (currentIndex == array.size) {
        return Pair(currentMin, currentMax)
    }
    val currentValue = array[currentIndex]
    var min = currentMin
    if (currentValue < min) {
        min = currentValue
    }
    var max = currentMax
    if (currentValue > max) {
        max = currentValue
    }
    return findMinAndMax(array, currentIndex + 1, min, max)
}