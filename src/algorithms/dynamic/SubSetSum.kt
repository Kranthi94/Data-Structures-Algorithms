package algorithms.dynamic

import utils.printPair

fun main() {
    findSubSets(arrayOf(2, 3, 5), 7).printPair()
    findSubSets(arrayOf(1, 2, 3, 5, 6), 8).printPair()
}

private fun findSubSets(numbers: Array<Int>, sum: Int): Pair<List<List<Int>>, List<List<Int>>> {
    return Pair(
        findSubSetsRecursive(numbers, sum, 0),
        findSubSetsTabulation(numbers, sum)
    )
}

private fun findSubSetsRecursive(numbers: Array<Int>, sum: Int, currentIndex: Int): MutableList<MutableList<Int>> {
    if (sum == 0) {
        return mutableListOf(mutableListOf())
    }
    if (sum < 0 || currentIndex !in numbers.indices) {
        return mutableListOf()
    }
    val result1 = findSubSetsRecursive(numbers, sum - numbers[currentIndex], currentIndex + 1)
    val result2 = findSubSetsRecursive(numbers, sum, currentIndex + 1)
    if (result1.isNotEmpty()) {
        result1.forEach {
            it.add(0, numbers[currentIndex])
        }
    }
    result1.addAll(result2)
    return result1
}

private fun findSubSetsTabulation(numbers: Array<Int>, sum: Int): List<List<Int>> {
    val subSetMatrix = Array<Array<List<List<Int>>>>(numbers.size + 1) { row ->
        Array(sum + 1) { column ->
            if (row == 0 && column == 0 || column == 0) {
                listOf(listOf())
            } else if (row == 0) {
                listOf()
            } else {
                listOf()
            }
        }
    }
    for (row in 1 until subSetMatrix.size) {
        for (column in 1 until subSetMatrix[0].size) {
            if (column < numbers[row - 1]) {
                subSetMatrix[row][column] = subSetMatrix[row - 1][column]
            } else {
                val result1 = subSetMatrix[row - 1][column - numbers[row - 1]]
                val result2 = subSetMatrix[row - 1][column]
                val newResult1: MutableList<List<Int>> = mutableListOf()
                if (result1.isNotEmpty()) {
                    result1.forEach { list ->
                        val r1: MutableList<Int> = list.toMutableList()
                        r1.add(numbers[row - 1])
                        newResult1.add(r1)
                    }
                }
                if (result2.isNotEmpty()) {
                    result2.forEach { list ->
                        if (!newResult1.contains(list)) {
                            newResult1.add(list)
                        }
                    }
                }
                subSetMatrix[row][column] = newResult1
            }
        }
    }
    return subSetMatrix[subSetMatrix.size - 1][subSetMatrix[0].size - 1]
}

