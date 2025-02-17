package algorithms.dynamic.subsequences

import utils.printPair

// https://www.naukri.com/code360/problems/longest-bitonic-sequence_1062688

fun main() {
    val inputs = arrayOf(arrayOf(1, 11, 2, 10, 4, 5, 2, 1), arrayOf(1, 2, 1, 2, 1), arrayOf(1, 2, 1, 3, 4))
    inputs.forEach {
        findLargestBitonicSubSequence(it).printPair()
    }
}

private fun findLargestBitonicSubSequence(numbers: Array<Int>): Pair<List<List<Int>>, Int> {
    val finalResult: MutableList<List<Int>> = mutableListOf()
    var maxLength = 0
    for (i in 0 until numbers.size + 1) {
        val result: MutableList<Int> = mutableListOf()
        val left = getLargestSubSequence(numbers.sliceArray(0 until i))
        val right = getLargestSubSequence(numbers.sliceArray(i until numbers.size).reversedArray())
        result.addAll(left)
        result.addAll(right.reversed())
        maxLength = maxOf(maxLength, result.size)
        if (!finalResult.contains(result)) {
            finalResult.add(result)
        }
    }
    return Pair(finalResult, maxLength)
}

private fun getLargestSubSequence(numbers: Array<Int>): List<Int> {
    if (numbers.isEmpty()) {
        return listOf()
    }
    val maxLengthArray: Array<Int> = Array(numbers.size) { 1 }
    val hashArray: Array<Int> = Array(numbers.size) { it }
    var maxLengthIndex = 0
    for (i in 1 until numbers.size) {
        for (j in 0 until i) {
            if (numbers[j] < numbers[i] && maxLengthArray[i] < 1 + maxLengthArray[j]) {
                maxLengthArray[i] = 1 + maxLengthArray[j]
                hashArray[i] = j
            }
        }
        if (maxLengthArray[maxLengthIndex] < maxLengthArray[i]) {
            maxLengthIndex = i
        }
    }
    val result: MutableList<Int> = mutableListOf()
    var currentIndex = maxLengthIndex
    while (currentIndex != hashArray[currentIndex]) {
        result.add(0, numbers[currentIndex])
        currentIndex = hashArray[currentIndex]
    }
    result.add(0, numbers[currentIndex])
    return result
}