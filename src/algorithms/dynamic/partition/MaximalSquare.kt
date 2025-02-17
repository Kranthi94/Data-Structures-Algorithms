package algorithms.dynamic.partition

import utils.printPair

// https://leetcode.com/problems/maximal-square/description/?envType=study-plan-v2&envId=dynamic-programming
// https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/

fun main() {
    val inputs = arrayOf(
        arrayOf(
            arrayOf(1, 0, 1, 0, 0),
            arrayOf(1, 0, 1, 1, 1),
            arrayOf(1, 1, 1, 1, 1),
            arrayOf(1, 0, 0, 1, 0)
        ),
        arrayOf(
            arrayOf(0, 1),
            arrayOf(1, 0)
        ),
        arrayOf(
            arrayOf(0),
            arrayOf(1),
        ),
        arrayOf(
            arrayOf(0, 0, 1, 0),
            arrayOf(1, 1, 1, 1),
            arrayOf(1, 1, 1, 1),
            arrayOf(1, 1, 1, 0),
            arrayOf(1, 1, 0, 0),
            arrayOf(1, 1, 1, 1),
            arrayOf(1, 1, 1, 0),
        ),
        arrayOf(
            arrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            arrayOf(1, 1, 1, 1, 1, 1, 1, 0),
            arrayOf(1, 1, 1, 1, 1, 1, 1, 0),
            arrayOf(1, 1, 1, 1, 1, 0, 0, 0),
            arrayOf(0, 1, 1, 1, 1, 0, 0, 0),
        )
    )
    inputs.forEach {
        maximalSquare(it).printPair()
    }
}

private fun maximalSquare(matrix: Array<Array<Int>>): Pair<Int, Int> {
    return Pair(
        maximalSquare1(matrix),
        maximalSquare2(matrix)
    )
}

// Most Optimised
private fun maximalSquare2(matrix: Array<Array<Int>>): Int {
    val dpMatrix = Array(matrix.size) {
        Array(matrix[0].size) {
            -1
        }
    }
    val maxSquareSizeList: MutableList<Int> = MutableList(1) { Int.MIN_VALUE }
    maximalSquare2(matrix, 0, 0, dpMatrix, maxSquareSizeList)
    return maxSquareSizeList[0] * maxSquareSizeList[0]
}

private fun maximalSquare2(matrix: Array<Array<Int>>, currentRow: Int, currentColumn: Int, dpMatrix: Array<Array<Int>>, maxSquareSizeList: MutableList<Int>): Int {
    if (currentRow !in matrix.indices || currentColumn !in matrix[0].indices) {
        return 0
    }
    if (dpMatrix[currentRow][currentColumn] != -1) {
        return dpMatrix[currentRow][currentColumn]
    }

    val maxRight = maximalSquare2(matrix, currentRow, currentColumn + 1, dpMatrix, maxSquareSizeList)
    val maxDiagonal = maximalSquare2(matrix, currentRow + 1, currentColumn + 1, dpMatrix, maxSquareSizeList)
    val maxDown = maximalSquare2(matrix, currentRow + 1, currentColumn, dpMatrix, maxSquareSizeList)
    if (matrix[currentRow][currentColumn] == 0) {
        dpMatrix[currentRow][currentColumn] = 0
    } else {
        dpMatrix[currentRow][currentColumn] = 1 + minOf(maxRight, minOf(maxDiagonal, maxDown))
    }
    maxSquareSizeList[0] = maxOf(maxSquareSizeList[0], dpMatrix[currentRow][currentColumn])
    return dpMatrix[currentRow][currentColumn]
}

private fun maximalSquare1(matrix: Array<Array<Int>>): Int {
    val dpMatrix = maxDownMatrix(matrix)
    var maxSquareSize = Int.MIN_VALUE
    for (row in matrix.indices) {
        for (column in matrix[0].indices) {
            maxSquareSize = maxSquareSize.coerceAtLeast(maximalSquare1(matrix, row, column, dpMatrix))
        }
    }
    return maxSquareSize * maxSquareSize
}

private fun maximalSquare1(
    matrix: Array<Array<Int>>,
    currentRow: Int,
    currentColumn: Int,
    dpMatrix: Array<Array<Int>>
): Int {
    var minDown = dpMatrix[currentRow][currentColumn]
    return if (minDown == 0 || minDown == 1) {
        minDown
    } else {
        var maxSquareSize = 1
        var count = 1
        var column = currentColumn + 1
        while (column in matrix[0].indices) {
            if (dpMatrix[currentRow][column] == 0) {
                break
            }
            minDown = minDown.coerceAtMost(dpMatrix[currentRow][column])
            count += 1
            column += 1
            maxSquareSize = maxSquareSize.coerceAtLeast(count.coerceAtMost(minDown))
        }
        return maxSquareSize
    }
}

private fun maxDownMatrix(
    matrix: Array<Array<Int>>,
): Array<Array<Int>> {
    val dpMatrix = Array(matrix.size) {
        Array(matrix[0].size) {
            -1
        }
    }
    for (column in matrix[0].indices) {
        maxDownMatrix(matrix, 0, column, dpMatrix)
    }
    return dpMatrix
}

private fun maxDownMatrix(
    matrix: Array<Array<Int>>,
    currentRow: Int,
    currentColumn: Int,
    dpMatrix: Array<Array<Int>>
): Int {
    if (currentRow !in matrix.indices || currentColumn !in matrix[0].indices) {
        return 0
    }
    if (dpMatrix[currentRow][currentColumn] != -1) {
        return dpMatrix[currentRow][currentColumn]
    }
    val maxDown = maxDownMatrix(matrix, currentRow + 1, currentColumn, dpMatrix)
    if (matrix[currentRow][currentColumn] == 0) {
        dpMatrix[currentRow][currentColumn] = 0
    } else {
        dpMatrix[currentRow][currentColumn] = maxDown + 1
    }
    return dpMatrix[currentRow][currentColumn]
}