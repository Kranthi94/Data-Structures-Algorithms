package algorithms.arrays.rotation

import utils.printList

// https://www.geeksforgeeks.org/maximum-sum-iarri-among-rotations-given-array/

fun main() {
    val arr = intArrayOf(8, 3, 1, 2)
    allRotationSum(arr).printList()
}

private fun allRotationSum(arr: IntArray): List<Int> {
    val list: MutableList<Int> = mutableListOf()
    repeat(arr.size) {
        list.add(allRotationSum(arr, it))
    }
    return list
}

private fun allRotationSum(arr: IntArray, rotation: Int): Int {
    var sum = 0
    arr.forEachIndexed { index, value ->
        sum += value* ((index + rotation) % arr.size)
    }
    return sum
}