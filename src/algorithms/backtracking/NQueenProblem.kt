package algorithms.backtracking

import utils.printMatrix
import utils.printValue

fun main() {
    val arrSize = 4
    val array = Array(arrSize) {
        Array(arrSize) {
            false
        }
    }
    placeNQueens(arrSize, 0, 0, array)
    placeNQueens(array, 0).printValue()
}

private fun placeNQueens(array: Array<Array<Boolean>>, currentRow: Int): Int {
    if (currentRow == array.size) {
        return 1
    }
    var count = 0
    for (currentColumn in array.indices) {
        val isSafe = checkLeftDiagonal(array, currentRow, currentColumn) && checkColumn(array, currentRow, currentColumn) && checkRightDiagonal(array, currentRow, currentColumn)
        if (isSafe) {
            array[currentRow][currentColumn] = true
            count += placeNQueens(array, currentRow + 1)
            array[currentRow][currentColumn] = false
        }
    }
    return count
}

private fun placeNQueens(n: Int, currentRow: Int, currentColumn: Int, array: Array<Array<Boolean>>): Boolean {
    if (currentRow == array.size) {
        if (n == 0) {
            array.printMatrix()
        }
        return true
    }
    if (currentColumn == array.size) {
        return false
    }
    val isSafe = checkLeftDiagonal(array, currentRow, currentColumn) && checkColumn(array, currentRow, currentColumn) && checkRightDiagonal(array, currentRow, currentColumn)
    if (isSafe) {
        array[currentRow][currentColumn] = true
        if (placeNQueens(n - 1, currentRow + 1, 0, array)) {
            return true
        }
        array[currentRow][currentColumn] = false
    }
    return placeNQueens(n, currentRow, currentColumn + 1, array)
}

private fun checkLeftDiagonal(array: Array<Array<Boolean>>, currentRow: Int, currentColumn: Int): Boolean {
    var leftRow = currentRow - 1
    var leftColumn = currentColumn - 1
    var leftDiagonalEmpty = true
    abc@ while (leftRow >= 0 && leftColumn >= 0) {
        if (array[leftRow][leftColumn]) {
            leftDiagonalEmpty = false
            break@abc
        }
        leftRow -= 1
        leftColumn -= 1
    }
    return leftDiagonalEmpty
}

private fun checkRightDiagonal(array: Array<Array<Boolean>>, currentRow: Int, currentColumn: Int): Boolean {
    var rightRow = currentRow - 1
    var rightColumn = currentColumn + 1
    var rightDiagonalEmpty = true
    abc@ while (rightRow >= 0 && rightColumn < array[0].size) {
        if (array[rightRow][rightColumn]) {
            rightDiagonalEmpty = false
            break@abc
        }
        rightRow -= 1
        rightColumn += 1
    }
    return rightDiagonalEmpty
}

private fun checkColumn(array: Array<Array<Boolean>>, currentRow: Int, currentColumn: Int): Boolean {
    var topRow = currentRow - 1
    var columnEmpty = true
    abc@ while (topRow >= 0) {
        if (array[topRow][currentColumn]) {
            columnEmpty = false
            break@abc
        }
        topRow--
    }
    return columnEmpty
}