package algorithms.stack

import datastructures.stack.Stack

// https://www.geeksforgeeks.org/sort-a-stack-using-recursion/

private val sortedStack: Stack<Int> = Stack()

fun main() {
    val stack = Stack<Int>()
    stack.pushAll(4, 8, 5, 7)
    stack.print()
    sortStack(stack)
    sortedStack.print()
}

private fun sortStack(stack: Stack<Int>) {
    if (stack.isEmpty()) {
        return
    }
    val top = stack.pop()
    sortStack(stack)
    insertSortedElement(top)
}

private fun insertSortedElement(num: Int) {
    when {
        sortedStack.isEmpty() -> {
            sortedStack.push(num)
        }
        sortedStack.peek() > num -> {
            sortedStack.push(num)
        }
        else -> {
            val top = sortedStack.pop()
            insertSortedElement(num)
            sortedStack.push(top)
        }
    }
}