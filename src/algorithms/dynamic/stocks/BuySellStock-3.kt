package algorithms.dynamic.stocks

import utils.printList

// https://www.naukri.com/code360/problems/buy-and-sell-stock_1071012

fun main() {
    maxProfit(arrayOf(3, 3, 5, 0, 3, 1, 4), 2).printList()
    maxProfit(arrayOf(1, 3, 1, 2, 4, 8), 2).printList()
    maxProfit(arrayOf(5, 4, 3, 2, 1), 2).printList()
}

private fun maxProfit(stockPrices: Array<Int>, maxTransactions: Int): List<Int> {
    if (stockPrices.isEmpty()) {
        return listOf(0)
    }
    return listOf(
        maxProfitDP(stockPrices, 0, 2 * maxTransactions, Array(stockPrices.size) { Array(2 * maxTransactions + 1) { -1 } }),
        maxProfitTabulation(stockPrices, 2 * maxTransactions)
    )
}

private fun maxProfitDP(stockPrices: Array<Int>, currentIndex: Int, maxTransactions: Int, dpMatrix: Array<Array<Int>>): Int {
    if (maxTransactions == 0 || currentIndex !in stockPrices.indices) {
        return 0
    }
    if (dpMatrix[currentIndex][maxTransactions] != -1) {
        return dpMatrix[currentIndex][maxTransactions]
    }
    val result = if (maxTransactions % 2 == 0) {
        maxOf(-stockPrices[currentIndex] + maxProfitDP(stockPrices, currentIndex + 1, maxTransactions - 1, dpMatrix), maxProfitDP(stockPrices, currentIndex + 1, maxTransactions, dpMatrix))
    } else {
        maxOf(stockPrices[currentIndex] + maxProfitDP(stockPrices, currentIndex + 1, maxTransactions - 1, dpMatrix), maxProfitDP(stockPrices, currentIndex + 1, maxTransactions, dpMatrix))
    }
    dpMatrix[currentIndex][maxTransactions] = result
    return result
}

// Check Why column % 2 == 0 is Buy

private fun maxProfitTabulation(stockPrices: Array<Int>, maxTransactions: Int): Int {
    var aheadRow = Array(maxTransactions + 1) {
        0
    }
    val currentRow = Array(maxTransactions + 1) {
        0
    }
    for (row in stockPrices.indices.reversed()) {
        for (column in 1 until maxTransactions + 1) {
            if (column % 2 == 0) {
                currentRow[column] = maxOf(-stockPrices[row] + aheadRow[column - 1], aheadRow[column])
            } else {
                currentRow[column] = maxOf(stockPrices[row] + aheadRow[column - 1], aheadRow[column])
            }
        }
        aheadRow = currentRow.clone()
    }
    return aheadRow[maxTransactions]
}