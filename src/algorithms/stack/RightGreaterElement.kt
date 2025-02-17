package algorithms.stack

import datastructures.stack.Stack
import utils.printArray

// https://www.geeksforgeeks.org/next-greater-element/

fun main() {
    findRightGreaterElement(arrayOf(4, 5, 3, 2, 24)).printArray()
    findRightGreaterElement(arrayOf(13, 7, 6, 2, 12)).printArray()
    findRightGreaterElement(arrayOf(13, 17, 26, 29, 32)).printArray()
}

private fun findRightGreaterElement(array: Array<Int>): Array<Int> {
    val result: Array<Int> = Array(array.size) { -1 }
    val stack = Stack<Int>(array.size)
    for (i in array.indices) {
        val currentValue = array[i]
        if (!stack.isEmpty()) {
            var index = i
            while (!stack.isEmpty() && stack.peek() < currentValue) {
                stack.pop()
                index -= 1
                result[index] = currentValue
            }
        }
        stack.push(currentValue)
    }
    return result
}