package algorithms.recursion

import utils.printList
import utils.printValue

fun main() {
    findSubSequences("abc").printList()
    findSubSequencesPowerSet("abc").maxOf { it.length }.printValue()
}

private fun findSubSequences(string: String): List<List<String>> {
    if (string.isEmpty()) {
        return listOf(listOf(""))
    }
    if (string.length == 1) {
        return listOf(listOf("", string))
    }
    return listOf(
        findSubSequencesRecursion(string, "", 0),
        findSubSequencesPowerSet(string)
    )
}

private fun findSubSequencesRecursion(string: String, subSequence: String, currentIndex: Int): MutableList<String> {
    if (currentIndex == string.length - 1) {
        return mutableListOf(subSequence + string[currentIndex], subSequence)
    }
    val take = findSubSequencesRecursion(string,  subSequence + string[currentIndex], currentIndex + 1)
    val notTake = findSubSequencesRecursion(string, subSequence, currentIndex + 1)
    notTake.forEach {
        if (!take.contains(it)) {
            take.add(it)
        }
    }
    return take
}

private fun findSubSequencesPowerSet(string: String): MutableList<String> {
    val subSequenceList: MutableList<String> = mutableListOf()
    val length = string.length
    val noOfSubSequences = 1 shl length
    for (i in 0 until noOfSubSequences) {
        var subSequence = ""
        for (j in 0 until length) {
            if (i and (1 shl j) != 0) {
                subSequence += string[j]
            }
        }
        if (!subSequenceList.contains(subSequence)) {
            subSequenceList.add(subSequence)
        }
    }
    return subSequenceList
}