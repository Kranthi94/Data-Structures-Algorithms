package algorithms.arrays.problems

import utils.printValue

// https://www.geeksforgeeks.org/problems/minimum-swaps/1

fun main() {
    val inputs = arrayOf(
        arrayOf(50, 10, 0, 80, 60, 30, 70, 40, 20)
    )
    inputs.forEach {
        findMinSwaps(it).printValue()
    }
}

private fun findMinSwaps(array: Array<Int>): Int {
    var pairsArray: Array<Pair<Int, Int>> = arrayOf()
    array.forEachIndexed { index, value ->
        pairsArray = pairsArray.plus(Pair(index, value))
    }
    pairsArray.sortBy { it.second }
    val visited: Array<Boolean> = Array(array.size) { false }
    var swaps = 0
    for (i in array.indices) {
        if (!visited[i]) {
            var count = 0
            var j = i
            while (!visited[j]) {
                visited[j] = true
                j = pairsArray[j].first
                count += 1
            }
            swaps += count - 1
        }
    }
    return swaps
}