package algorithms.dynamic.partition

import utils.printList

// https://www.naukri.com/code360/problems/problem-name-boolean-evaluation_1214650

fun main() {
    val inputs = arrayOf("T|T&F", "T^T^F", "F|T^F")
    inputs.forEach {
        evaluateBooleanExpression(it, true).printList()
    }
}

private fun evaluateBooleanExpression(string: String, isTrue: Boolean): List<Int> {
    if (string.isEmpty()) {
        return listOf(0)
    }
    return listOf(
        evaluateBooleanExpressionDP(
            string,
            0,
            string.length - 1,
            isTrue,
            Array(string.length) { Array(string.length) { Array(2) { -1 } } }
        ),
        evaluateBooleanExpressionTabulation(string, isTrue)
    )
}

private fun evaluateBooleanExpressionDP(
    string: String,
    startIndex: Int,
    endIndex: Int,
    isTrue: Boolean,
    dpMatrix: Array<Array<Array<Int>>>
): Int {
    if (startIndex > endIndex) {
        return 0
    }
    if (startIndex == endIndex) {
        return if ((isTrue && string[startIndex] == 'T') || (!isTrue && string[startIndex] == 'F')) {
            1
        } else {
            0
        }
    }
    if (dpMatrix[startIndex][endIndex][if (isTrue) 1 else 0] != -1) {
        return dpMatrix[startIndex][endIndex][if (isTrue) 1 else 0]
    }
    var ways = 0
    for (partitionIndex in startIndex + 1 until endIndex step 2) {
        val currentOperand = string[partitionIndex]
        ways += when (currentOperand) {
            '&' -> {
                if (isTrue) {
                    val leftTrue = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, true, dpMatrix)
                    val rightTrue = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, true, dpMatrix)
                    leftTrue * rightTrue
                } else {
                    val leftTrue = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, true, dpMatrix)
                    val rightTrue = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, true, dpMatrix)
                    val leftFalse = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, false, dpMatrix)
                    val rightFalse = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, false, dpMatrix)
                    leftFalse * rightTrue + leftTrue * rightFalse + leftFalse * rightFalse
                }
            }

            '|' -> {
                if (isTrue) {
                    val leftTrue = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, true, dpMatrix)
                    val rightTrue = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, true, dpMatrix)
                    val leftFalse = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, false, dpMatrix)
                    val rightFalse = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, false, dpMatrix)
                    leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue
                } else {
                    val leftFalse = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, false, dpMatrix)
                    val rightFalse = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, false, dpMatrix)
                    leftFalse * rightFalse
                }
            }

            else -> {
                if (isTrue) {
                    val leftTrue = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, true, dpMatrix)
                    val rightTrue = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, true, dpMatrix)
                    val leftFalse = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, false, dpMatrix)
                    val rightFalse = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, false, dpMatrix)
                    leftTrue * rightFalse + leftFalse * rightTrue
                } else {
                    val leftTrue = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, true, dpMatrix)
                    val rightTrue = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, true, dpMatrix)
                    val leftFalse = evaluateBooleanExpressionDP(string, startIndex, partitionIndex - 1, false, dpMatrix)
                    val rightFalse = evaluateBooleanExpressionDP(string, partitionIndex + 1, endIndex, false, dpMatrix)
                    leftTrue * rightTrue + leftFalse * rightFalse
                }
            }
        }
    }
    dpMatrix[startIndex][endIndex][if (isTrue) 1 else 0] = ways
    return ways
}

private fun evaluateBooleanExpressionTabulation(
    string: String,
    isTrue: Boolean
): Int {
    val matrix: Array<Array<Array<Int>>> = Array(string.length) { row ->
        Array(string.length) { column ->
            Array(2) { diagonal ->
                0
            }
        }
    }
    for (row in string.indices.reversed()) {
        for (column in row + 1 until string.length) {
            if (row > column) {
                continue
            }
            if (row == column) {
                if (isTrue && string[row] == 'T') {
                    matrix[row][column][1] = 1
                } else if (!isTrue && string[row] == 'F') {
                    matrix[row][column][0] = 1
                }
                continue
            }
            repeat(2) {
                var ways = 0
                for (partitionIndex in row + 1 until column step 2) {
                    val leftTrue = matrix[row][partitionIndex - 1][1]
                    val rightTrue = matrix[partitionIndex + 1][column][1]
                    val leftFalse = matrix[row][partitionIndex - 1][0]
                    val rightFalse = matrix[partitionIndex + 1][column][0]
                    ways += when (string[partitionIndex]) {
                        '&' -> {
                            if (it == 1) {
                                leftTrue * rightTrue
                            } else {
                                leftFalse * rightTrue + leftTrue * rightFalse + leftFalse * rightFalse
                            }
                        }

                        '|' -> {
                            if (it == 1) {
                                leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue
                            } else {
                                leftFalse * rightFalse
                            }
                        }

                        else -> {
                            if (it == 1) {
                                leftTrue * rightFalse + leftFalse * rightTrue
                            } else {
                                leftTrue * rightTrue + leftFalse * rightFalse
                            }
                        }
                    }
                }
                matrix[row][column][it] = ways
            }
        }
    }
    return matrix[0][string.length - 1][if (isTrue) 1 else 0]
}

