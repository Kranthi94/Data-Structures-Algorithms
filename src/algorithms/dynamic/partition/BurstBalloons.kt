package algorithms.dynamic.partition

import utils.printValue

// https://www.naukri.com/code360/problems/mining-diamonds_4244494

fun main() {
    val inputs = arrayOf(arrayOf(), arrayOf(7, 1, 8), arrayOf(9, 1), arrayOf(1, 2, 3, 4, 5), arrayOf(1, 5, 2, 8))
    inputs.forEach {
        val array: Array<Int> = arrayOf<Int>().plus(1).plus(it.copyOfRange(0, it.size)).plus(1)
        burstBalloons(array).printValue()
    }
}

private fun burstBalloons(array: Array<Int>): List<Int> {
    if (array.isEmpty()) {
        return listOf(0, 0)
    }
    return listOf(
        burstBalloonsDP(array, 1, array.size - 2, Array(array.size) { Array(array.size) { 0 } }),
        burstBalloonsTabulation(array)
    )
}

private fun burstBalloonsDP(array: Array<Int>, startIndex: Int, endIndex: Int, dpMatrix: Array<Array<Int>>): Int {
    if (startIndex > endIndex) {
        return 0
    }
    if (dpMatrix[startIndex][endIndex] != 0) {
        return dpMatrix[startIndex][endIndex]
    }
    var maxCoins = 0
    for (i in startIndex until endIndex + 1) {
        val currentCoins = array[startIndex - 1] * array[i] * array[endIndex + 1]
        val leftCoins = burstBalloonsDP(array, startIndex, i - 1, dpMatrix)
        val rightCoins = burstBalloonsDP(array, i + 1, endIndex, dpMatrix)
        maxCoins = maxOf(maxCoins, currentCoins + leftCoins + rightCoins)
    }
    dpMatrix[startIndex][endIndex] = maxCoins
    return maxCoins
}

private fun burstBalloonsTabulation(array: Array<Int>): Int {
    val matrix: Array<Array<Int>> = Array(array.size) {
        Array(array.size) {
            0
        }
    }
    for (row in array.size - 2 downTo 1) {
        for (column in 1 until array.size - 1) {
            if (row > column) {
                continue
            }
            var maxCoins = 0
            for (i in row until column + 1) {
                val currentCoins = array[row - 1] * array[i] * array[column + 1]
                val leftCoins = matrix[row][i - 1]
                val rightCoins = matrix[i + 1][column]
                maxCoins = maxOf(maxCoins, currentCoins + leftCoins + rightCoins)
            }
            matrix[row][column] = maxCoins
        }
    }
    return matrix[1][array.size - 2]
}