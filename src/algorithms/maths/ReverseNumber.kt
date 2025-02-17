package algorithms.maths

import utils.printList

fun main() {
    reverseNumber(348932).printList()
    reverseNumber(10400).printList()
    reverseNumber(-10400).printList()
    reverseNumber(1534236469).printList()
    "123".toIntOrNull()
}

private fun reverseNumber(num: Int): List<Long> {
    val isNegative = num < 0
    val finalNumber = if (isNegative) num * -1 else num
    return listOf(
//        if (isNegative) reverseNumberByString(finalNumber) * -1 else reverseNumberByString(finalNumber),
        if (isNegative) reverseNumberRecursion(finalNumber).first * -1 else reverseNumberRecursion(finalNumber).first
    )
}

private fun reverseNumberRecursion(num: Int): Pair<Long, Int> {
    val reminder = num % 10
    if (reminder == num) {
        return Pair(reminder.toLong(), 10)
    }
    val quotient = num / 10
    val result = reverseNumberRecursion(quotient)
    return Pair(reminder * result.second + result.first, result.second * 10)
}