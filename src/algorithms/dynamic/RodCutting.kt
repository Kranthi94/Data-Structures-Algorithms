package algorithms.dynamic

import utils.printPair

fun main() {
    maxRodCutPrice(5, mutableMapOf(1 to 1, 2 to 2, 3 to 3, 4 to 4, 5 to 5)).printPair()
    maxRodCutPrice(5, mutableMapOf(1 to 1, 2 to 3, 3 to 5, 4 to 7, 5 to 9)).printPair()
    maxRodCutPrice(8, mutableMapOf(1 to 1, 2 to 5, 3 to 8, 4 to 9, 5 to 10, 6 to 17, 7 to 17, 8 to 20)).printPair()
}

private fun maxRodCutPrice(length: Int, priceArray: Map<Int, Int>): Pair<Int, Int> {
    return Pair(
        maxRodCutPriceDP(length, priceArray, mutableMapOf()),
        maxRodCutPriceTabulation(length, priceArray)
    )
}

private fun maxRodCutPriceDP(length: Int, priceArray: Map<Int, Int>, maxPriceArray: MutableMap<Int, Int>): Int {
    if (length <= 0) {
        return 0
    }
    if (length == 1) {
        return priceArray[1]!!
    }
    if (maxPriceArray[length] != null) {
        return maxPriceArray[length]!!
    }
    var maxPrice = 0
    for (i in 1 until length) {
        maxPriceArray[i] = maxRodCutPriceDP(i, priceArray, maxPriceArray)
        maxPriceArray[length - i] = maxRodCutPriceDP(length - i, priceArray, maxPriceArray)
        maxPrice = maxPrice.coerceAtLeast(maxPriceArray[i]!! + maxPriceArray[length - i]!!)
    }
    maxPriceArray[length] = maxPrice.coerceAtLeast(priceArray[length]!!)
    return maxPriceArray[length]!!
}

private fun maxRodCutPriceTabulation(length: Int, priceArray: Map<Int, Int>): Int {
    val maxPriceMatrix: Array<Array<Int>> = Array(priceArray.size + 1) { row ->
        Array(length + 1) { column ->
            if (column == 0) {
                0
            } else if (row == 0) {
                priceArray[row] ?: 0
            } else {
                -1
            }
        }
    }
    for (row in 1 until maxPriceMatrix.size) {
        for (column in 1 until maxPriceMatrix[0].size) {
            if (row > column) {
                maxPriceMatrix[row][column] = maxPriceMatrix[row - 1][column]
            } else {
                maxPriceMatrix[row][column] = maxOf(priceArray[row]!! + maxPriceMatrix[row - 1][column - row], maxPriceMatrix[row - 1][column])
            }
        }
    }
    return maxPriceMatrix[priceArray.size][length]
}