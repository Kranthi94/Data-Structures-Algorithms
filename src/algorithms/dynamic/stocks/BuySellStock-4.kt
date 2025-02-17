package algorithms.dynamic.stocks

import utils.printList

// https://www.naukri.com/code360/problems/highway-billboards_3125969

fun main() {
    maxProfit(arrayOf(4, 9, 0, 4, 10)).printList()
    maxProfit(arrayOf(1, 2, 3, 4)).printList()
    maxProfit(arrayOf(5, 4, 3)).printList()
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
        dpMatrix[currentIndex][0] = maxOf(stockPrices[currentIndex] + maxProfitDP(stockPrices, currentIndex + 2, true, dpMatrix), maxProfitDP(stockPrices, currentIndex + 1, false, dpMatrix))
    }
    return dpMatrix[currentIndex][if (buy) 1 else 0]
}

private fun maxProfitTabulation(stockPrices: Array<Int>): Int {
    var aheadRow1 = Array(2) {
        0
    }
    var aheadRow2 = Array(2) {
        0
    }
    val currentRow = Array(2) {
        0
    }
    for (row in stockPrices.indices.reversed()) {
        currentRow[0] = maxOf(-stockPrices[row] + aheadRow1[1], aheadRow1[0])
        currentRow[1] = maxOf(stockPrices[row] + aheadRow2[0], aheadRow1[1])
        aheadRow2 = aheadRow1.clone()
        aheadRow1 = currentRow.clone()
    }
    return aheadRow1[0]
}