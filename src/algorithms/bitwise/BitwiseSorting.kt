package algorithms.bitwise

import utils.printValue

// https://leetcode.com/problems/find-if-array-can-be-sorted/description/

fun main() {
    val inputs = arrayOf(
        arrayOf(3, 16, 8, 4, 2),
        arrayOf(1, 2, 3, 4, 5),
        arrayOf(8, 4, 2, 30, 15),
        arrayOf(8, 4, 2, 30, 15, 0),
        arrayOf(4, 2, 8, 13, 7, 14, 32, 16, 64, 30, 15),
        arrayOf(4, 2, 13, 7, 14)
    )
    inputs.forEach {
        canBeSorted(it).printValue()
    }
}

private fun canBeSorted(array: Array<Int>): Boolean {
    if (array.size <= 1) {
        return true
    }
    val bitsList: MutableList<Pair<Int, Int>> = mutableListOf()
    var previousSetBit = -1 // 4
    var previousSetBitMinValue = Int.MAX_VALUE // 15
    var previousSetBitMaxValue = Int.MIN_VALUE // 15
    array.forEach {
        val currentSetBit = findSetBits(it)
        if (previousSetBit != -1 && previousSetBit != currentSetBit) {
            bitsList.add(Pair(previousSetBitMinValue, previousSetBitMaxValue))
            if (bitsList.size > 1) {
                if (bitsList[bitsList.size - 2].second > bitsList[bitsList.size - 1].first) {
                    return false
                }
            }
            previousSetBitMinValue = it
            previousSetBitMaxValue = it
        } else {
            previousSetBitMinValue = minOf(previousSetBitMinValue, it)
            previousSetBitMaxValue = maxOf(previousSetBitMaxValue, it)
        }
        previousSetBit = currentSetBit
    }
    bitsList.add(Pair(previousSetBitMinValue, previousSetBitMaxValue))
    if (bitsList.size <= 1) {
        return true
    }
    return bitsList[bitsList.size - 2].second <= bitsList[bitsList.size - 1].first
}


/**
 * TC: O(LogN)
 */
private fun findSetBits(num: Int): Int {
    var count = 0
    var target = num
    while (target != 0) {
        target = target and target - 1
        count += 1
    }
    return count
}