package algorithms.maths

import kotlin.math.max

// https://leetcode.com/problems/maximum-product-of-three-numbers/

fun main() {
    val array = intArrayOf(2, -5, 4, 8, -1, 10, -2, 5)
    println("Max Product 1 -> ${maxProduct1(array)}")
    println("Max Product 2 -> ${maxProduct2(array)}")
}

private fun maxProduct1(array: IntArray): Int {
    val size = array.size
    array.sort()
    return max(array[0] * array[1] * array[size - 1], array[size - 3] * array[size - 2] * array[size - 1])
}

private fun maxProduct2(array: IntArray): Int {
    var (min1, min2) = arrayOf(Int.MAX_VALUE, Int.MAX_VALUE)
    var (max1, max2, max3) = arrayOf(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)
    array.forEach {
        if (it >= max1) {
            max3 = max2
            max2 = max1
            max1 = it
        } else if (it >= max2) {
            max3 = max2
            max2 = it
        } else if (it >= max3) {
            max3 = it
        }
        if (it <= min1) {
            min2 = min1
            min1 = it
        } else if(it <= min2) {
            min2 = it
        }
    }
    return max(min2 * min1 * max1, max3 * max2 * max1)
}