package algorithms.dynamic

import utils.printValue
import kotlin.math.abs

// https://www.naukri.com/code360/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494
// https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
// https://www.naukri.com/code360/problems/partitions-with-given-difference_3751628

fun main() {
    minSubSetDifference(arrayOf(1, 6, 11, 5)).printValue()
    minSubSetDifference(arrayOf(1, 5, 11, 5)).printValue()
    minSubSetDifference(arrayOf(3, 1, 5, 2, 8)).printValue()
    minSubSetDifference(arrayOf(1, 2, 3, 4)).printValue()
    minSubSetDifference(arrayOf(8, 6, 5)).printValue()
}

private fun minSubSetDifference(numbers: Array<Int>): Int {
    val targetSum = numbers.sum()
    return minSubSetDifference(numbers, targetSum)
}

private fun minSubSetDifference(numbers: Array<Int>, targetSum: Int): Int {
    val canMakeTargetMatrix: Array<Array<Boolean>> = Array(numbers.size + 1) { row ->
        Array(targetSum + 1) { column ->
            row == 0 && column == 0 || column == 0
        }
    }
    for (row in 1 until canMakeTargetMatrix.size) {
        for (column in 1 until canMakeTargetMatrix[0].size) {
            if (column < numbers[row - 1]) {
                canMakeTargetMatrix[row][column] = false
            } else {
                canMakeTargetMatrix[row][column] = canMakeTargetMatrix[row - 1][column] || canMakeTargetMatrix[row - 1][column - numbers[row - 1]]
            }
        }
    }
    var minDifference = Int.MAX_VALUE
    val size = 1 + (canMakeTargetMatrix[0].size - 1) / 2
    for (column in 1 until size) {
        if (canMakeTargetMatrix[canMakeTargetMatrix.size - 1][column]) {
            minDifference = minOf(minDifference, abs(targetSum - 2 * column))
        }
    }
    return minDifference
}