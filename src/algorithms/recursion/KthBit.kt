package algorithms.recursion

import utils.printValue

// https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/description/

fun main() {
    findKthBit(1, 1).printValue()
    findKthBit(2, 2).printValue()
    findKthBit(3, 1).printValue()
    findKthBit(4, 11).printValue()
}

private fun findKthBit(n: Int, k: Int): Char {
    return findNthBinaryString(n)[k - 1]
}

private fun findNthBinaryString(n: Int): String {
    if (n == 1) {
        return "0"
    }
    val binaryString = findNthBinaryString(n - 1)
    return binaryString + "1" + invert(binaryString)
}

private fun invert(string: String): String {
    return string.map {
        when(it) {
            '0' -> '1'
            '1' -> '0'
            else -> it
        }
    }.joinToString("").reversed()
}