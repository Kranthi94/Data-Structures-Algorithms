package algorithms.recursion

import utils.printValue

// https://www.geeksforgeeks.org/problems/handshakes1303/1

fun main() {
    findHandShakeCombinations(1, 4).printValue()
    findHandShakeCombinations(1, 6).printValue()
    findHandShakeCombinations(1, 8).printValue()
    findHandShakeCombinations(1, 10).printValue()

    println()

    findHandShakeCombinations(4).printValue()
    findHandShakeCombinations(6).printValue()
    findHandShakeCombinations(8).printValue()
    findHandShakeCombinations(10).printValue()
}

private fun findHandShakeCombinations(size: Int): Int {
    if (size <= 2) {
        return 1
    }
    var count = 0
    for (i in (0..size - 2).step(2)) {
        count += findHandShakeCombinations(i) * findHandShakeCombinations(size - 2 - i)
    }
    return count
}

private fun findHandShakeCombinations(startNum: Int, endNum: Int): Int {
    if (startNum + 1 >= endNum) {
        return 1
    }
    if (startNum + 3 == endNum) {
        return 2
    }
    var increment = startNum + 1
    var count = 0
    while (increment <= endNum) {
        val leftHandShakes = findHandShakeCombinations(startNum + 1, increment - 1)
        val rightHandShakes = findHandShakeCombinations(increment + 1 , endNum)
        count += leftHandShakes * rightHandShakes
        increment += 2
    }
    return count
}