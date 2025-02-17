package algorithms.arrays.searching

// https://www.geeksforgeeks.org/interpolation-search/

fun main() {
    val arr = intArrayOf(1, 4, 5, 19, 23)
    doInterpolationSearch(arr, 24, 0, arr.size - 1)
}

private fun doInterpolationSearch(arr: IntArray, num: Int, startIndex: Int, endIndex: Int) {
    if (startIndex > endIndex || startIndex < 0 || endIndex >= arr.size) {
        println("Element not found")
    } else {
        val v1 = arr[startIndex]
        val v2 = arr[endIndex]
        val pos = startIndex + ((num - v1) * (endIndex - startIndex))/(v2 - v1)
        val v3 = arr[pos]
        when {
            num < v3 -> doInterpolationSearch(arr, num, startIndex, pos - 1)
            num > v3 -> doInterpolationSearch(arr, num, pos + 1, endIndex)
            else -> println("Element found at index $pos")
        }
    }
}