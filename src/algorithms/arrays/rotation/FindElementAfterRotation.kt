package algorithms.arrays.rotation

import utils.printValue

// https://www.geeksforgeeks.org/find-element-given-index-number-rotations/

fun main() {
    val arr = intArrayOf(15, 18, 2, 3, 6, 12)
    findElementAfterRotation(arr, arrayOf(Pair(0, 2), Pair(0, 3), Pair(1, 4)), 1).printValue()
}

private fun findElementAfterRotation(arr: IntArray, rotations: Array<Pair<Int, Int>>, targetIndex: Int): Int {
    var index = targetIndex
    rotations.reversed().forEach {
        val rangeStart = it.first
        val rangeEnd = it.second
        when (index) {
            rangeStart -> {
                index = rangeEnd
            }
            in rangeStart + 1..rangeEnd -> {
                index -= 1
            }
        }
    }
    return arr[index]
}