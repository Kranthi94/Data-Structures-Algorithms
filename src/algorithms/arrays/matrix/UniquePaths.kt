package algorithms.arrays.matrix

// https://leetcode.com/problems/unique-paths/description/

fun main() {
    val rows = 3
    val columns = 7
    val array = Array(rows) {
        IntArray(columns) { 0 }
    }
    println("Unique Paths for $rows rows and $columns columns ===> ${uniquePaths(rows - 1, columns - 1, rows, columns, array)}")
}

private fun uniquePaths(currentRow: Int, currentColumn: Int, rows: Int, columns: Int, array: Array<IntArray>): Int {
    val rowInBounds = currentRow in 0..< rows
    val columnsInBounds = currentColumn in 0..< columns
    if (!rowInBounds || !columnsInBounds) {
        return 0
    }
    if (currentRow == 0 && currentColumn == 0) {
        return 1
    }
    if (array[currentRow][currentColumn] != 0) {
        return array[currentRow][currentColumn]
    }
    val result = uniquePaths(currentRow, currentColumn - 1, rows, columns, array) +
            uniquePaths(currentRow - 1, currentColumn, rows, columns, array)
    array[currentRow][currentColumn] = result
    return result
}