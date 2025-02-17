package algorithms.arrays.rotation

import utils.printArray

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    doJugglingAlgorithm(arr, 2)
}

private fun doJugglingAlgorithm(arr: IntArray, d: Int) {
    val gcd = findGCD(arr.size, d)
    for (i in 0 until d) {
        val temp = arr[i]
        for (j in i until arr.size step gcd) {
            if (j + gcd <= arr.size - 1) {
                arr[j] = arr[j + gcd]
            } else {
                arr[j] = temp
            }
        }
    }
    arr.printArray()
}

private fun findGCD(num1: Int, num2: Int): Int {
    return if (num1 == 0 || num2 == 1) {
        num2
    } else if (num2 == 0 || num1 == 1) {
        num1
    } else {
        if (num1 > num2) {
            findGCD(num1 % num2, num2)
        } else {
            findGCD(num1, num2 % num1)
        }
    }
}