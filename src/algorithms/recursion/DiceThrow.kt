package algorithms.recursion

import utils.printValue
import utils.printArray

fun main() {
    diceThrowCount(5, 3).printValue()
    diceThrowCombinations(5, 3).printArray()
}

private fun diceThrowCount(num: Int, maxDice: Int): Int {
    if (num == 0 || num == 1) {
        return 1
    }
    var result = 0
    var count = 1
    while (count <= num.coerceAtMost(maxDice)) {
        result += diceThrowCount(num - count, maxDice)
        count++
    }
    return result
}

private fun diceThrowCombinations(num: Int, maxDice: Int): Array<String> {
    if (num == 0) {
        return arrayOf("")
    }
    var prefix = 1
    var result = arrayOf<String>()
    var count = 1
    while (count <= num.coerceAtMost(maxDice)) {
        val throws = diceThrowCombinations(num - count, maxDice)
        var newArray = arrayOf<String>()
        throws.forEach {
            newArray = newArray.plus(prefix.toString() + it)
        }
        result = result.plus(newArray)
        prefix++
        count++
    }
    return result
}