package algorithms.dynamic.subsequences

import utils.printList
import utils.printValue

// https://www.naukri.com/code360/problems/longest-increasing-subsequence_630459

fun main() {
    val inputs = arrayOf(arrayOf(1, 3, 2), arrayOf(5, 4, 11, 1, 16, 8), arrayOf(1, 7, 8, 4, 5, 6, -1, 9))
    inputs.forEach {
        getAllLargestIncreasingSubSequences(it).printList()
        getLargestSubSequence(it).printList()
        getLargestSubSequenceLength(it).printValue()
    }
}

private fun getAllLargestIncreasingSubSequences(numbers: Array<Int>): List<List<List<Int>>> {
    if (numbers.isEmpty()) {
        return listOf()
    }
    if (numbers.size == 1) {
        return listOf(listOf(listOf(numbers[0])), listOf(listOf(numbers[0])))
    }
    val maxNumbers: Array<Int> = Array(numbers.size + 1) {
        if (it == 0) {
            0
        } else {
            numbers[it - 1]
        }
    }
    return listOf(
        longestIncreasingSubSequenceDP(numbers, 0, 0, Array(numbers.size) { Array(numbers.max()) { listOf() } }),
        longestIncreasingSubSequenceDP(numbers, 0, maxNumbers,0, Array(numbers.size) { Array(maxNumbers.size) { listOf() } }),
        longestIncreasingSubSequenceTabulation(numbers, maxNumbers)
    )
}

private fun longestIncreasingSubSequenceDP(
    numbers: Array<Int>,
    currentIndex: Int,
    currentMaxValue: Int,
    dpMatrix: Array<Array<List<List<Int>>>>
): List<List<Int>> {
    if (currentIndex == numbers.size - 1) {
        return if (currentMaxValue < numbers[currentIndex]) {
            listOf(MutableList(1) { numbers[currentIndex] })
        } else {
            listOf(listOf())
        }
    }
    if (dpMatrix[currentIndex][currentMaxValue].isNotEmpty()) {
        return dpMatrix[currentIndex][currentMaxValue]
    }
    val result: MutableList<List<Int>> = mutableListOf()
    if (currentMaxValue < numbers[currentIndex]) {
        val take =
            longestIncreasingSubSequenceDP(numbers, currentIndex + 1, numbers[currentIndex], dpMatrix).toMutableList()
        result.addAll(take)
        take.forEach {
            val newList = it.toMutableList()
            newList.add(0, numbers[currentIndex])
            if (!result.contains(newList)) {
                result.add(newList)
            }
        }
    }
    val noTake = longestIncreasingSubSequenceDP(numbers, currentIndex + 1, currentMaxValue, dpMatrix)
    noTake.forEach {
        if (!result.contains(it)) {
            result.add(it)
        }
    }
    val finalResult = result.sortedBy { it.size }
    dpMatrix[currentIndex][currentMaxValue] = finalResult
    return finalResult
}

private fun longestIncreasingSubSequenceDP(
    numbers: Array<Int>,
    currentIndex: Int,
    maxNumbers: Array<Int>,
    currentMaxIndex: Int,
    dpMatrix: Array<Array<List<List<Int>>>>
): List<List<Int>> {
    if (currentIndex == numbers.size - 1) {
        return if (maxNumbers[currentMaxIndex] < numbers[currentIndex]) {
            listOf(MutableList(1) { numbers[currentIndex] })
        } else {
            listOf(listOf())
        }
    }
    if (dpMatrix[currentIndex][currentMaxIndex].isNotEmpty()) {
        return dpMatrix[currentIndex][currentMaxIndex]
    }
    val result: MutableList<List<Int>> = mutableListOf()
    if (maxNumbers[currentMaxIndex] < numbers[currentIndex]) {
        val take = longestIncreasingSubSequenceDP(
            numbers,
            currentIndex + 1,
            maxNumbers,
            currentIndex + 1,
            dpMatrix
        ).toMutableList()
        result.addAll(take)
        take.forEach {
            val newList = it.toMutableList()
            newList.add(0, numbers[currentIndex])
            if (!result.contains(newList)) {
                result.add(newList)
            }
        }
    }
    val noTake = longestIncreasingSubSequenceDP(numbers, currentIndex + 1, maxNumbers, currentMaxIndex, dpMatrix)
    noTake.forEach {
        if (!result.contains(it)) {
            result.add(it)
        }
    }
    val finalResult = result.sortedBy { it.size }
    dpMatrix[currentIndex][currentMaxIndex] = finalResult
    return finalResult
}

private fun longestIncreasingSubSequenceTabulation(
    numbers: Array<Int>,
    maxNumbers: Array<Int>
): List<List<Int>> {
    var aheadRow: Array<List<List<Int>>> = Array(maxNumbers.size) {
        listOf(listOf())
    }
    val currentRow: Array<List<List<Int>>> = Array(maxNumbers.size) {
        listOf(listOf())
    }
    for (row in numbers.size - 1 downTo 0) {
        for (column in aheadRow.indices) {
            val result: MutableList<List<Int>> = mutableListOf()
            if (maxNumbers[column] < numbers[row]) {
                val take = aheadRow[row + 1]
                result.addAll(take)
                take.forEach {
                    val newList = it.toMutableList()
                    newList.add(0, numbers[row])
                    if (!result.contains(newList)) {
                        result.add(newList)
                    }
                }
            }
            val noTake = aheadRow[column]
            noTake.forEach {
                if (!result.contains(it)) {
                    result.add(it)
                }
            }
            val finalResult = result.sortedBy { it.size }
            currentRow[column] = finalResult
        }
        aheadRow = currentRow.clone()
    }
    return aheadRow[0]
}

private fun getLargestSubSequence(numbers: Array<Int>): List<Int> {
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

private fun getLargestSubSequenceLength(numbers: Array<Int>): Int {
    val list = mutableListOf(numbers[0])
    var currentIndex = 0
    var maxLength = 1
    for (i in 1 until numbers.size) {
        val number = numbers[i]
        val floor = findFloor(list, number, IntRange(0, currentIndex))
        if (floor != -1) {
            list.add(floor + 1, number)
            currentIndex = floor + 1
            maxLength = maxOf(maxLength, currentIndex + 1)
        }
    }
    return maxLength
}

private fun findFloor(numbers: List<Int>, target: Int, range: IntRange): Int {
    var startIndex = range.first
    var endIndex = range.last
    if (target <= numbers[0]) {
        return -1
    }
    if (target >= numbers[numbers.size - 1]) {
        return endIndex
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = numbers[midIndex]
        if (target == midValue) {
            return midIndex
        }
        if (target < midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return endIndex
}