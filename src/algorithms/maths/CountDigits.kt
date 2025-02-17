package algorithms.maths

import utils.printValue

fun main() {
    countDigits(54321).printValue()
    countDigits(0).printValue()
    countDigits(-35478).printValue()
}

private fun countDigits(num: Int): Int {
    val reminder = num % 10
    if (reminder == num) {
        return 1
    }
    val quotient = num / 10
    return 1 + countDigits(quotient)
}