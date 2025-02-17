package algorithms.maths

// https://www.geeksforgeeks.org/finding-sum-of-digits-of-a-number-until-sum-becomes-single-digit/

fun main() {
    val n = 12345678
    println(findSingleDigitSum(n))
}

private fun findSingleDigitSum(n: Int): Int {
    var index = n
    var sum = 0
    while (index > 0) {
        sum += index % 10
        index /= 10
    }
    return sum
}