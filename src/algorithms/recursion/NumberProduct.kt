package algorithms.recursion

import utils.printValue

fun main() {
    findProduct1(123, 123).printValue()
    findProduct1(2, 2).printValue()
    findProduct1(10, 10).printValue()
    findProduct1(12, 12).printValue()
    findProduct1(25, 25).printValue()

    println()

    findProduct2(123, 123).printValue()
    findProduct2(2, 2).printValue()
    findProduct2(10, 10).printValue()
    findProduct2(12, 12).printValue()
    findProduct2(25, 25).printValue()
}

private fun findProduct2(n1: Int, n2: Int): Int {
    if (n1 == 0 || n2 == 0) {
        return 0
    }
    if (n1 % 10 == n1 && n2 % 10 == n2) {
        return n1 * n2
    }
    if (n2 % 10 == n2) {
        return findProduct2(n2, n1)
    }
    var num = n2
    var result = 0
    var multiplier = 1
    while (num > 0) {
        result += multiplier * findProduct2(n1, num % 10)
        multiplier *= 10
        num /= 10
    }
    return result
}

private fun findProduct1(n1: Int, n2: Int): Int {
    var num2 = n2
    var multiplier1 = 1
    var result1 = 0
    while (num2 > 0) {
        var num1 = n1
        val r2 = num2 % 10
        var result2 = 0
        var multiplier2 = 1
        while (num1 > 0) {
            val r1 = num1 % 10
            result2 += multiplier2 * r1 * r2
            multiplier2 *= 10
            num1 /= 10
        }
        result1 += multiplier1 * result2
        multiplier1 *= 10
        num2 /= 10
    }
    return result1
}

//    var n2 = num2
//    var result = 0
//    var multiplier2 = 1
//    while (n2 > 0) {
//        var n1 =  num1
//        val r2 = n2 % 10
//        var multiplier1 = 1
//        while (n1 > 0) {
//            val r1 = n1 % 10
//            result += multiplier1 * r1 * r2
//            multiplier1 *= 10
//            n1 /= 10
//        }
//        println(result)
//        result += multiplier2 * result
//        multiplier2 *= 10
//        n2 /= 10
//    }
//    return result
