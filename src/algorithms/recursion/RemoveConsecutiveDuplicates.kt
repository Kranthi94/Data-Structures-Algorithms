package algorithms.recursion

import utils.printValue

fun main() {
    removeConsecutiveDuplicates("aaaaabbbbbb").printValue()
    removeConsecutiveDuplicates("geeksforgeeks").printValue()
    removeConsecutiveDuplicates("aabccba").printValue()
}

private fun removeConsecutiveDuplicates(string: String, index: Int = 0, currentChar: Char = ' '): String {
    if(index == string.length) {
        return string
    }
    val char = string[index]
    return if (char != currentChar) {
        removeConsecutiveDuplicates(string, index + 1, char)
    } else {
        removeConsecutiveDuplicates(string.substring(0, index).plus(string.substring(index + 1)), index, char)
    }
}