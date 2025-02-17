package algorithms.arrays.sorting

import datastructures.heaps.MinHeap
import utils.printArray

// https://www.geeksforgeeks.org/heap-sort/

fun main() {
    val inputs = arrayOf(
        arrayOf(4, 3, 1, 6, 5, 2, 7, 8, 0),
        arrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9),
        arrayOf(4, 3, -1, 6, -5, 2, 7, -8, 0),
        arrayOf(1, 2, 3, 4, 5),
        arrayOf(5, 4, 3, 2, 1),
        arrayOf(5),
        arrayOf()
    )
    inputs.forEach {
        doHeapSort(it).printArray()
    }
}

private fun doHeapSort(array: Array<Int>): Array<Int> {
    if (array.size <= 1) {
        return array
    }
    val maxHeap: MinHeap<Int> = MinHeap()
    array.forEach {
        maxHeap.insert(it)
    }
    var result: Array<Int> = arrayOf()
    while (!maxHeap.isEmpty()) {
        result = result.plus(maxHeap.getMinValue())
        maxHeap.delete()
    }
    return result
}

