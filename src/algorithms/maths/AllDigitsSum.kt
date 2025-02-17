package algorithms.maths

// https://www.geeksforgeeks.org/count-sum-of-digits-in-numbers-from-1-to-n/

fun main() {
    println(findAllDigitsSum(10))
    println(findAllDigitsSum(15))
    println(findAllDigitsSum(25))
    println(findAllDigitsSum(30))
    println(findAllDigitsSum(40))
}

private fun findAllDigitsSum(n: Int): Int {
    val quotient = n / 10
    val reminder = n % 10
    var sum = 0
    if (quotient > 0) {
        sum += 10 * (quotient * (quotient - 1)) / 2
        sum += 45 * quotient
    }
    sum += quotient * (reminder + 1)
    sum += (reminder * (reminder + 1)) / 2
    return sum
}