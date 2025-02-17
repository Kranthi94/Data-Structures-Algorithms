package algorithms.stack

import datastructures.stack.Stack
import utils.printArray

// https://www.geeksforgeeks.org/the-stock-span-problem/

fun main() {
    findStockSpan("100 80 60 70 85".split(" "))
    findStockSpan("100 80 60 70 60 75 85".split(" "))
    findStockSpan("10 4 5 90 120 80".split(" "))
}

private fun findStockSpan(stringArray: List<String>) {
    val outputArray = IntArray(stringArray.size) { 1 }
    val stack = Stack<Int>()
    for (i in stringArray.indices) {
        if (!stack.isEmpty()) {
            val currentValue = stringArray[i]
            var top = stack.pop()
            while (stringArray[top].toInt() < currentValue.toInt()) {
                top = stack.pop()
            }
            top.let {
                if (stringArray[top].toInt() > currentValue.toInt()) {
                    stack.push(top)
                }
            }
            outputArray[i] = i - top
        }
        stack.push(i)
    }
    println(outputArray.printArray())
}