package algorithms.dynamic.partition

import utils.printList

// https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/
// https://www.naukri.com/code360/problems/cost-to-cut-a-chocolate_3208460

fun main() {
    minCost(7, listOf(1, 3, 4, 5)).printList()
    minCost(9, listOf(1, 2, 4, 5)).printList()
    minCost(9, listOf(5, 6, 1, 4, 2)).printList()
    minCost(4, listOf(1, 2)).printList()
}

private fun minCost(stickSize: Int, cutsList: List<Int>): List<Int> {
    return listOf(
        minCostRecursive(stickSize, cutsList.sorted()),
        minCostDP(cutsList.sorted(), 0, stickSize, Array(stickSize + 1) { Array(stickSize + 1) { -1 } })
    )
}

private fun minCostRecursive(stickSize: Int, cutsList: List<Int>): Int {
    if (cutsList.isEmpty()) {
        return 0
    }
    if (cutsList.size == 1) {
        return stickSize
    }
    var minCost = Int.MAX_VALUE
    for (i in cutsList.indices) {
        val currentCut = cutsList[i]
        val leftMinCost = minCostRecursive(currentCut, cutsList.slice(IntRange(0, i - 1)))
        val rightCuts: MutableList<Int> = mutableListOf()
        if (i < cutsList.size - 1) {
            cutsList.slice(IntRange((i + 1).coerceAtMost(cutsList.size), cutsList.size - 1)).forEach {
                rightCuts.add(it - currentCut)
            }
        }
        val rightMinCost = minCostRecursive(stickSize - currentCut, rightCuts)
        minCost = minCost.coerceAtMost(leftMinCost + rightMinCost)
    }
    return minCost + stickSize
}

private fun minCostDP(cutsList: List<Int>, startIndex: Int, endIndex: Int, dpMatrix: Array<Array<Int>>): Int {
    if (dpMatrix[startIndex][endIndex] != -1) {
        return dpMatrix[startIndex][endIndex]
    }
    var minCost = Int.MAX_VALUE
    cutsList.forEach { cut ->
        if (cut in (startIndex + 1)..< endIndex) {
            minCost = minOf(minCost, minCostDP(cutsList, startIndex, cut, dpMatrix) + minCostDP(cutsList, cut, endIndex, dpMatrix))
        }
    }
    dpMatrix[startIndex][endIndex] = if (minCost == Int.MAX_VALUE) 0 else minCost + endIndex - startIndex
    return dpMatrix[startIndex][endIndex]
}