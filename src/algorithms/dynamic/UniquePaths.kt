package algorithms.dynamic

import utils.printValue

// https://leetcode.com/problems/unique-paths/?envType=study-plan-v2&envId=dynamic-programming
// https://leetcode.com/problems/unique-paths-ii/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    uniquePaths(3, 2).printValue()
    uniquePaths(3, 7).printValue()
    uniquePathsWithObstacles(
        arrayOf(
            arrayOf(0, 0, 0),
            arrayOf(0, 1, 0),
            arrayOf(0, 0, 0)
        )
    ).printValue()
    uniquePathsWithObstacles(
        arrayOf(
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 1, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 1, 0),
            arrayOf(0, 0, 0, 0)
        )
    ).printValue()
}

fun uniquePaths(m: Int, n: Int): Int {
    return uniquePaths(m - 1, n - 1, m, n)
}

private fun uniquePaths(currentRow: Int, currentColumn: Int, rows: Int, columns: Int): Int {
    val rowInBounds = currentRow in 0..<rows
    val columnsInBounds = currentColumn in 0..<columns
    if (!rowInBounds || !columnsInBounds) {
        return 0
    }
    if (currentRow == 0 && currentColumn == 0) {
        return 1
    }
    val result = uniquePaths(currentRow, currentColumn - 1, rows, columns) + uniquePaths(
        currentRow - 1,
        currentColumn,
        rows,
        columns
    )
    return result
}

fun uniquePathsWithObstacles(matrix: Array<Array<Int>>): Int {
    return uniquePathsWithObstacles(matrix.size - 1, matrix[0].size - 1, matrix)
}

private fun uniquePathsWithObstacles(
    currentRow: Int,
    currentColumn: Int,
    matrix: Array<Array<Int>>,
    dpArray: Array<IntArray>
): Int {
    val rowInBounds = currentRow in matrix.indices
    val columnsInBounds = currentColumn in matrix[0].indices
    if (!rowInBounds || !columnsInBounds) {
        return 0
    }
    if (dpArray[currentRow][currentColumn] != 0) {
        return dpArray[currentRow][currentColumn]
    }
    if (matrix[currentRow][currentColumn] == 1) {
        return 0
    }
    if (currentRow == 0 && currentColumn == 0) {
        return 1
    }
    val result = uniquePathsWithObstacles(
        currentRow,
        currentColumn - 1,
        matrix,
        dpArray
    ) + uniquePathsWithObstacles(currentRow - 1, currentColumn, matrix, dpArray)
    dpArray[currentRow][currentColumn] = result
    return result
}

private fun uniquePathsWithObstacles(
    currentRow: Int,
    currentColumn: Int,
    matrix: Array<Array<Int>>
): Int {
    val rowInBounds = currentRow in matrix.indices
    val columnsInBounds = currentColumn in matrix[0].indices
    if (!rowInBounds || !columnsInBounds) {
        return 0
    }
    if (matrix[currentRow][currentColumn] == 1) {
        return 0
    }
    if (currentRow == 0 && currentColumn == 0) {
        return 1
    }
    val result = uniquePathsWithObstacles(
        currentRow,
        currentColumn - 1,
        matrix
    ) + uniquePathsWithObstacles(currentRow - 1, currentColumn, matrix)
    matrix[currentRow][currentColumn] = result
    return result
}