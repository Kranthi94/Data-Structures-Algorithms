package algorithms.stack

import java.util.*

// https://leetcode.com/problems/basic-calculator/description/

fun main() {
//    println(calculate1("1-11"))
//    println(calculate1("1 + 1"))
//    println(calculate1("(1+(4+5+2)-3)+(6+8)"))
//
//    println(calculate2("1-11"))
//    println(calculate2("1 + 1"))
    println(calculate2("(1+(4+5+2)-3)+(6+8)"))
}

private fun calculate2(exp: String): Int {
    val stack: Stack<Int> = Stack()
    var result = 0
    var number = 0
    var sign = 1
    for (i in exp.indices) {
        val char = exp[i]
        when {
            char.isDigit() -> {
                number = number * 10 + (char - '0')
            }

            char == '+' -> {
                result += sign * number
                sign = 1
                number = 0
            }

            char == '-' -> {
                result += sign * number
                sign = -1
                number = 0
            }

            char == '(' -> {
                stack.push(result)
                stack.push(sign)
                result = 0
                number = 0
                sign = 1
            }

            char == ')' -> {
                result += sign * number
                val previousSign = stack.pop()
                val previousResult = stack.pop()
                result = previousResult + previousSign * result
                number = 0
            }
        }
    }
    result += sign * number
    return result
}