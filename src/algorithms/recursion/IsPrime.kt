package algorithms.recursion

import utils.printValue
import kotlin.math.sqrt

fun main() {
    isPrime(1).printValue()
    isPrime(2).printValue()
    isPrime(3).printValue()
    isPrime(4).printValue()
    isPrime(5).printValue()
    isPrime(7).printValue()
    isPrime(11).printValue()
    isPrime(13).printValue()
    isPrime(16).printValue()
}

private fun isPrime(num: Int, sqrt: Int = sqrt(num.toDouble()).toInt()): Boolean {
    if (sqrt == 1) {
        return true
    }
    return num % sqrt != 0 && isPrime(num, sqrt - 1)
}