package algorithms.dynamic.stocks

import utils.printValue

// https://www.naukri.com/code360/problems/stocks-are-profitable_893405

fun main() {
    maxProfit(arrayOf(2, 100, 150, 120)).printValue()
    maxProfit(arrayOf(1, 2, 3, 4)).printValue()
    maxProfit(arrayOf(2, 2, 2, 2)).printValue()
    maxProfit(arrayOf(17, 20, 11, 9, 12, 6)).printValue()
    maxProfit(arrayOf(98, 101, 66, 72)).printValue()
}

private fun maxProfit(stockPrices: Array<Int>): Int {
    if (stockPrices.isEmpty()) {
        return 0
    }
    if (stockPrices.size == 1) {
        return -1 * stockPrices[0]
    }
    return maxProfit(stockPrices, 1, stockPrices[0])
}

private fun maxProfit(stockPrices: Array<Int>, currentIndex: Int, minPrice: Int): Int {
    if (currentIndex == stockPrices.size - 1) {
        return stockPrices[currentIndex] - minPrice
    }
    val maxProfit = maxOf(stockPrices[currentIndex] - minPrice, maxProfit(stockPrices, currentIndex + 1, minOf(minPrice, stockPrices[currentIndex])))
    return maxProfit
}