package algorithms.graphs.matrix

import utils.printValue

fun main() {
    val matrix = arrayOf(
        arrayOf("L", "W", "W", "W", "W"),
        arrayOf("W", "W", "W", "W", "L"),
        arrayOf("W", "W", "W", "L", "W"),
        arrayOf("W", "L", "L", "L", "W"),
        arrayOf("W", "L", "L", "W", "L")
    )
    findIslandCount(matrix).printValue()
}

private fun findIslandCount(isLandMatrix: Array<Array<String>>): Int {
    var isLandsCount = 0
    val visitedSet: MutableSet<String> = mutableSetOf()
    for (row in isLandMatrix.indices) {
        for (column in isLandMatrix[row].indices) {
            if (!visitedSet.contains("$row-$column") && isLandMatrix[row][column] == "L") {
                doBFSTraversal(isLandMatrix, visitedSet, row, column)
                isLandsCount += 1
            }
        }
    }
    return isLandsCount
}

private fun doBFSTraversal(isLandMatrix: Array<Array<String>>, visitedSet: MutableSet<String>, row: Int, column: Int) {
    val rowInBounds = row in isLandMatrix.indices
    val columnInBounds = column in isLandMatrix[0].indices
    if (!rowInBounds || !columnInBounds) {
        return
    }
    if (visitedSet.contains("$row-$column") || isLandMatrix[row][column] == "W") {
        return
    }
    visitedSet.add("$row-$column")
    doBFSTraversal(isLandMatrix, visitedSet, row, column - 1)
    doBFSTraversal(isLandMatrix, visitedSet, row - 1, column)
    doBFSTraversal(isLandMatrix, visitedSet, row, column + 1)
    doBFSTraversal(isLandMatrix, visitedSet, row + 1, column)
}