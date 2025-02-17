package algorithms.bitwise

import utils.printValue

fun main() {
    isPowerOfTwo(5).printValue()
    isPowerOfTwo(16).printValue()
}

private fun isPowerOfTwo(n: Int): Boolean {
    if (n == 0) {
        return false
    }
    return n and n - 1 == 0
}