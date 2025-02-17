package algorithms.arrays.searching

// https://www.geeksforgeeks.org/repeatedly-search-element-doubling-every-successful-search/?ref=rp

private var finalNumber: Int = -1

fun main() {
    val arr = intArrayOf(1, 4, 5, 8, 23)
    doRepeatedSearch(arr, 4, 1)
}

private fun doRepeatedSearch(arr: IntArray, num: Int, endIndex: Int) {
    var localIndex = endIndex
    if (localIndex >= arr.size) {
        localIndex = arr.size - 1
    }
    val num1 = arr[localIndex]
    when {
        num > num1 -> doRepeatedSearch(arr, num, localIndex * 2)
        num < num1 -> doBinarySearch(arr, num, localIndex / 2, localIndex)
        else -> {
            finalNumber = num
            doRepeatedSearch(arr, num * 2, localIndex * 2)
        }
    }
}

private fun doBinarySearch(arr: IntArray, num: Int, startIndex: Int, endIndex: Int) {
    if (startIndex == endIndex || startIndex < 0 || endIndex >= arr.size) {
        if (finalNumber != -1 ) println("Final value is $finalNumber") else println("Element not found")
    } else {
        val indexSum = startIndex + endIndex
        val mid = if (indexSum % 2 == 0) indexSum / 2 else (indexSum - 1) / 2
        val midValue = arr[mid]
        when {
            num < midValue -> doBinarySearch(arr, num, startIndex, mid - 1)
            num > midValue -> doBinarySearch(arr, num, mid + 1, endIndex)
            else -> {
                finalNumber = num
                doRepeatedSearch(arr, num * 2, mid * 2)
            }
        }
    }
}