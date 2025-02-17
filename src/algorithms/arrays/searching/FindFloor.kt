package algorithms.arrays.searching

import utils.getElement
import utils.printValue

fun main() {
    val array = arrayOf(2, 4, 5, 7, 12, 18, 23, 48)
    "Floor of Number 16 is ${array.getElement(findFloor(array, 16))}".printValue()
    "Floor of Number 7 is ${array.getElement(findFloor(array, 7))}".printValue()
    "Floor of Number 2 is ${array.getElement(findFloor(array, 2))}".printValue()
    "Floor of Number 0 is ${array.getElement(findFloor(array, 0))}".printValue()
}

private fun findFloor(array: Array<Int>, target: Int): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    if (target < array[startIndex]) {
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
    return endIndex
}