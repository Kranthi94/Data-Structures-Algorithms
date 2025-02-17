package algorithms.backtracking

import utils.printValue
import utils.printMatrix

fun main() {
    val arrSize = 3
    val array = Array(arrSize) {
        Array(arrSize) {
            false
        }
    }
    placeNKnights(array, 0, 0, arrSize).printValue()
}

private fun placeNKnights(array: Array<Array<Boolean>>, currentRow: Int, currentColumn: Int, knights: Int): Int {
    if (knights == 0) {
        array.printMatrix()
        println("-------------------------------------------------------")
        return 1
    }
    if (currentRow == array.size) {
        return 0
    }
    if (currentColumn == array[0].size) {
        return placeNKnights(array, currentRow + 1, 0, knights)
    }
    var count = 0
    val isSafe = isSafe(array, currentRow, currentColumn)
    if (isSafe) {
        array[currentRow][currentColumn] = true
        count += placeNKnights(array, currentRow, currentColumn + 1, knights - 1)
        array[currentRow][currentColumn] = false
    }
    count += placeNKnights(array, currentRow, currentColumn + 1, knights)
    return count
}

private fun isSafe(array: Array<Array<Boolean>>, row: Int, column: Int): Boolean {
    var isSafe = true
    val diff = arrayOf(
        arrayOf(-1, -2),
        arrayOf(-2, -1),
        arrayOf(-2, 1),
        arrayOf(-1, 2)
    )
    abc@ for (i in diff.indices) {
        val arr = diff[i]
        val aRow = row + arr[0]
        val aCol = column + arr[1]
        val aRowInBounds = aRow >= 0 && aRow < array.size
        val aColumnInBounds = aCol >= 0 && aCol < array[0].size
        if (aRowInBounds && aColumnInBounds && array[aRow][aCol]) {
            isSafe = false
            break@abc
        }
    }
    return isSafe
}