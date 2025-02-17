package algorithms.dynamic

import utils.printList

// https://www.naukri.com/code360/problems/longest-common-substring_1235207

fun main() {
    findLCSubString("ab", "ad").printList()
    findLCSubString("abde", "ade").printList()
    findLCSubString("abcjklp", "acjkp").printList()
    findLCSubString("wasdijkl", "wsdjkl").printList()
    findLCSubString("tyfg", "cvbnuty").printList()
}

private fun findLCSubString(string1: String, string2: String): List<List<String>> {
    if (string1.isEmpty() || string2.isEmpty()) {
        return listOf(listOf(""))
    }
    val dpMatrix: Array<Array<List<String>>> = Array(string1.length) {
        Array(string2.length) {
            listOf()
        }
    }
    return listOf(
        findLCSubStringDP(string1, string2, string1.length - 1, string2.length - 1, dpMatrix),
        findLCSubStringTabulation(string1, string2)
    )
}

private fun findLCSubStringDP(string1: String, string2: String, index1: Int, index2: Int, dpMatrix: Array<Array<List<String>>>): List<String> {
    if (index1 < 0 || index2 < 0) {
        return mutableListOf("")
    }
    if (dpMatrix[index1][index2].isNotEmpty()) {
        return dpMatrix[index1][index2]
    }
    if (string1[index1] == string2[index2]) {
        val result = findLCSubStringDP(string1, string2, index1 - 1, index2 - 1, dpMatrix)
        val newResult: MutableList<String> = mutableListOf()
        result.forEach {
            newResult.add(it)
            if (it.isEmpty() || (index1 - 1 in string1.indices && index2 - 1 in string2.indices && it.last() == string1[index1 - 1] && it.last() == string2[index2 - 1])) {
                newResult.add(it + string1[index1])
            }
        }
        dpMatrix[index1][index2] = newResult.toList()
        return newResult
    }
    val result1 = findLCSubStringDP(string1, string2, index1 - 1, index2, dpMatrix).toMutableList()
    val result2 = findLCSubStringDP(string1, string2, index1, index2 - 1, dpMatrix)
    result2.forEach {
        if (!result1.contains(it)) {
            result1.add(it)
        }
    }
    dpMatrix[index1][index2] = result1.toList()
    return result1
}

private fun findLCSubStringTabulation(string1: String, string2: String): List<String> {
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
                    if (it.isEmpty() || (row - 2 in string1.indices && column - 2 in string2.indices && it.last() == string1[row - 2] && it.last() == string2[column - 2])) {
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

