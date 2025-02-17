package algorithms.recursion

fun main() {
    val arr = intArrayOf(2, 3, 7, 5, 6)
    println(isArraySorted(arr, 4))
}

private fun isArraySorted(array: IntArray, index: Int): Boolean {
    if (array.size == 1 || index == 0) {
        return true
    }
    return if (array[index] >= array[index - 1]) {
        isArraySorted(array, index - 1)
    } else {
        false
    }
}