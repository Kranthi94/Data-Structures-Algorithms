package algorithms.dynamic

import utils.printList

fun main() {
    wildCardMatching("?ay", "aray").printList()
    wildCardMatching("ab?cd", "abecd").printList()
    wildCardMatching("ab*cd", "abdefcd").printList()
    wildCardMatching("ab*cd", "axdefcd").printList()
    wildCardMatching("ab*cd", "abcd").printList()
    wildCardMatching("ab?d", "abcd").printList()
    wildCardMatching("*a***b?d", "abcd").printList()
    wildCardMatching("****ab?d", "abcd").printList()
}

private fun wildCardMatching(string1: String, string2: String): List<Boolean> {
    if (string1.isEmpty() && string2.isEmpty()) {
        return listOf(true, true)
    }
    if (string1.isEmpty() || string2.isEmpty()) {
        return listOf(false, false)
    }
    return listOf(
        wildCardMatchingDP(
            string1,
            string2,
            string1.length - 1,
            string2.length - 1,
            Array(string1.length) { Array(string2.length) { -1 } }
        ),
        wildCardMatchingTabulation(string1, string2)
    )
}

private fun wildCardMatchingDP(
    string1: String,
    string2: String,
    index1: Int,
    index2: Int,
    dpMatrix: Array<Array<Int>>
): Boolean {
    if (index1 < 0 && index2 < 0) {
        return true
    }
    if (index1 < 0) {
        return false
    }
    if (index2 < 0) {
        for (i in 0 until index1) {
            if (string1[i] != '*') {
                return false
            }
        }
        return true
    }
    if (dpMatrix[index1][index2] != -1) {
        return dpMatrix[index1][index2] == 1
    }
    when (string1[index1]) {
        string2[index2], '?' -> {
            dpMatrix[index1][index2] =
                if (wildCardMatchingDP(string1, string2, index1 - 1, index2 - 1, dpMatrix)) 1 else 0
        }

        '*' -> {
            dpMatrix[index1][index2] =
                if (wildCardMatchingDP(string1, string2, index1 - 1, index2, dpMatrix) || wildCardMatchingDP(
                        string1,
                        string2,
                        index1,
                        index2 - 1,
                        dpMatrix
                    )
                ) 1 else 0
        }

        else -> {
            dpMatrix[index1][index2] = 0
        }
    }
    return dpMatrix[index1][index2] == 1
}

private fun wildCardMatchingTabulation(
    string1: String,
    string2: String
): Boolean {
    var previousRow = Array(string2.length + 1) { column ->
        column == 0
    }
    val currentRow = Array(string2.length + 1) { column ->
        column == 0
    }
    for (row in 1 until string1.length + 1) {
        currentRow[0] = if (string1[row - 1] == '*') {
            previousRow[0]
        } else {
            false
        }
        for (column in 1 until string2.length + 1) {
            currentRow[column] = when (string1[row - 1]) {
                string2[column - 1], '?' -> previousRow[column - 1]
                '*' ->  previousRow[column] || currentRow[column - 1]
                else -> false
            }
        }
        previousRow = currentRow.clone()
    }
    return previousRow[previousRow.size - 1]
}

