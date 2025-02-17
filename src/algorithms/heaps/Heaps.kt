package algorithms.heaps

import datastructures.heaps.MaxHeap
import datastructures.heaps.MinHeap
import utils.printValue

fun main() {
    val minHeap = MinHeap<Int>()
    minHeap.insert(3)
    minHeap.insert(4)
    minHeap.insert(5)
    minHeap.insert(1)
    minHeap.insert(6)
    minHeap.insert(7)
    minHeap.insert(2)
    minHeap.delete()
    minHeap.delete()
    minHeap.getMinValue().printValue()

    val maxHeap = MaxHeap<Int>()
    maxHeap.insert(3)
    maxHeap.insert(4)
    maxHeap.insert(5)
    maxHeap.insert(1)
    maxHeap.insert(6)
    maxHeap.insert(7)
    maxHeap.insert(2)
    maxHeap.delete()
    maxHeap.delete()
    maxHeap.getMaxValue().printValue()
}