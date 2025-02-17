package algorithms.dynamic.partition

import datastructures.stack.Stack
import utils.printValue

// https://leetcode.com/problems/maximal-rectangle/description/

fun main() {
    val inputs = arrayOf(
        arrayOf(
            arrayOf(1, 0, 1, 0, 0),
            arrayOf(1, 0, 1, 1, 1),
            arrayOf(1, 1, 1, 1, 1),
            arrayOf(1, 0, 0, 1, 0)
        ),
        arrayOf(
            arrayOf(0, 1),
            arrayOf(1, 0)
        ),
        arrayOf(
            arrayOf(0),
            arrayOf(1),
        ),
        arrayOf(
            arrayOf(0, 0, 1, 0),
            arrayOf(1, 1, 1, 1),
            arrayOf(1, 1, 1, 1),
            arrayOf(1, 1, 1, 0),
            arrayOf(1, 1, 0, 0),
            arrayOf(1, 1, 1, 1),
            arrayOf(1, 1, 1, 0),
        ),
        arrayOf(
            arrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            arrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            arrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            arrayOf(1, 1, 1, 1, 1, 0, 0, 0),
            arrayOf(0, 1, 1, 1, 1, 0, 0, 0),
        )
    )
    inputs.forEach {
        maximalRectangle(it).printValue()
    }
}

private fun maximalRectangle(matrix: Array<Array<Int>>): Int {
    val row: Array<Int> = matrix[0]
    var maxArea = 0
    for (i in 1 until matrix.size) {
        for (j in matrix[0].indices) {
            if (matrix[i][j] == 1) {
                row[j] = row[j] + 1
            } else {
                row[j] = 0
            }
        }
        maxArea = maxOf(maxArea, maxHistogramArea(row))
    }
    return maxArea
}


private fun maxHistogramArea(array: Array<Int>): Int {
    if (array.isEmpty()) {
        return 0
    }
    if (array.size == 1) {
        return array[0]
    }
    var maxArea = 0
    val stack: Stack<Int> = Stack()
    stack.push(-1)
    for (i in 0..array.size) {
        while (!stack.isEmpty() && getValue(array, stack.peek()) > getValue(array, i)) {
            val poppedIndex = stack.pop()
            val peekIndex = stack.peek()
            val area = ((i - poppedIndex) + (poppedIndex - peekIndex - 1)) * getValue(array, poppedIndex)
            maxArea = maxArea.coerceAtLeast(area)
        }
        stack.push(i)
    }
    return maxArea
}

private fun getValue(array: Array<Int>, index: Int): Int {
    if (index !in array.indices) {
        return 0
    }
    return array[index]
}