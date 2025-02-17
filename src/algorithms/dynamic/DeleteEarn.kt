package algorithms.dynamic

import utils.printValue

// https://leetcode.com/problems/delete-and-earn/description/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    deleteAndEarn(mutableListOf(3, 4, 2)).printValue()
    deleteAndEarn(mutableListOf(2, 2, 3, 3, 3, 4)).printValue()
    deleteAndEarn(mutableListOf(8, 3, 4, 7, 6, 6, 9, 2, 5, 8, 2, 4, 9, 5, 9, 1, 5, 7, 1, 4)).printValue()
    mutableListOf(8, 3, 4, 7, 6, 6, 9, 2, 5, 8, 2, 4, 9, 5, 9, 1, 5, 7, 1, 4).sum().printValue()
}

private fun deleteAndEarn(numbersArray: MutableList<Int>): Int {
    var maxCost = Int.MIN_VALUE
    val costMap: MutableMap<Int, Int> = mutableMapOf()
    for (i in numbersArray.indices) {
        val newList = numbersArray.toMutableList()
        maxCost = maxCost.coerceAtLeast(deleteAndEarn(newList, newList.size, i, costMap))
    }
    return maxCost
}

private fun deleteAndEarn(
    numbersList: MutableList<Int>,
    numbersListSize: Int,
    currentIndex: Int,
    costMap: MutableMap<Int, Int>
): Int {
    if (numbersList.isEmpty()) {
        return 0
    }
    if (numbersList.size == 1) {
        return numbersList[0]
    }
    val currentValue = numbersList.removeAt(currentIndex)
    if (costMap[currentValue] != null && numbersList.size == numbersListSize) {
        return costMap[currentValue]!!
    }
    val newList: MutableList<Int> = mutableListOf()
    var maxCost = 0
    numbersList.forEach {
        if (it != currentValue - 1 && it != currentValue + 1) {
            newList.add(it)
        }
    }
    for (i in newList.indices) {
        val list = newList.toMutableList()
        maxCost = maxCost.coerceAtLeast(deleteAndEarn(list, numbersListSize, i, costMap))
    }
    maxCost += currentValue
    costMap[currentValue] = costMap.getOrDefault(currentValue, 0).coerceAtLeast(maxCost)
    return maxCost
}