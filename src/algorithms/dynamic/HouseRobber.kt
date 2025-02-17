package algorithms.dynamic

import utils.printValue

// https://leetcode.com/problems/house-robber/description/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    maxCostRobbing(intArrayOf(1, 2, 3, 1)).printValue()
    maxCostRobbing(intArrayOf(2, 1, 1, 2)).printValue()
    maxCostRobbing(intArrayOf(2, 7, 9, 3, 1)).printValue()
}

private fun maxCostRobbing(costArray: IntArray): Int {
    var maxCost = Int.MIN_VALUE
    val maxCostArray = Array(costArray.size) { -1 }
    repeat(2) { currentIndex ->
        maxCost = maxCost.coerceAtLeast(maxCostRobbing(currentIndex, costArray, maxCostArray))
    }
    return maxCost
}

private fun maxCostRobbing(currentIndex: Int, costArray: IntArray, maxCostArray: Array<Int>): Int {
    if (currentIndex !in costArray.indices) {
        return 0
    }
    if (maxCostArray[currentIndex] != -1) {
        return maxCostArray[currentIndex]
    }
    var index = currentIndex + 2
    var maxCost = Int.MIN_VALUE
    while (index in costArray.indices) {
        maxCost = maxCost.coerceAtLeast(maxCostRobbing(index, costArray, maxCostArray))
        index += 1
    }
    if (maxCost == Int.MIN_VALUE) {
        maxCost = 0
    }
    maxCostArray[currentIndex] = costArray[currentIndex] + maxCost
    return maxCostArray[currentIndex]
}