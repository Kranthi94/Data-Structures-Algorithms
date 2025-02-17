package algorithms.backtracking

import utils.printMatrix
import kotlin.math.sqrt

fun main() {
    val array = arrayOf(
        arrayOf(3, 0, 6, 5, 0, 8, 4, 0, 0),
        arrayOf(5, 2, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 8, 7, 0, 0, 0, 0, 3, 1),
        arrayOf(0, 0, 3, 0, 1, 0, 0, 8, 0),
        arrayOf(9, 0, 0, 8, 6, 3, 0, 0, 5),
        arrayOf(0, 5, 0, 0, 9, 0, 6, 0, 0),
        arrayOf(1, 3, 0, 0, 0, 0, 2, 5, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 7, 4),
        arrayOf(0, 0, 5, 2, 0, 6, 3, 0, 0),
    )
    if (solveSudoku(array)) {
        println("Sudoku is Solved")
    } else {
        println("Sudoku can't be solved")
    }
    println()
    array.printMatrix()
}

private fun solveSudoku(array: Array<Array<Int>>): Boolean {
    var currentRow = -1
    var currentColumn = -1
    var emptyCellsPresent = false

    abc@ for (i in array.indices) {
        for (j in array[0].indices) {
            if (array[i][j] == 0) {
                currentRow = i
                currentColumn = j
                emptyCellsPresent = true
                break@abc
            }
        }
    }
    if (!emptyCellsPresent) {
        return true
    }

    for (i in 1 until 10) {
        if (isSafe(array, currentRow, currentColumn, i)) {
            array[currentRow][currentColumn] = i
            if (solveSudoku(array)) {
                return true
            } else {
                array[currentRow][currentColumn] = 0
            }
        }
    }
    return false
}

private fun isSafe(array: Array<Array<Int>>, row: Int, column: Int, target: Int): Boolean {
    for (i in array[0].indices) {
        if (array[row][i] == target) {
            return false
        }
    }
    for (i in array.indices) {
        if (array[i][column] == target) {
            return false
        }
    }
    val sqrt = sqrt(array.size.toDouble()).toInt()
    val rowStart = row - row % sqrt
    val colStart = column - column % sqrt
    for (i in rowStart until rowStart + sqrt) {
        for (j in colStart until colStart + sqrt) {
            if (array[i][j] == target) {
                return false
            }
        }
    }
    return true
}