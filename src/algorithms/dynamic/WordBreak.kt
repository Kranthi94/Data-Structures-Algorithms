package algorithms.dynamic

import utils.printValue

// https://leetcode.com/problems/word-break/description/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    wordBreak("leetcode", listOf("leet", "code")).printValue() 
    wordBreak("applepenapple", listOf("apple", "pen")).printValue()
    wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat")).printValue()
}

private fun wordBreak(string: String, wordsList: List<String>): Boolean {
    return wordBreak(string, wordsList, mutableMapOf())
}

private fun wordBreak(string: String, wordsList: List<String>, containedMap: MutableMap<String, Boolean>): Boolean {
    if (string.isEmpty()) {
        return false
    }
    if (containedMap.contains(string)) {
        return containedMap[string]!!
    }
    if (wordsList.contains(string)) {
        return true
    }
    for (index in 1 until string.length) {
        val leftSubString = string.substring(0, index)
        val leftSubStringContains = wordBreak(leftSubString, wordsList, containedMap)
        containedMap[leftSubString] = leftSubStringContains
        if (leftSubStringContains) {
            val rightSubString = string.substring(index, string.length)
            val rightSubStringContains = wordBreak(rightSubString, wordsList, containedMap)
            containedMap[rightSubString] = rightSubStringContains
            if (rightSubStringContains) {
                return true
            }
        }
    }
    return false
}