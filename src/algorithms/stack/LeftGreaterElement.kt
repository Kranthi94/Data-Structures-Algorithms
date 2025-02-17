package algorithms.stack

import datastructures.stack.Stack
import utils.printArray

fun main() {
    findLeftGreaterElement(arrayOf(5, 3, 4, 2, 12)).printArray()
    findLeftGreaterElement(arrayOf(4, 5, 3, 2, 12)).printArray()
    findLeftGreaterElement(arrayOf(4, 3, 2, 1)).printArray()
}

private fun findLeftGreaterElement(array: Array<Int>): Array<Int> {
    val result = Array(array.size) { -1 }
    val stack: Stack<Int> = Stack(array.size)
    for (i in array.indices.reversed()) {
        val currentValue = array[i]
        if (!stack.isEmpty()) {
            var index = i
            while (!stack.isEmpty() && currentValue > stack.peek()) {
                stack.pop()
                index += 1
                result[index] = currentValue
            }
        }
        stack.push(currentValue)
    }
    return result
}