package algorithms.bitwise

// https://leetcode.com/problems/power-of-four/

fun main() {
    println("Is 2 power of two ===> ${powerOfFour(2)}")
    println("Is 3 power of two ===> ${powerOfFour(3)}")
    println("Is 64 power of two ===> ${powerOfFour(64)}")
    println("Is 99 power of two ===> ${powerOfFour(99)}")
}

fun isPowerOfFour(n: Int): Boolean {
    return when {
        n == 0 -> false
        n == 1 -> true
        n % 4 != 0 -> false
        else -> isPowerOfFour(n / 4)
    }
}

private fun powerOfFour(num: Int): Boolean {
    if (num == 1) {
        return true
    }
    if (num and num - 1 == 0) {
        var index = 2
        while (index < 31) {
            if (num shr index and 1 == 1) {
                return true
            }
            index += 2
        }
    }
    return false
}