package algorithms.stack

import datastructures.stack.Stack
import utils.printArray

fun main() {
    findRightSmallerElement(arrayOf(7, 8, 5, 3, 10)).printArray()
    findRightSmallerElement(arrayOf(7, 8, 9, 10, 11)).printArray()
}

private fun findRightSmallerElement(array: Array<Int>): Array<Int> {
    val result: Array<Int> = Array(array.size) { -1 }
    val stack = Stack<Int>(array.size)
    for (i in array.indices) {
        val currentValue = array[i]
        if (!stack.isEmpty()) {
            var index = i
            while (!stack.isEmpty() && stack.peek() > currentValue) {
                stack.pop()
                index -= 1
                result[index] = currentValue
            }
        }
        stack.push(currentValue)
    }
    return result
}

