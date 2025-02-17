package algorithms.recursion

import utils.printValue

// https://leetcode.com/problems/word-search/description/

fun main() {
    val rows = 3
    val columns = 4
    val array = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    val visitedArray = Array(rows) {
        Array(columns) {
            false
        }
    }
    wordSearch(array, "ABCCED", visitedArray).printValue()
    wordSearch(array, "SEE", visitedArray).printValue()
    wordSearch(array, "FD", visitedArray).printValue()
    wordSearch(array, "DX", visitedArray).printValue()
    wordSearch(array, "CCD", visitedArray).printValue()
    wordSearch(array, "CCE", visitedArray).printValue()
}

private fun wordSearch(array: Array<CharArray>, string: String, visitedArray: Array<Array<Boolean>>): Boolean {
    for (i in array.indices) {
        for (j in array[0].indices) {
            if (wordSearch(array, string, visitedArray, i, j, 0)) {
                return true
            }
        }
    }
    return false
}

private fun wordSearch(array: Array<CharArray>, string: String, visitedArray: Array<Array<Boolean>>, currentRow: Int, currentColumn: Int, index: Int): Boolean {
    if (index == string.length) {
        return true
    }
    if (currentRow !in array.indices || currentColumn !in array[0].indices || visitedArray[currentRow][currentColumn] || string[index] != array[currentRow][currentColumn]) {
        return false
    }
    visitedArray[currentRow][currentColumn] = true
    val ansFound: Boolean = wordSearch(array, string, visitedArray, currentRow, currentColumn - 1, index + 1)
            || wordSearch(array, string, visitedArray, currentRow - 1, currentColumn, index + 1)
            || wordSearch(array, string, visitedArray, currentRow, currentColumn + 1, index + 1)
            || wordSearch(array, string, visitedArray, currentRow + 1, currentColumn, index + 1)
    visitedArray[currentRow][currentColumn] = false
    return ansFound
}