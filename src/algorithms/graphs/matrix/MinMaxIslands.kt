package algorithms.graphs.matrix

import utils.printPair

fun main() {
    val matrix = arrayOf(
        arrayOf("W", "W", "W", "W", "L"),
        arrayOf("W", "W", "W", "W", "L"),
        arrayOf("W", "W", "W", "L", "W"),
        arrayOf("W", "L", "L", "L", "W"),
        arrayOf("W", "L", "W", "W", "W")
    )
    findIslandCount(matrix).printPair()
}

private fun findIslandCount(isLandMatrix: Array<Array<String>>): Pair<Int, Int> {
    var minIslandCount = Int.MAX_VALUE
    var maxIslandCount = Int.MIN_VALUE
    val visitedSet: MutableSet<String> = mutableSetOf()
    for (row in isLandMatrix.indices) {
        for (column in isLandMatrix[row].indices) {
            if (!visitedSet.contains("$row-$column") && isLandMatrix[row][column] == "L") {
                val isLandIndexes: MutableList<String> = mutableListOf()
                doBFSTraversal(isLandMatrix, visitedSet, row, column, isLandIndexes)
                minIslandCount = minIslandCount.coerceAtMost(isLandIndexes.size)
                maxIslandCount = maxIslandCount.coerceAtLeast(isLandIndexes.size)
            }
        }
    }
    return Pair(minIslandCount, maxIslandCount)
}

private fun doBFSTraversal(isLandMatrix: Array<Array<String>>, visitedSet: MutableSet<String>, row: Int, column: Int, isLandIndexes: MutableList<String>) {
    val rowInBounds = row in isLandMatrix.indices
    val columnInBounds = column in isLandMatrix[0].indices
    if (!rowInBounds || !columnInBounds) {
        return
    }
    if (visitedSet.contains("$row-$column") || isLandMatrix[row][column] == "W") {
        return
    }
    visitedSet.add("$row-$column")
    isLandIndexes.add("$row-$column")
    doBFSTraversal(isLandMatrix, visitedSet, row, column - 1, isLandIndexes)
    doBFSTraversal(isLandMatrix, visitedSet, row - 1, column, isLandIndexes)
    doBFSTraversal(isLandMatrix, visitedSet, row, column + 1, isLandIndexes)
    doBFSTraversal(isLandMatrix, visitedSet, row + 1, column, isLandIndexes)
}