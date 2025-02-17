package algorithms.dynamic

import utils.printMatrix

// https://leetcode.com/problems/minimum-falling-path-sum/description/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    findMinFallingPathSum(
        arrayOf(
            arrayOf(2, 1, 3),
            arrayOf(6, 5, 4),
            arrayOf(7, 8, 9)
        )
    ).printMatrix()
}

private fun findMinFallingPathSum(matrix: Array<Array<Int>>): Array<Array<Int>> {
    if (matrix.size == 1) {
        return matrix
    }
    1.coerceAtMost(2)
    for (row in matrix.size - 2 downTo 0) {
        for (column in matrix[0].indices) {
            matrix[row][column] = matrix[row][column] + getMinValue(row, column, matrix)
        }
    }
    return matrix
}

private fun getMinValue(currentRow: Int, currentColumn: Int, matrix: Array<Array<Int>>): Int {
    val subArray = arrayOf(arrayOf(1, -1), arrayOf(1, 0), arrayOf(1, 1))
    var minValue = Int.MAX_VALUE
    subArray.forEach { arr ->
        val newRow = currentRow + arr[0]
        val newColumn = currentColumn + arr[1]
        if (newRow in matrix.indices && newColumn in matrix[0].indices) {
            minValue = minValue.coerceAtMost(matrix[newRow][newColumn])
        }
    }
    return if (minValue != Int.MAX_VALUE) minValue else 0
}