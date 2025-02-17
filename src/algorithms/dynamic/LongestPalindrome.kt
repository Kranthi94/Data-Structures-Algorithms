package algorithms.dynamic

import utils.printList

fun main() {
    longestPalindrome("babad").printList()
    longestPalindrome("ac").printList()
}

private fun longestPalindrome(string: String): List<String> {
    if (string.length == 1) {
        return listOf(string)
    }
    val palindromeMap: MutableMap<String, String> = mutableMapOf()
    val result: MutableList<String> = MutableList(1) { "" }
    longestPalindrome(string, palindromeMap, result)
    return result
}

private fun longestPalindrome(string: String, palindromeMap: MutableMap<String, String>, result: MutableList<String>) {
    if (checkPalindrome(string)) {
        if (string.length > result[0].length) {
            result[0] = string
        }
//        if (!result.contains(string)) {
//            result.add(string)
//        }
        palindromeMap[string] = string
        return
    }
    if (palindromeMap.containsKey(string)) {
        return
    }
    for (index in 1 until string.length) {
        val leftSubString = string.substring(0, index)
        val rightSubString = string.substring(index, string.length)
        longestPalindrome(leftSubString, palindromeMap, result)
        longestPalindrome(rightSubString, palindromeMap, result)
        palindromeMap[string] = ""
        val leftPalindrome = palindromeMap.getOrDefault(leftSubString, "")
        val rightPalindrome = palindromeMap.getOrDefault(rightSubString, "")
        if (leftPalindrome.isNotEmpty() && palindromeMap[string]!!.length < leftPalindrome.length) {
            palindromeMap[string] = leftPalindrome
        }
        if (rightPalindrome.isNotEmpty() && palindromeMap[string]!!.length < rightPalindrome.length) {
            palindromeMap[string] = rightPalindrome
        }
    }
}

private fun checkPalindrome(string: String): Boolean {
    var startPointer = 0
    var endPointer = string.length - 1
    while (startPointer <= endPointer) {
        if (string[startPointer] != string[endPointer]) {
            return false
        }
        startPointer += 1
        endPointer -= 1
    }
    return true
}