package algorithms.stack

import datastructures.stack.Stack
import utils.printList

// https://www.geeksforgeeks.org/reverse-a-stack-using-recursion/

fun main() {
    val stack = Stack<Int>()
    stack.pushAll(1, 2, 3, 4)
    stack.print()
    findReverse(stack).printList()
}

private fun <T> findReverse(stack: Stack<T>): MutableList<T> {
    if (stack.isEmpty()) {
        return mutableListOf()
    }
    val top = stack.pop()
    val result = findReverse(stack)
    result.add(top)
    return result
}