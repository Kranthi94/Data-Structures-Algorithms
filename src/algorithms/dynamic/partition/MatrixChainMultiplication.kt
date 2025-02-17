package algorithms.dynamic.partition

import utils.printList

// https://www.naukri.com/code360/problems/matrix-chain-multiplication_975344

fun main() {
    val inputs = arrayOf(arrayOf(10, 20, 30, 40), arrayOf(4, 5, 3, 2), arrayOf(10, 15, 20, 25), arrayOf(1, 4, 3, 2))
    inputs.forEach {
        findMinimumSteps(it).printList()
    }
}

private fun findMinimumSteps(array: Array<Int>): List<Int> {
    if (array.size < 2) {
        return listOf(0, 0)
    }
    return listOf(
        findMinimumStepsRecursive(array, 1, array.size - 1, Array(array.size) { Array(array.size) { -1 } }),
        findMinimumStepsTabulation(array)
    )
}

private fun findMinimumStepsRecursive(
    array: Array<Int>,
    startIndex: Int,
    endIndex: Int,
    dpMatrix: Array<Array<Int>>
): Int {
    if (startIndex == endIndex) {
        return 0
    }
    if (dpMatrix[startIndex][endIndex] != -1) {
        return dpMatrix[startIndex][endIndex]
    }
    var minSteps = Int.MAX_VALUE
    for (partitionIndex in startIndex until endIndex) {
        val currentSteps = array[startIndex - 1] * array[partitionIndex] * array[endIndex]
        val leftSteps = findMinimumStepsRecursive(array, startIndex, partitionIndex, dpMatrix)
        val rightSteps = findMinimumStepsRecursive(array, partitionIndex + 1, endIndex, dpMatrix)
        val totalSteps = currentSteps + leftSteps + rightSteps
        minSteps = minOf(minSteps, totalSteps)
    }
    dpMatrix[startIndex][endIndex] = minSteps
    return minSteps
}

private fun findMinimumStepsTabulation(array: Array<Int>): Int {
    val matrix: Array<Array<Int>> = Array(array.size) {
        Array(array.size) {
            0
        }
    }
    for (row in array.size - 1 downTo 1) {
        for (column in row + 1 until array.size) {
            var minSteps = Int.MAX_VALUE
            for (partitionIndex in row until column) {
                val currentSteps = array[row - 1] * array[partitionIndex] * array[column]
                val leftSteps = matrix[row][partitionIndex]
                val rightSteps = matrix[partitionIndex + 1][column]
                val totalSteps = currentSteps + leftSteps + rightSteps
                minSteps = minOf(minSteps, totalSteps)
            }
            matrix[row][column] = if (minSteps != Int.MAX_VALUE) minSteps else 0
        }
    }
    return matrix[1][matrix[0].size - 1]
}