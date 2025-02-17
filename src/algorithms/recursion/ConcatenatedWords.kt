package algorithms.recursion

import utils.printList

// https://leetcode.com/problems/concatenated-words/
// https://www.hackerrank.com/challenges/password-cracker/problem?isFullScreen=true

fun main() {
    findConcatenatedWords(arrayOf("cat","cats","catsdog","dog")).printList()
    findConcatenatedWords(arrayOf("cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat")).printList()
    findConcatenatedWords(arrayOf("a","b","ab","abc")).printList()
}

private fun findConcatenatedWords(array: Array<String>): List<String> {
    if (array.size < 2) {
        return mutableListOf()
    }
    val result = mutableListOf<String>()
    array.indices.forEach { index ->
        val word = array[index]
        if (checkIsConcatenated(word, index, 0, array)) {
            result.add(word)
        }
    }
    return result
}

private fun checkIsConcatenated(word: String, arrayIndex: Int, splitIndex: Int, array: Array<String>): Boolean {
    if (word.isEmpty() && splitIndex == 0) {
        return true
    }
    if (splitIndex == word.length) {
        return false
    }
    val prefix = word.substring(0, splitIndex + 1)
    val prefixIndex = array.indexOf(prefix)
    val prefixContains = prefixIndex != -1 && prefixIndex != arrayIndex
    if (prefixContains) {
        val suffix = word.substring(splitIndex + 1)
        if (checkIsConcatenated(suffix, arrayIndex, 0, array)) {
            return true
        }
    }
    return checkIsConcatenated(word, arrayIndex, splitIndex + 1, array)
}