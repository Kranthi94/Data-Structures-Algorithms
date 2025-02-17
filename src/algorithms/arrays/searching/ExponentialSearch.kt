package algorithms.arrays.searching

// https://www.geeksforgeeks.org/exponential-search/

fun main() {
    val arr = intArrayOf(1, 4, 5, 19, 23, 25, 26)
    doExponentialSearch(arr, 25, 1)
}

private fun doExponentialSearch(arr: IntArray, num: Int, endIndex: Int) {
    var localIndex = endIndex
    if (localIndex >= arr.size) {
        localIndex = arr.size - 1
    }
    val num1 = arr[localIndex]
    when {
        num > num1 -> doExponentialSearch(arr, num, localIndex * 2)
        num < num1 -> doBinarySearch(arr, num, localIndex / 2, localIndex)
        else -> println("Element found at index $localIndex")
    }
}

private fun doBinarySearch(arr: IntArray, num: Int, startIndex: Int, endIndex: Int) {
    if (startIndex == endIndex || startIndex < 0 || endIndex >= arr.size) {
        println("Element not found")
    } else {
        val indexSum = startIndex + endIndex
        val mid = if (indexSum % 2 == 0) indexSum / 2 else (indexSum - 1) / 2
        val midValue = arr[mid]
        when {
            num < midValue -> doBinarySearch(arr, num, startIndex, mid - 1)
            num > midValue -> doBinarySearch(arr, num, mid + 1, endIndex)
            else -> println("Element found at index $mid")
        }
    }
}