package datastructures.heaps

import utils.swap

class MinHeap<T : Comparable<T>> {

    private var arrayList: ArrayList<T> = ArrayList()

    fun isEmpty(): Boolean {
        return arrayList.isEmpty()
    }

    fun getMinValue(): T {
        return arrayList[0]
    }

    fun insert(value: T) {
        arrayList.add(value)
        upHeap(arrayList.size - 1)
    }

    fun delete() {
        if (arrayList.isEmpty()) {
            return
        }
        arrayList.swap(0, arrayList.size - 1)
        arrayList.removeAt(arrayList.size - 1)
        downHeap(0)
    }

    private fun getParentIndex(index: Int): Int {
        return (index - 1) / 2
    }

    private fun getLeftIndex(index: Int): Int {
        return 2 * index + 1
    }

    private fun getRightIndex(index: Int): Int {
        return 2 * index + 2
    }

    private fun upHeap(currentIndex: Int) {
        if (currentIndex == 0) {
            return
        }
        val parentIndex = getParentIndex(currentIndex)
        if (arrayList[parentIndex] > arrayList[currentIndex]) {
            arrayList.swap(parentIndex, currentIndex)
            upHeap(parentIndex)
        }
    }

    private fun downHeap(currentIndex: Int) {
        var minIndex = currentIndex
        val leftIndex = getLeftIndex(currentIndex)
        val rightIndex = getRightIndex(currentIndex)
        if (leftIndex !in arrayList.indices || rightIndex !in arrayList.indices) {
            return
        }
        if (arrayList[leftIndex] < arrayList[minIndex]) {
            minIndex = leftIndex
        }
        if (arrayList[rightIndex] < arrayList[minIndex]) {
            minIndex = rightIndex
        }
        if (minIndex != currentIndex) {
            arrayList.swap(currentIndex, minIndex)
            downHeap(minIndex)
        }
    }
}