package algorithms.maths

import java.math.BigInteger

// https://www.geeksforgeeks.org/euclidean-algorithms-basic-and-extended/
fun main() {
    val arr = intArrayOf(3, 6, 15, 21, 27)
    println("LCM is : " + findLCM(BigInteger.valueOf(arr[0].toLong()), 1, arr))
}

private fun findGCD(num1: BigInteger, num2: BigInteger): BigInteger {
    return if (num1.compareTo(BigInteger.ZERO) == 0 || num2.compareTo(BigInteger.ONE) == 0) {
        num2
    } else if (num2.compareTo(BigInteger.ZERO) == 0 || num1.compareTo(BigInteger.ONE) == 0) {
        num1
    } else {
        if (num1 > num2) {
            findGCD(num1.mod(num2), num2)
        } else {
            findGCD(num1, num2.mod(num1))
        }
    }
}

private fun findLCM(initialLCM: BigInteger, startIndex: Int, arr: IntArray): BigInteger {
    if (startIndex == arr.size) {
        return initialLCM
    }
    val lcm = initialLCM.multiply(BigInteger.valueOf(arr[startIndex].toLong()))
        .divide(findGCD(initialLCM, BigInteger.valueOf(arr[startIndex].toLong())))
    return findLCM(lcm, startIndex + 1, arr)
}