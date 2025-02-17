package algorithms.arrays.searching

import utils.printArray

fun main() {
    val array = arrayOf(
        arrayOf(10, 20, 30, 40),
        arrayOf(12, 22, 32, 42),
        arrayOf(14, 24, 34, 44),
        arrayOf(16, 26, 36, 46)
    )
    findTarget(array, 12).printArray()
    findTarget(array, 34).printArray()
}

private fun findTarget(array: Array<Array<Int>>, target: Int): IntArray {
    var columnIndex = array[0].size - 1
    var rowIndex = 0
    while (columnIndex >= 0) {
        val element = array[0][columnIndex]
        if (element > target) {
            columnIndex -= 1
        } else if (element == target) {
            return arrayOf(0, columnIndex).toIntArray()
        } else {
            break
        }
    }
    if (columnIndex >= 0) {
        while (rowIndex < array.size) {
            val element = array[rowIndex][columnIndex]
            if (element < target) {
                rowIndex += 1
            } else if (element == target) {
                return arrayOf(rowIndex, columnIndex).toIntArray()
            } else {
                break
            }
        }
    }
    return IntArray(2) { -1 }
}