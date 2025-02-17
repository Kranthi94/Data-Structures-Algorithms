package algorithms.arrays.searching

import utils.getElement

fun main() {
    val array = arrayOf(2, 4, 5, 7, 12, 18, 23, 48)
    println("Ceil of Number 16 is ${array.getElement(findCeil(array, 16))}")
    println("Ceil of Number 7 is ${array.getElement(findCeil(array, 7))}")
    println("Ceil of Number 2 is ${array.getElement(findCeil(array, 2))}")
    println("Ceil of Number 0 is ${array.getElement(findCeil(array, 0))}")
    println("Ceil of Number 49 is ${array.getElement(findCeil(array, 49))}")
}

private fun findCeil(array: Array<Int>, target: Int): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    if (target > array[endIndex]) {
        return -1
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        if (target == midValue) {
            return midIndex
        }
        if (target < midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return startIndex
}