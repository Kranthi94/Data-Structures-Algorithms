package algorithms.dynamic.subsequences

import utils.printList

// https://www.naukri.com/code360/problems/divisible-set_3754960

fun main() {
    val inputs = arrayOf(arrayOf(1, 16, 7, 8, 4))
    inputs.forEach {
        largestDivisibleSubSet(it).printList()
    }
}

private fun largestDivisibleSubSet(array: Array<Int>): List<Int> {
    val numbers = array.sorted()
    val maxLengthArray: Array<Int> = Array(numbers.size) { 1 }
    val hashArray: Array<Int> = Array(numbers.size) { it }
    var maxLengthIndex = 0
    for (i in 1 until numbers.size) {
        for (j in 0 until i) {
            if (numbers[i] % numbers[j] == 0 && maxLengthArray[i] < 1 + maxLengthArray[j]) {
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