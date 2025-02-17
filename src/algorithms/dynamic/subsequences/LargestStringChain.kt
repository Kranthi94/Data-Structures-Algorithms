package algorithms.dynamic.subsequences

import utils.printList

// https://www.naukri.com/code360/problems/longest-string-chain_3752111

fun main() {
    val inputs = arrayOf(arrayOf("x", "xx", "y", "xyx"), arrayOf("m", "nm", "mmm"), arrayOf("a", "bc", "ad", "adc", "bcd"))
    inputs.forEach {
        largestStringChain(it).printList()
    }
}

private fun largestStringChain(strings: Array<String>): List<String> {
    val maxLengthArray: Array<Int> = Array(strings.size) { 1 }
    val hashArray: Array<Int> = Array(strings.size) { it }
    var maxLengthIndex = 0
    for (i in 1 until strings.size) {
        for (j in 0 until i) {
            if (strings[j].length + 1 == strings[i].length && checkStringContains(strings[i], strings[j]) && maxLengthArray[i] < 1 + maxLengthArray[j]) {
                maxLengthArray[i] = 1 + maxLengthArray[j]
                hashArray[i] = j
            }
        }
        if (maxLengthArray[maxLengthIndex] < maxLengthArray[i]) {
            maxLengthIndex = i
        }
    }
    val result: MutableList<String> = mutableListOf()
    var currentIndex = maxLengthIndex
    while (currentIndex != hashArray[currentIndex]) {
        result.add(0, strings[currentIndex])
        currentIndex = hashArray[currentIndex]
    }
    result.add(0, strings[currentIndex])
    return result
}

private fun checkStringContains(superString: String, subString: String): Boolean {
    var size = 0
    subString.forEach {
        if (superString.contains(it)) {
            size += 1
        }
    }
    return size == subString.length
}