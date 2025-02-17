package algorithms.maths

import utils.printValue

fun main() {
    findGCD(12, 36).printValue()
}

private fun findGCD(num1: Int, num2: Int): Int {
    return if (num1 == 0) {
        num2
    } else if (num2 == 0) {
        num1
    } else if (num1 < num2) {
        findGCD(num2 % num1, num1)
    } else {
        findGCD(num1 % num2, num2)
    }
}