package algorithms.recursion

import utils.printValue

fun main() {
    checkBalancedParenthesis("[()]").printValue()
    checkBalancedParenthesis("[()]{").printValue()
    checkBalancedParenthesis("[()]{()()}").printValue()
    checkBalancedParenthesis("[()]{}{[()()]()}{").printValue()
}

var closingBrackets = charArrayOf(']', ')', '}')

private fun checkBalancedParenthesis(string: String, startIndex: Int = 0, endIndex: Int = string.length - 1): Boolean {
    if (string.isEmpty() || startIndex > endIndex) {
        return true
    }
    val char = string[startIndex]
    if (string.length == 1 || closingBrackets.contains(char)) {
        return false
    }
    val closingIndex = getClosingBracketIndex(string, char, startIndex, endIndex)
    if (closingIndex in (startIndex + 1)..< endIndex + 1) {
        return checkBalancedParenthesis(string, startIndex + 1, closingIndex - 1) && checkBalancedParenthesis(string, closingIndex + 1, endIndex)
    }
    return false
}

private fun getClosingBracketIndex(string: String, char: Char, startIndex: Int, endIndex: Int): Int {
    return when(char) {
        '[' -> string.substring(startIndex, endIndex + 1).indexOf(']') + startIndex
        '{' -> string.substring(startIndex, endIndex + 1).indexOf('}') + startIndex
        '(' -> string.substring(startIndex, endIndex + 1).indexOf(')') + startIndex
        else -> -1
    }
}