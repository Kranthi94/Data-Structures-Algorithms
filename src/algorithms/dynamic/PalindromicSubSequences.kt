package algorithms.dynamic

import utils.printList

// https://www.naukri.com/code360/problems/longest-common-subsequence_624879
// https://leetcode.com/problems/longest-palindromic-subsequence/description/?envType=study-plan-v2&envId=dynamic-programming

fun main() {
    findPalindromicSubSequences("qq").printList()
    findPalindromicSubSequences("bab").printList()
    findPalindromicSubSequences("cbbd").printList()
    findPalindromicSubSequences("bbbab").printList()
}

private fun findPalindromicSubSequences(string: String): List<List<String>> {
    if (string.isEmpty()) {
        return listOf(listOf(""))
    }
    if (string.length == 1) {
        return listOf(listOf("", string))
    }
    val dpMatrix: Array<Array<List<String>>> = Array(string.length) {
        Array(string.length) {
            listOf()
        }
    }
    return listOf(
        findPalindromicSubSequencesDP(string, string.reversed(), string.length - 1, string.length - 1, dpMatrix),
        findPalindromicSubStringTabulation(string, string.reversed())
    )
}

private fun findPalindromicSubSequencesDP(string1: String, string2: String, index1: Int, index2: Int, dpMatrix: Array<Array<List<String>>>): List<String> {
    if (index1 < 0 || index2 < 0) {
        return mutableListOf("")
    }
    if (dpMatrix[index1][index2].isNotEmpty()) {
        return dpMatrix[index1][index2]
    }
    if (string1[index1] == string2[index2]) {
        val result: List<String> = findPalindromicSubSequencesDP(string1, string2, index1 - 1, index2 - 1, dpMatrix)
        val newResult: MutableList<String> = result.toMutableList()
        result.forEach {
            if (!newResult.contains(it.plus(string1[index1]))) {
                newResult.add(it.plus(string1[index1]))
            }
        }
        dpMatrix[index1][index2] = newResult.toList()
        return newResult
    }
    val result1 = findPalindromicSubSequencesDP(string1, string2, index1 - 1, index2, dpMatrix).toMutableList()
    val result2 = findPalindromicSubSequencesDP(string1, string2, index1, index2 - 1, dpMatrix)
    result2.forEach {
        if (!result1.contains(it)) {
            result1.add(it)
        }
    }
    dpMatrix[index1][index2] = result1.toList()
    return result1
}

private fun findPalindromicSubStringTabulation(string1: String, string2: String): List<String> {
    var previousRow: Array<List<String>> = Array(string2.length + 1) {
        listOf("")
    }
    val currentRow: Array<List<String>> = Array(string2.length + 1) {
        listOf("")
    }
    for (row in 1 until string1.length + 1) {
        for (column in 1 until  string2.length + 1) {
            if (string1[row - 1] == string2[column - 1]) {
                val newResult: MutableList<String> = previousRow[column - 1].toMutableList()
                previousRow[column - 1].forEach {
                    if (!newResult.contains(it.plus(string1[row - 1]))) {
                        newResult.add(it.plus(string1[row - 1]))
                    }
                }
                currentRow[column] = newResult.toList()
            } else {
                val result1 = previousRow[column].toMutableList()
                val result2 = currentRow[column - 1]
                result2.forEach {
                    if (!result1.contains(it)) {
                        result1.add(it)
                    }
                }
                currentRow[column] = result1.toList()
            }
        }
        previousRow = currentRow.clone()
    }
    return previousRow[previousRow.size - 1]
}