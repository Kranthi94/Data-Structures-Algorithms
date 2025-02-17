package algorithms.recursion

import utils.printList

fun main() {
    letterCombinations("23").printList()
    letterCombinations("123").printList()
}

private fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) {
        return arrayListOf()
    }
    if (digits.length == 1) {
        return getMapping(digits)
    }
    val currentDigit = digits[0]
    val result = letterCombinations(digits.substring(1))
    return mergeLists(getMapping(currentDigit.toString()), result)
}

private fun mergeLists(leftList: List<String>, rightList: List<String>): List<String> {
    val result = arrayListOf<String>()
    leftList.forEach { left ->
        rightList.forEach { right ->
            result.add(left + right)
        }
    }
    return result
}

private fun getMapping(digit: String): List<String> {
    return when(digit) {
        "2" -> arrayListOf("a", "b", "c")
        "3" -> arrayListOf("d", "e", "f")
        "4" -> arrayListOf("g", "h", "i")
        "5" -> arrayListOf("j", "k", "l")
        "6" -> arrayListOf("m", "n", "o")
        "7" -> arrayListOf("p", "q", "r", "s")
        "8" -> arrayListOf("t", "u", "v")
        "9" -> arrayListOf("w", "x", "y", "z")
        else -> arrayListOf()
    }
}