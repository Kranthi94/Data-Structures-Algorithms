package algorithms.recursion

import utils.printArray

fun main() {
    var array = charArrayOf('h','e','l','l','o')
    reverseString(array, 0, array.size - 1)
    array = charArrayOf('H','a','n','n','a','h')
    array.reverse()
    reverseString(array, 0, array.size - 1)
}

private fun reverseString(array: CharArray, startIndex: Int, endIndex: Int) {
    if (startIndex > endIndex) {
        array.printArray()
        return
    }
    val temp = array[startIndex]
    array[startIndex] = array[endIndex]
    array[endIndex] = temp
    reverseString(array, startIndex + 1, endIndex - 1)
}