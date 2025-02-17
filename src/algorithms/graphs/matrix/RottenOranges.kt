package algorithms.graphs.matrix

import datastructures.queue.Queue
import utils.printValue

// https://leetcode.com/problems/rotting-oranges/description/

fun main() {
    val matrix = arrayOf(
        arrayOf(2, 1, 1),
        arrayOf(1, 1, 1),
        arrayOf(0, 1, 2)
    )
    findRottingOrangesTime(matrix).printValue()
}

private fun findRottingOrangesTime(matrix: Array<Array<Int>>): Int {
    var timeTaken = -1
    val rottenTimeMatrix: Array<Array<Int>> = Array(matrix.size) {
        Array(matrix[0].size) {
            0
        }
    }
    for (row in matrix.indices) {
        for (column in matrix[row].indices) {
            if (matrix[row][column] == 2) {
                val visitedSet: MutableSet<String> = mutableSetOf()
                doBFS(matrix, visitedSet, rottenTimeMatrix, row, column)
            }
        }
    }
    abc@ for (row in rottenTimeMatrix.indices) {
        for (column in rottenTimeMatrix[0].indices) {
            if (rottenTimeMatrix[row][column] == 0 && matrix[row][column] == 1) {
                timeTaken = -1
                break@abc
            }
            timeTaken = timeTaken.coerceAtLeast(rottenTimeMatrix[row][column])
        }
    }
    return timeTaken
}

private fun doBFS(matrix: Array<Array<Int>>, visitedSet: MutableSet<String>, rottenTimeMatrix: Array<Array<Int>>, row: Int, column: Int) {
    val rowInBounds = row in matrix.indices
    val columnInBounds = column in matrix[0].indices
    if (!rowInBounds || !columnInBounds) {
        return
    }
    visitedSet.add("$row-$column")
    val queue: Queue<Pair<Int, Int>> = Queue()
    findAllAdjacentVertices(matrix, visitedSet, row, column).forEach {
        queue.enQueue(it)
        if (rottenTimeMatrix[it.first][it.second] == 0) {
            rottenTimeMatrix[it.first][it.second] = rottenTimeMatrix[row][column] + 1
        } else {
            rottenTimeMatrix[it.first][it.second] = rottenTimeMatrix[it.first][it.second].coerceAtMost(rottenTimeMatrix[row][column] + 1)
        }
    }
    while (!queue.isEmpty()) {
        val poppedPair = queue.deQueue()
        val adjacentVertices = findAllAdjacentVertices(matrix, visitedSet, poppedPair.first, poppedPair.second)
        adjacentVertices.forEach {
            queue.enQueue(it)
            if (rottenTimeMatrix[it.first][it.second] == 0) {
                rottenTimeMatrix[it.first][it.second] = rottenTimeMatrix[poppedPair.first][poppedPair.second] + 1
            } else {
                rottenTimeMatrix[it.first][it.second] = rottenTimeMatrix[it.first][it.second].coerceAtMost(rottenTimeMatrix[poppedPair.first][poppedPair.second] + 1)
            }
        }
    }
}

private fun findAllAdjacentVertices(matrix: Array<Array<Int>>, visitedSet: MutableSet<String>, row: Int, column: Int): List<Pair<Int, Int>> {
    val array = arrayOf(Pair(0, -1), Pair(-1, 0), Pair(0, 1), Pair(1, 0))
    val adjacentVertices: MutableList<Pair<Int, Int>> = mutableListOf()
    array.forEach { pair ->
        val newRow = row + pair.first
        val newColumn = column + pair.second
        if (newRow in matrix.indices && newColumn in matrix[0].indices && matrix[newRow][newColumn] == 1 && !visitedSet.contains("$newRow-$newColumn")) {
            visitedSet.add("$newRow-$newColumn")
            adjacentVertices.add(Pair(newRow, newColumn))
        }
    }
    return adjacentVertices
}