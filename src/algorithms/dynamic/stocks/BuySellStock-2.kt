package algorithms.dynamic.stocks

import utils.printList

// https://www.naukri.com/code360/problems/selling-stock_630282

fun main() {
    maxProfit(arrayOf(1, 2, 3, 4, 5, 6, 7)).printList()
    maxProfit(arrayOf(7, 6, 5, 4, 3, 2, 1)).printList()
}

private fun maxProfit(stockPrices: Array<Int>): List<Int> {
    if (stockPrices.isEmpty()) {
        return listOf(0, 0)
    }
    return listOf(
        maxProfitDP(stockPrices, 0, true, Array(stockPrices.size) { Array(2) { -1 } }),
        maxProfitTabulation(stockPrices)
    )
}

private fun maxProfitDP(stockPrices: Array<Int>, currentIndex: Int, buy: Boolean, dpMatrix: Array<Array<Int>>): Int {
    if (currentIndex !in stockPrices.indices) {
        return 0
    }
    if (dpMatrix[currentIndex][if (buy) 1 else 0] != -1) {
        return dpMatrix[currentIndex][if (buy) 1 else 0]
    }
    if (buy) {
        dpMatrix[currentIndex][1] = maxOf(-stockPrices[currentIndex] + maxProfitDP(stockPrices, currentIndex + 1, false, dpMatrix), maxProfitDP(stockPrices, currentIndex + 1, true, dpMatrix))
    } else {
        dpMatrix[currentIndex][0] = maxOf(stockPrices[currentIndex] + maxProfitDP(stockPrices, currentIndex + 1, true, dpMatrix), maxProfitDP(stockPrices, currentIndex + 1, false, dpMatrix))
    }
    return dpMatrix[currentIndex][if (buy) 1 else 0]
}

private fun maxProfitTabulation(stockPrices: Array<Int>): Int {
    var aheadRow = Array(2) {
        0
    }
    val currentRow = Array(2) {
        0
    }
    for (row in stockPrices.indices.reversed()) {
        currentRow[0] = maxOf(-stockPrices[row] + aheadRow[1], aheadRow[0])
        currentRow[1] = maxOf(stockPrices[row] + aheadRow[0], aheadRow[1])
        aheadRow = currentRow.clone()
    }
    return aheadRow[0]
}