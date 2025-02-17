package algorithms.backtracking

import utils.printList

fun main() {
    val row = 3
    val column = 3
    findAllPossiblePaths(
        Array(row) {
            Array(column) {
                false
            }
        },
        0, 0
    ).printList()
}

private fun findAllPossiblePaths(array: Array<Array<Boolean>>, currentRow: Int, currentColumn: Int): List<String> {
    if (currentRow < 0 || currentColumn < 0 || currentRow == array.size || currentColumn == array[0].size ) {
        return arrayListOf()
    }
    if (currentRow == array.size - 1 && currentColumn == array[0].size - 1) {
        return arrayListOf("($currentRow, $currentColumn)")
    }
    if (array[currentRow][currentColumn]) {
        return arrayListOf()
    }
    array[currentRow][currentColumn] = true
    val left = findAllPossiblePaths(array, currentRow, currentColumn - 1)
    val top = findAllPossiblePaths(array, currentRow - 1, currentColumn)
    val right = findAllPossiblePaths(array, currentRow, currentColumn + 1)
    val bottom = findAllPossiblePaths(array, currentRow + 1, currentColumn)
    val newLeft = arrayListOf<String>()
    left.forEach {
        newLeft.add("($currentRow, $currentColumn) -> $it")
    }
    val newTop = arrayListOf<String>()
    top.forEach {
        newTop.add("($currentRow, $currentColumn) -> $it")
    }
    val newRight = arrayListOf<String>()
    right.forEach {
        newRight.add("($currentRow, $currentColumn) -> $it")
    }
    val newBottom = arrayListOf<String>()
    bottom.forEach {
        newBottom.add("($currentRow, $currentColumn) -> $it")
    }
    array[currentRow][currentColumn] = false
    return newLeft + newTop + newRight + newBottom
}