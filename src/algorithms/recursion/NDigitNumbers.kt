package algorithms.recursion

import utils.printList

// https://www.geeksforgeeks.org/problems/n-digit-numbers-with-digits-in-increasing-order5903/1

fun main() {
    val size = 2
    val results = arrayListOf<Int>()
    val map = mutableMapOf<String, MutableList<Int>>()
    for (i in IntRange(size, 9)) {
        val subResult = getNDigitNumbers(size, i, map)
        subResult.forEach {
            if(!results.contains(it)) {
                results.add(it)
            }
        }
    }
    results.sorted().printList()
}

private fun getNDigitNumbers(size: Int, succeedingDigit: Int, map: MutableMap<String, MutableList<Int>>): MutableList<Int> {
    if (map["$size-$succeedingDigit"] != null) {
        return map["$size-$succeedingDigit"]!!
    }
    if (size == 1) {
        val baseResult = arrayListOf<Int>()
        for (i in 1 until succeedingDigit) {
            baseResult.add(i)
        }
        return baseResult
    }
    val result = arrayListOf<Int>()
    for (i in size until succeedingDigit + 1) {
        val subResult = getNDigitNumbers(size - 1, i, map)
        subResult.forEach {
            result.add(it * 10 + succeedingDigit)
        }
    }
    map["$size-$succeedingDigit"] = result
    return result
}