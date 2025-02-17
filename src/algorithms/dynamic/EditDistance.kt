package algorithms.dynamic

import utils.printList

// https://www.naukri.com/code360/problems/edit-distance_630420

fun main() {
    editDistance("horse", "ros").printList()
    editDistance("abc", "dc").printList()
    editDistance("whgtdwhgtdg", "aswcfg").printList()
}

private fun editDistance(string1: String, string2: String): List<Int> {
    if (string1.isEmpty()) {
        return listOf(string2.length, string2.length)
    }
    if (string2.isEmpty()) {
        return listOf(string1.length, string1.length)
    }
    return listOf(
        editDistanceDP(string1, string2, string1.length - 1, string2.length - 1, Array(string1.length) { Array(string2.length) { -1 } }),
        editDistanceDPTabulation(string1, string2)
    )
}

private fun editDistanceDP(string1: String, string2: String, index1: Int, index2: Int, dpMatrix: Array<Array<Int>>): Int {
    if (index1 < 0) {
        return index2 + 1
    }
    if (index2 < 0) {
        return index1 + 1
    }
    if (dpMatrix[index1][index2] != -1) {
        return dpMatrix[index1][index2]
    }
    if (string1[index1] == string2[index2]) {
        dpMatrix[index1][index2] = editDistanceDP(string1, string2, index1 - 1, index2 - 1, dpMatrix)
    } else {
        val delete = 1 + editDistanceDP(string1, string2, index1 - 1, index2, dpMatrix)
        val replace = 1 + editDistanceDP(string1, string2, index1 - 1, index2 - 1, dpMatrix)
        val add = 1 + editDistanceDP(string1, string2, index1, index2 - 1, dpMatrix)
        dpMatrix[index1][index2] = minOf(add, minOf(delete, replace))
    }
    return dpMatrix[index1][index2]
}

private fun editDistanceDPTabulation(
    string1: String,
    string2: String
): Int {
    var previousRow: Array<Int> = Array(string2.length + 1) { column ->
        column
    }
    val currentRow: Array<Int> = Array(string2.length + 1) { column ->
        column
    }
    for (row in 1 until string1.length + 1) {
        previousRow[0] = row - 1
        currentRow[0] = row
        for (column in 1 until string2.length + 1) {
            if (string1[row - 1] == string2[column - 1]) {
                currentRow[column] = previousRow[column - 1]
            } else {
                currentRow[column] = minOf(1 + currentRow[column - 1], minOf(1 + previousRow[column], 1 + previousRow[column - 1]))
            }
        }
        previousRow = currentRow.clone()
    }
    return previousRow[previousRow.size - 1]
}