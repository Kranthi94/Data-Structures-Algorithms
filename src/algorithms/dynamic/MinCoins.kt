package algorithms.dynamic

import utils.printList

// https://www.naukri.com/code360/problems/minimum-elements_3843091

fun main() {
    minCoins(arrayOf(1, 2, 3), 7).printList()
    minCoins(arrayOf(12, 1, 3), 4).printList()
    minCoins(arrayOf(2, 1), 11).printList()
}

private fun minCoins(coins: Array<Int>, targetSum: Int): List<Int> {
    if (coins.isEmpty()) {
        return listOf(Int.MAX_VALUE)
    }
    if (targetSum == 0) {
        return listOf(0)
    }
    return listOf(
        minCoinsDP(coins, targetSum, coins.size - 1, Array(coins.size) {
            Array(targetSum + 1) {
                -1
            }
        }),
        minCoinsTabulations(coins, targetSum)
    )
}

 private fun minCoinsDP(coins: Array<Int>, targetSum: Int, currentIndex: Int, minCoinsMatrix: Array<Array<Int>>): Int {
     if (currentIndex == 0) {
         if (targetSum % coins[currentIndex] == 0) {
             return targetSum / coins[currentIndex]
         }
         return 10000
     }
     if (minCoinsMatrix[currentIndex][targetSum] != -1) {
         return minCoinsMatrix[currentIndex][targetSum]
     }
     var take = 10000
     if (coins[currentIndex] <= targetSum) {
         take = 1 + minCoinsDP(coins, targetSum - coins[currentIndex], currentIndex, minCoinsMatrix)
     }
     val notTake = minCoinsDP(coins, targetSum, currentIndex - 1, minCoinsMatrix)
     minCoinsMatrix[currentIndex][targetSum] = minOf(notTake, take)
     return minCoinsMatrix[currentIndex][targetSum]
 }

private fun minCoinsTabulations(coins: Array<Int>, targetSum: Int): Int {
    val minCoinsMatrix = Array(targetSum + 1) {
        if (it == 0) {
            0
        } else {
            10000
        }
    }
    for (row in coins.indices) {
        for (column in 1 until targetSum + 1) {
            if (column >= coins[row]) {
                minCoinsMatrix[column] = minOf(minCoinsMatrix[column], 1 + minCoinsMatrix[column - coins[row]])
            }
        }
    }
    return minCoinsMatrix[minCoinsMatrix.size - 1]
}