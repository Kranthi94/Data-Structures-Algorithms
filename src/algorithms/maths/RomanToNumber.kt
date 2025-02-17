package algorithms.maths

// https://leetcode.com/problems/roman-to-integer/

fun main() {
    println(romanToInt("MCMXCIV"))
}

private fun romanToInt(string: String): Int {
    val array = charArrayOf('I', 'V', 'X', 'L', 'C', 'D', 'M')
    var sum = getValue(string[0])
    for (i in 1 until string.length) {
        val previous = string[i - 1]
        val current = string[i]
        val compare = compare(array, previous, current)
        sum += if (compare) {
            getValue(current)
        } else {
            getValue(current) - 2 * getValue(previous)
        }
    }
    return sum
}

private fun compare(array: CharArray, a: Char, b: Char): Boolean {
    return array.indexOf(a) >= array.indexOf(b)
}

private fun getValue(char: Char): Int {
    return when(char) {
        'I' -> 1
        'V' -> 5
        'X' -> 10
        'L' -> 50
        'C' -> 100
        'D' -> 500
        else -> 1000
    }
}