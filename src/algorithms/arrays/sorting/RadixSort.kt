package algorithms.arrays.sorting

import utils.printArray

// https://www.geeksforgeeks.org/radix-sort/

fun main() {
    val arr = intArrayOf(4, 3, 1, 6, 5, 10, 7, 8, 11, 9)
    println(doRadixSort(arr, 0).printArray())
}

//private fun doRadixSort(array: IntArray): IntArray {
//    val maxNumber = array.maxOrNull() ?: 0
//    val maxDigits = log10(maxNumber.toDouble()) + 1
//}

private fun doRadixSort(arr: IntArray, digit: Int): IntArray {
    if (digit > 1) {
        return arr
    }
    for (i in 1 until arr.size) {
        val num1 = findSignificantDigit(arr[i], digit)
        for (j in 0 until i) {
            val num2 = findSignificantDigit(arr[j], digit)
            if (num2 > num1) {
                var replacingDigit = arr[i]
                for (k in j until i + 1) {
                    val temp = arr[k]
                    arr[k] = replacingDigit
                    replacingDigit = temp
                }
                break
            }
        }
    }
    return doRadixSort(arr, digit.plus(1))
}

private fun findSignificantDigit(num: Int, digit: Int): Int {
    return (num / Math.pow(10.toDouble(), digit.toDouble()).toInt()) % 10
}