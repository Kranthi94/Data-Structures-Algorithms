package algorithms.stack

import datastructures.stack.Stack
import utils.printValue

// https://leetcode.com/problems/largest-rectangle-in-histogram/description/

fun main() {
    val inputs = arrayOf(
        arrayOf(6, 2, 5, 4, 5, 1, 6),
        arrayOf(2, 1, 5, 6, 2, 3),
        arrayOf(5, 5, 5),
        arrayOf(0, 0, 0),
        arrayOf(0, 5, 6, 6, 4, 3),
        arrayOf(5)
    )
    inputs.forEach {
        maxHistogramArea(it).printValue()
    }
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