package algorithms.stack

import datastructures.stack.Stack
import utils.printValue

// https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/

fun main() {
    checkBalancedParentheses("[()]{}{[()()]()}".toCharArray()).printValue()
    checkBalancedParentheses("}[(])".toCharArray()).printValue()
}

private fun checkBalancedParentheses(charArray: CharArray): Boolean {
    val stack = Stack<Char>()
    for (char in charArray) {
        when(char) {
            ')' -> {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false
                }
            }
            '}' -> {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false
                }
            }
            ']' -> {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false
                }
            }
            else -> stack.push(char)
        }
    }
    return stack.isEmpty()
}