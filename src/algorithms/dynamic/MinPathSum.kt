package algorithms.dynamic

import utils.printMatrix

// https://leetcode.com/problems/minimum-path-sum/description/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    minPathSum(
        arrayOf(
            arrayOf(1, 3, 1),
            arrayOf(1, 5, 1),
            arrayOf(4, 2, 1)
        )
    ).printMatrix()
    println("=========================")
    minPathSum(
        arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6)
        )
    ).printMatrix()
}

private fun minPathSum(matrix: Array<Array<Int>>): Array<Array<Int>> {
    for (row in matrix.indices) {
        abc@ for (column in matrix[0].indices) {
            if (column == 0 && row == 0) {
                continue@abc
            } else if (row - 1 < 0) {
                matrix[row][column] = matrix[row][column - 1] + matrix[row][column]
            } else if (column - 1 < 0) {
                matrix[row][column] = matrix[row - 1][column] + matrix[row][column]
            } else {
                matrix[row][column] =
                    (matrix[row - 1][column] + matrix[row][column]).coerceAtMost(matrix[row][column - 1] + matrix[row][column])
            }
        }
    }
    return matrix
}