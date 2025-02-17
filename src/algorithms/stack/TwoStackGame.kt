package algorithms.stack

import utils.printValue

// https://www.hackerrank.com/challenges/game-of-two-stacks/problem

fun main() {
    val aArray = arrayOf(4, 2, 4)
    val bArray = arrayOf(2, 1)
    twoStacks(15, aArray, bArray, 0, 0, 0, 0, mutableMapOf()).printValue()
}

private fun twoStacks(
    maxSum: Int,
    a: Array<Int>,
    b: Array<Int>,
    aIndex: Int,
    bIndex: Int,
    currentSum: Int,
    maxSteps: Int,
    map: MutableMap<String, Int>
): Int {
    if (currentSum > maxSum) {
        return maxSteps - 1
    }
    if (aIndex !in a.indices || bIndex !in b.indices) {
        return maxSteps
    }
    if (map["$currentSum-$aIndex-$bIndex"] != null) {
        return map["$currentSum-$aIndex-$bIndex"]!!
    }
    val choosingAIndex = twoStacks(maxSum, a, b, aIndex + 1, bIndex, currentSum + a[aIndex], maxSteps + 1, map)
    val choosingBIndex = twoStacks(maxSum, a, b, aIndex, bIndex + 1, currentSum + b[bIndex], maxSteps + 1, map)
    map["$currentSum-$aIndex-$bIndex"] = choosingAIndex.coerceAtLeast(choosingBIndex)
    return choosingAIndex.coerceAtLeast(choosingBIndex)
}