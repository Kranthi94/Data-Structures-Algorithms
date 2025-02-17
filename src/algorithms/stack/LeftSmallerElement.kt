package algorithms.stack

import datastructures.stack.Stack
import utils.printArray

fun main() {
    findLeftSmallerElement(arrayOf(7, 8, 5, 3, 10)).printArray()
    findLeftSmallerElement(arrayOf(7, 8, 9, 10, 11)).printArray()
    findLeftSmallerElement(arrayOf(5, 3, 4, 2, 12)).printArray()
}

private fun findLeftSmallerElement(array: Array<Int>): Array<Int> {
    val result: Array<Int> = Array(array.size) { -1 }
    val stack: Stack<Int> = Stack(array.size)
    for(i in array.indices.reversed()) {
        val currentValue = array[i]
        if (!stack.isEmpty()) {
            var index = i
            while (!stack.isEmpty() && currentValue < stack.peek()) {
                stack.pop()
                index += 1
                result[index] = currentValue
            }
        }
        stack.push(currentValue)
    }
    return result
}