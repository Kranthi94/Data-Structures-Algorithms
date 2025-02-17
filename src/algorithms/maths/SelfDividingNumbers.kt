package algorithms.maths

// https://leetcode.com/problems/self-dividing-numbers/

fun main() {
    println(selfDividing(51, 51))
}

private fun selfDividing(start: Int, end: Int): List<Int> {
    val list = mutableListOf<Int>()
    for (i in IntRange(start, end)) {
        var num = i
        var selfDividing = true
        abc@ while (num > 0) {
            val reminder = num % 10
            if (reminder > 0) {
                selfDividing = i % reminder == 0
                if (selfDividing) {
                    num /= 10
                } else {
                    break@abc
                }
            } else {
                selfDividing = false
                break@abc
            }
        }
        if (selfDividing) {
            list.add(i)
        }
    }
    return list
}