package algorithms.recursion

import utils.getIntMatrix
import utils.getStringListMatrix
import utils.printValue
import utils.printList

fun main() {
    var rows = 3
    var columns = 3
    reachMazeEndDownAndRight(rows - 1, columns - 1).printValue()
    reachMazeEndDownAndRightWithDP(rows - 1, columns - 1, getIntMatrix(rows, columns)).printValue()
    reachMazeEndDownRightAndDiagonal(rows - 1, columns - 1).printValue()
    reachMazeEndDownRightAndDiagonalWithDP(rows - 1, columns - 1, getIntMatrix(rows, columns)).printValue()

    reachMazeEndPathsDownAndRight(rows - 1, columns - 1).printList()
    reachMazeEndPathsDownAndRightWithDP(rows - 1, columns - 1, getStringListMatrix(rows, columns)).printList()
    reachMazeEndPathsDownRightAndDiagonal(rows - 1, columns - 1).printList()
    reachMazeEndPathsDownRightAndDiagonalWithDP(rows - 1, columns - 1, getStringListMatrix(rows, columns)).printList()

    println()
    println("##################################################################")
    println()

    rows = 4
    columns = 3

    reachMazeEndDownAndRight(rows - 1, columns - 1).printValue()
    reachMazeEndDownAndRightWithDP(rows - 1, columns - 1, getIntMatrix(rows, columns)).printValue()
    reachMazeEndDownRightAndDiagonal(rows - 1, columns - 1).printValue()
    reachMazeEndDownRightAndDiagonalWithDP(rows - 1, columns - 1, getIntMatrix(rows, columns)).printValue()

    reachMazeEndPathsDownAndRight(rows - 1, columns - 1).printList()
    reachMazeEndPathsDownAndRightWithDP(rows - 1, columns - 1, getStringListMatrix(rows, columns)).printList()
    reachMazeEndPathsDownRightAndDiagonal(rows - 1, columns - 1).printList()
    reachMazeEndPathsDownRightAndDiagonalWithDP(rows - 1, columns - 1, getStringListMatrix(rows, columns)).printList()

    println()
    println("##################################################################")
    println()
    rows = 3
    columns = 3
    mazeWithObstacles(Array(rows) {
        Array(columns) {
            it % 2
        }
    }, rows - 1, columns - 1).printValue()
}

private fun reachMazeEndDownAndRight(currentRow: Int, currentColumn: Int): Int {
    if (currentRow == 0 || currentColumn == 0) {
        return 1
    }
    return reachMazeEndDownAndRight(currentRow, currentColumn - 1) + reachMazeEndDownAndRight(currentRow - 1, currentColumn)
}

private fun reachMazeEndDownAndRightWithDP(currentRow: Int, currentColumn: Int, array: Array<Array<Int>>): Int {
    if (currentRow == 0 || currentColumn == 0) {
        return 1
    }
    if (array[currentRow][currentColumn] != -1) {
        return array[currentRow][currentColumn]
    }
    array[currentRow][currentColumn - 1] = reachMazeEndDownAndRightWithDP(currentRow, currentColumn - 1, array)
    array[currentRow - 1][currentColumn] = reachMazeEndDownAndRightWithDP(currentRow - 1, currentColumn, array)
    return array[currentRow][currentColumn - 1] + array[currentRow - 1][currentColumn]
}

private fun reachMazeEndPathsDownAndRight(currentRow: Int, currentColumn: Int): List<String> {
    if (currentRow < 0 || currentColumn < 0) {
        return arrayListOf()
    }
    if (currentRow == 0 && currentColumn == 0) {
        return arrayListOf("(0, 0)")
    }
    val left = reachMazeEndPathsDownAndRight(currentRow, currentColumn - 1)
    val top = reachMazeEndPathsDownAndRight(currentRow - 1, currentColumn)
    val newLeft = arrayListOf<String>()
    left.forEach {
        newLeft.add("$it -> ($currentRow, $currentColumn)")
    }
    val newTop = arrayListOf<String>()
    top.forEach {
        newTop.add("$it -> ($currentRow, $currentColumn)")
    }
    return newLeft + newTop
}

private fun reachMazeEndPathsDownAndRightWithDP(currentRow: Int, currentColumn: Int, array: Array<Array<List<String>>>): List<String> {
    if (currentRow < 0 || currentColumn < 0) {
        return arrayListOf()
    }
    if (currentRow == 0 && currentColumn == 0) {
        return arrayListOf("(0, 0)")
    }
    if (array[currentRow][currentColumn].isNotEmpty()) {
        return array[currentRow][currentColumn]
    }
    val left = reachMazeEndPathsDownAndRight(currentRow, currentColumn - 1)
    val top = reachMazeEndPathsDownAndRight(currentRow - 1, currentColumn)
    val newLeft = arrayListOf<String>()
    left.forEach {
        newLeft.add("$it -> ($currentRow, $currentColumn)")
    }
    val newTop = arrayListOf<String>()
    top.forEach {
        newTop.add("$it -> ($currentRow, $currentColumn)")
    }
    array[currentRow][currentColumn - 1] = left
    array[currentRow - 1][currentColumn] = top
    array[currentRow][currentColumn] = newLeft + newTop
    return array[currentRow][currentColumn]
}

private fun reachMazeEndDownRightAndDiagonal(currentRow: Int, currentColumn: Int): Int {
    if (currentRow == 0 || currentColumn == 0) {
        return 1
    }
    return reachMazeEndDownRightAndDiagonal(currentRow, currentColumn - 1) +
            reachMazeEndDownRightAndDiagonal(currentRow - 1, currentColumn) + reachMazeEndDownRightAndDiagonal(currentRow - 1, currentColumn - 1)
}

private fun reachMazeEndDownRightAndDiagonalWithDP(currentRow: Int, currentColumn: Int, array: Array<Array<Int>>): Int {
    if (currentRow == 0 || currentColumn == 0) {
        return 1
    }
    if (array[currentRow][currentColumn] != -1) {
        return array[currentRow][currentColumn]
    }
    array[currentRow][currentColumn - 1] = reachMazeEndDownRightAndDiagonal(currentRow, currentColumn - 1)
    array[currentRow - 1][currentColumn] = reachMazeEndDownRightAndDiagonal(currentRow - 1, currentColumn)
    array[currentRow - 1][currentColumn - 1] = reachMazeEndDownRightAndDiagonal(currentRow - 1, currentColumn - 1)
    return  array[currentRow][currentColumn - 1] + array[currentRow - 1][currentColumn] + array[currentRow - 1][currentColumn - 1]
}

private fun reachMazeEndPathsDownRightAndDiagonal(currentRow: Int, currentColumn: Int): List<String> {
    if (currentRow < 0 || currentColumn < 0) {
        return arrayListOf()
    }
    if (currentRow == 0 && currentColumn == 0) {
        return arrayListOf("(0, 0)")
    }
    val left = reachMazeEndPathsDownRightAndDiagonal(currentRow, currentColumn - 1)
    val top = reachMazeEndPathsDownRightAndDiagonal(currentRow - 1, currentColumn)
    val diagonal = reachMazeEndPathsDownRightAndDiagonal(currentRow - 1, currentColumn - 1)
    val newLeft = arrayListOf<String>()
    left.forEach {
        newLeft.add("$it -> ($currentRow, $currentColumn)")
    }
    val newTop = arrayListOf<String>()
    top.forEach {
        newTop.add("$it -> ($currentRow, $currentColumn)")
    }
    val newDiagonal = arrayListOf<String>()
    diagonal.forEach {
        newDiagonal.add("$it -> ($currentRow, $currentColumn)")
    }
    return newLeft + newTop + newDiagonal
}

private fun reachMazeEndPathsDownRightAndDiagonalWithDP(currentRow: Int, currentColumn: Int, array: Array<Array<List<String>>>): List<String> {
    if (currentRow < 0 || currentColumn < 0) {
        return arrayListOf()
    }
    if (currentRow == 0 && currentColumn == 0) {
        return arrayListOf("(0, 0)")
    }
    if (array[currentRow][currentColumn].isNotEmpty()) {
        return array[currentRow][currentColumn]
    }
    val left = reachMazeEndPathsDownRightAndDiagonal(currentRow, currentColumn - 1)
    val top = reachMazeEndPathsDownRightAndDiagonal(currentRow - 1, currentColumn)
    val diagonal = reachMazeEndPathsDownRightAndDiagonal(currentRow - 1, currentColumn - 1)
    val newLeft = arrayListOf<String>()
    left.forEach {
        newLeft.add("$it -> ($currentRow, $currentColumn)")
    }
    val newTop = arrayListOf<String>()
    top.forEach {
        newTop.add("$it -> ($currentRow, $currentColumn)")
    }
    val newDiagonal = arrayListOf<String>()
    diagonal.forEach {
        newDiagonal.add("$it -> ($currentRow, $currentColumn)")
    }
    array[currentRow][currentColumn - 1] = left
    array[currentRow - 1][currentColumn] = top
    array[currentRow - 1][currentColumn - 1] = diagonal
    array[currentRow][currentColumn] = newLeft + newTop + newDiagonal
    return array[currentRow][currentColumn]
}

private fun mazeWithObstacles(arr: Array<Array<Int>>, currentRow: Int, currentColumn: Int): Int {
    if (currentRow < 0 || currentColumn < 0) {
        return 0
    }
    if (currentRow == 0 && currentColumn == 0) {
        return arr[0][0]
    }
    if (arr[currentRow][currentColumn] == 0) {
        return 0
    }
    val left = mazeWithObstacles(arr, currentRow, currentColumn - 1)
    val right = mazeWithObstacles(arr, currentRow - 1, currentColumn)
    return left + right
}