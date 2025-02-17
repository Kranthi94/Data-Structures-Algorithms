package algorithms.dynamic

import utils.printList

// https://www.naukri.com/code360/problems/unbounded-knapsack_1215029

fun main() {
    maxKnapsack(arrayOf(1, 2, 3, 4), arrayOf(1, 2, 3, 4), 8).printList()
    maxKnapsack(arrayOf(1, 2, 3, 4), arrayOf(1, 3, 5, 7), 7).printList()
    maxKnapsack(arrayOf(1, 2, 3, 4), arrayOf(1, 5, 7, 9), 10).printList()
}

private fun maxKnapsack(weightArray: Array<Int>, valueArray: Array<Int>, maxWeight: Int): List<Int> {
    if (weightArray.isEmpty() || valueArray.isEmpty() || weightArray.size != valueArray.size || maxWeight <= 0) {
        return listOf(0)
    }
    return listOf(
        maxKnapsackRecursive(weightArray, valueArray, maxWeight, 0),
        maxKnapsackDP(weightArray, valueArray, maxWeight, 0, mutableMapOf()),
        maxKnapsackTabulation(weightArray, valueArray, maxWeight),
        maxKnapsackTabulationOptimised(weightArray, valueArray, maxWeight)
    )
}

private fun maxKnapsackRecursive(
    weightArray: Array<Int>,
    valueArray: Array<Int>,
    maxWeight: Int,
    currentIndex: Int,
): Int {
    if (maxWeight == 0 || currentIndex !in weightArray.indices) {
        return 0
    }
    var take = Int.MIN_VALUE
    if (weightArray[currentIndex] <= maxWeight) {
        take = valueArray[currentIndex] + maxKnapsackRecursive(weightArray, valueArray, maxWeight - weightArray[currentIndex], currentIndex)
    }
    val notTake = maxKnapsackRecursive(weightArray, valueArray, maxWeight, currentIndex + 1)
    return maxOf(take, notTake)
}

private fun maxKnapsackDP(
    weightArray: Array<Int>,
    valueArray: Array<Int>,
    maxWeight: Int,
    currentIndex: Int,
    dpMap: MutableMap<String, Int>
): Int {
    if (maxWeight == 0 || currentIndex !in weightArray.indices) {
        return 0
    }
    if (dpMap.containsKey("$maxWeight-$currentIndex")) {
        return dpMap["$maxWeight-$currentIndex"]!!
    }
    var take = Int.MIN_VALUE
    if (weightArray[currentIndex] <= maxWeight) {
        take = valueArray[currentIndex] + maxKnapsackDP(weightArray, valueArray, maxWeight - weightArray[currentIndex], currentIndex, dpMap)
    }
    val notTake = maxKnapsackDP(weightArray, valueArray, maxWeight, currentIndex + 1, dpMap)
    dpMap["$maxWeight-$currentIndex"] = maxOf(take, notTake)
    return dpMap["$maxWeight-$currentIndex"]!!
}

private fun maxKnapsackTabulation(
    weightArray: Array<Int>,
    valueArray: Array<Int>,
    maxWeight: Int
): Int {
    val maxProfitMatrix: Array<Array<Int>> = Array(weightArray.size + 1) { row ->
        Array(maxWeight + 1) { column ->
            if (row == 0 || column == 0) {
                0
            } else {
                -1
            }
        }
    }
    for (row in 1 until maxProfitMatrix.size) {
        for (column in 1 until maxProfitMatrix[0].size) {
            if (row > column) {
                maxProfitMatrix[row][column] = maxProfitMatrix[row - 1][column]
            } else {
                maxProfitMatrix[row][column] = maxOf(valueArray[row - 1] + maxProfitMatrix[row][column - weightArray[row - 1]], maxProfitMatrix[row - 1][column])
            }
        }
    }
    return maxProfitMatrix[maxProfitMatrix.size - 1][maxProfitMatrix[0].size - 1]
}

private fun maxKnapsackTabulationOptimised(
    weightArray: Array<Int>,
    valueArray: Array<Int>,
    maxWeight: Int
): Int {
    val maxProfitMatrix: Array<Int> = Array(maxWeight + 1) { 0 }
    for (row in 1 until weightArray.size + 1) {
        for (column in 1 until maxWeight + 1) {
            if (column >= weightArray[row - 1]) {
                maxProfitMatrix[column] = maxOf(maxProfitMatrix[column], valueArray[row - 1] + maxProfitMatrix[column - weightArray[row - 1]])
            }
        }
    }
    return maxProfitMatrix[maxProfitMatrix.size - 1]
}

