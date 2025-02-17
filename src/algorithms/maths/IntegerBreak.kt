package algorithms.maths

// https://leetcode.com/problems/integer-break/description/

fun main() {
    println(integerBreak1(2))
    println(integerBreak1(3))
    println(integerBreak1(4))
    println(integerBreak1(5))
    println(integerBreak1(8))
    println(integerBreak1(10))

    println(integerBreak2(2))
    println(integerBreak2(3))
    println(integerBreak2(4))
    println(integerBreak2(5))
    println(integerBreak2(8))
    println(integerBreak2(10))
}

private fun integerBreak2(n: Int): Int {
    if (n == 2 || n == 3) {
        return n - 1
    }
    var num = n
    var result = 1
    while (num > 4) {
        result *= 3
        num -= 3
    }
    return result * num
}

private fun integerBreak1(n: Int): Int {
    if (n == 2 || n == 3) {
        return n - 1
    }
    return integerBreak1(n, mutableMapOf())
}

private fun integerBreak1(n: Int, map: MutableMap<Int, Int>): Int {
    if (n == 2 || n == 3) {
        return n
    }
    if (map.containsKey(n)) {
        return map[n]!!
    }
    val midValue = n / 2
    var result = 1
    for (i in IntRange(1, midValue)) {
        result = result.coerceAtLeast(integerBreak1(i, map) * integerBreak1(n - i, map))
    }
    map[n] = result
    return result
}