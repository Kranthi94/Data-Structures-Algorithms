package algorithms.dynamic

import utils.printPair

// https://www.naukri.com/code360/problems/shortest-supersequence_4244493

fun main() {
    findShortestCommonSuperSequence("brute", "groot").printPair()
    findShortestCommonSuperSequence("bleed", "blue").printPair()
    findShortestCommonSuperSequence("coding", "ninjas").printPair()
    findShortestCommonSuperSequence("blinding", "lights").printPair()
}

private fun findShortestCommonSuperSequence(string1: String, string2: String): Pair<String, Int> {
    val superSequence = findShortestCommonSuperSequenceTabulation(string1, string2)
    return Pair(superSequence, superSequence.length)
}

private fun findShortestCommonSuperSequenceTabulation(string1: String, string2: String): String {
    val matrix: Array<Array<Int>> = Array(string1.length + 1) { row ->
        Array(string2.length + 1) { column ->
            if (row == 0 || column == 0) {
                0
            } else {
                -1
            }
        }
    }
    for (row in 1 until string1.length + 1) {
        for (column in 1 until string2.length + 1) {
            if (string1[row - 1] == string2[column - 1]) {
                matrix[row][column] = 1 + matrix[row - 1][column - 1]
            } else {
                matrix[row][column] = maxOf(matrix[row - 1][column], matrix[row][column - 1])
            }
        }
    }
    var finalString = ""
    var currentRow = string1.length
    var currentColumn = string2.length
    while (currentRow - 1 in string1.indices && currentColumn - 1 in string2.indices) {
        if (string1[currentRow - 1] == string2[currentColumn - 1]) {
            finalString += string1[currentRow - 1]
            currentColumn -= 1
            currentRow -= 1
        } else {
            val top = matrix[currentRow - 1][currentColumn]
            val left = matrix[currentRow][currentColumn - 1]
            if (top >= left) {
                finalString += string1[currentRow - 1]
                currentRow -= 1
            } else {
                finalString += string2[currentColumn - 1]
                currentColumn -= 1
            }
        }
    }
    if (currentColumn > 0) {
        finalString += string2.substring(0, currentColumn)
    }
    if (currentRow > 0) {
        finalString += string1.substring(0, currentRow)
    }
    return finalString.reversed()
}