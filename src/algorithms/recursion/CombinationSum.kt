package algorithms.recursion

import utils.printList

// https://leetcode.com/problems/combination-sum/description/

fun main() {
    combinationSum(intArrayOf(2, 3, 6, 7), 7, arrayListOf(), mutableMapOf()).printList()
    combinationSum(intArrayOf(8, 7, 4, 3), 11, arrayListOf(), mutableMapOf()).printList()

    println()

    val results = ArrayList<ArrayList<Int>>()
    combinationSum(intArrayOf(2, 3, 6, 7), 7, 0, arrayListOf(), results)
    results.printList()
    results.clear()
    combinationSum(intArrayOf(8, 7, 4, 3), 11, 0, arrayListOf(), results)
    results.printList()
    results.clear()
}

private fun combinationSum(array: IntArray, target: Int, index: Int, combinations: ArrayList<Int>, results: ArrayList<ArrayList<Int>>) {
    if (target == 0) {
        val list = arrayListOf<Int>()
        combinations.forEach {
            list.add(it)
        }
        results.add(list)
        return
    }
    if (index >= array.size || target < 0) {
        return
    }
    combinations.add(array[index])
    combinationSum(array, target - array[index], index, combinations, results)
    combinations.removeAt(combinations.size - 1)
    combinationSum(array, target, index + 1, combinations, results)
}

private fun combinationSum(array: IntArray, target: Int, result: ArrayList<Int>, map: MutableMap<Int, ArrayList<ArrayList<Int>>>): ArrayList<ArrayList<Int>> {
    if (target < 0) {
        return arrayListOf()
    }
    if (target == 0) {
        val newList = ArrayList<Int>()
        result.forEach {
            newList.add(it)
        }
        return arrayListOf(newList)
    }
    if (map.containsKey(target)) {
        return map[target]!!
    }
    val list = ArrayList<ArrayList<Int>>()
    for (i in array.indices) {
        result.add(array[i])
        result.sort()
        val r1 = combinationSum(array, target - array[i], result, map)
        r1.forEach {
            if(it.isNotEmpty() && !list.contains(it)) {
                list.add(it)
            }
        }
        result.remove(array[i])
    }
    map[target] = list
    return list
}