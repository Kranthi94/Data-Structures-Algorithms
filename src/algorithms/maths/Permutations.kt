package algorithms.maths

fun main() {
    println(findNumber(5, 25))
    println(findNumber(5, 39))
    println(findNumber(5, 48))
}

private fun findNumber(n: Int, k: Int): String {
    val stringBuilder: StringBuilder = StringBuilder("")
    val numString: StringBuilder = StringBuilder("")
    var reminder = k
    var num = n
    for (i in 0 until n) {
        numString.append(i + 1)
    }
    while (num > 0) {
        val factorial = factorial(num - 1)
        var quotient = reminder / factorial
        reminder %= factorial
        if (reminder == 0) {
            quotient -= 1
        }
        if (quotient < 0) {
            quotient = numString.length - 1
        }
        stringBuilder.append(numString[quotient])
        numString.deleteCharAt(quotient)
        num -= 1
    }
    stringBuilder.append(numString)
    return stringBuilder.toString()
}

private fun factorial(n: Int): Int {
   if (n == 0) {
        return 1
    }
    return n * factorial(n - 1)
}