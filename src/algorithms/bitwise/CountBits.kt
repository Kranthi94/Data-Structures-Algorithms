package algorithms.bitwise

import utils.printArray

// https://leetcode.com/problems/counting-bits/

fun main() {
    countSetBits(10).printArray()
}

private fun countSetBits(n: Int): Array<Int> {
    val array = Array(n + 1) { 0 }
    for (i in array.indices) {
        if (i % 2 == 0) {
            array[i] = array[i / 2]
        } else {
            array[i] = array[i / 2] + 1
        }
    }
    return array
}