package algorithms.recursion

import utils.printList


// https://leetcode.com/problems/target-sum/description/

fun main() {
    val results = mutableListOf<MutableList<Int>>()
    findTargetSum(intArrayOf(1, 1, 1, 1, 1), 3, 0, 0, mutableListOf(), results)
    results.printList()
    results.clear()
    findTargetSum(intArrayOf(1), 1, 0, 0, mutableListOf(), results)
    results.printList()
}

private fun findTargetSum(
    array: IntArray,
    target: Int,
    currentSum: Int = 0,
    index: Int = 0,
    combinations: MutableList<Int> = mutableListOf(),
    results: MutableList<MutableList<Int>>
) {
    if (index == array.size) {
        if (target == currentSum) {
            val result = ArrayList<Int>()
            combinations.forEach {
                result.add(it)
            }
            results.add(result)
        }
        return
    }
    if (index > array.size) {
        return
    }
    combinations.add(array[index])
    findTargetSum(array, target, currentSum + array[index], index + 1, combinations, results)
    combinations.removeAt(combinations.size - 1)
    combinations.add(array[index] * -1)
    findTargetSum(array, target, currentSum - array[index], index + 1, combinations, results)
    combinations.removeAt(combinations.size - 1)
}