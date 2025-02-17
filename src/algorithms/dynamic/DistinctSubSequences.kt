package algorithms.dynamic

import utils.printList

// https://www.naukri.com/code360/problems/subsequence-counting_3755256



fun main() {
    findNoOfDistinctSubSequences("babgbag", "bag").printList()
    findNoOfDistinctSubSequences("brootgroot", "brt").printList()
    findNoOfDistinctSubSequences("dingdingdingding", "ing").printList()
    findNoOfDistinctSubSequences("aaaaa", "a").printList()
    findNoOfDistinctSubSequences("abc", "d").printList()
    findNoOfDistinctSubSequences("abc", "").printList()
}

private fun findNoOfDistinctSubSequences(string1: String, string2: String): List<Int> {
    if (string1.isEmpty() || string2.isEmpty()) {
        return listOf(1, 1)
    }
    return listOf(
        findNoOfDistinctSubSequencesDP(
            string1,
            string2,
            string1.length - 1,
            string2.length - 1,
            Array(string1.length) { Array(string2.length) { -1 } }),
        findNoOfDistinctSubSequencesTabulation(string1, string2)
    )
}

/**
 * N = S1 Length
 * M = S2 Length
 * TC -> O(N * M)
 * SC -> O(N * M) + O(N + M) -> Auxiliary Stack Space
 */

private fun findNoOfDistinctSubSequencesDP(
    string1: String,
    string2: String,
    index1: Int,
    index2: Int,
    dpMatrix: Array<Array<Int>>
): Int {
    if (index2 < 0) return 1
    if (index1 < 0) return 0
    if (dpMatrix[index1][index2] != -1) {
        return dpMatrix[index1][index2]
    }
    if (string1[index1] == string2[index2]) {
        dpMatrix[index1][index2] = findNoOfDistinctSubSequencesDP(
            string1,
            string2,
            index1 - 1,
            index2 - 1,
            dpMatrix
        ) + findNoOfDistinctSubSequencesDP(string1, string2, index1 - 1, index2, dpMatrix)
    } else {
        dpMatrix[index1][index2] = findNoOfDistinctSubSequencesDP(string1, string2, index1 - 1, index2, dpMatrix)
    }
    return dpMatrix[index1][index2]
}

/**
 * N = S1 Length
 * M = S2 Length
 * TC -> O(N * M)
 * SC -> O(N * M)
 */

private fun findNoOfDistinctSubSequencesTabulation(
    string1: String,
    string2: String
): Int {
    val matrix: Array<Int> = Array(string2.length + 1) { column ->
        if (column == 0) {
            1
        } else {
            0
        }
    }
    for (row in 1 until string1.length + 1) {
        for (column in string2.length downTo 1) {
            if (string1[row - 1] == string2[column - 1]) {
                matrix[column] = matrix[column - 1] + matrix[column]
            }
        }
    }
    return matrix[matrix.size - 1]
}