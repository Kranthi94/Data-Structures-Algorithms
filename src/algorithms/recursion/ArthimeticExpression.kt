package algorithms.recursion

import utils.printValue

// https://www.hackerrank.com/challenges/arithmetic-expressions/problem?isFullScreen=true

//100
//1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1

fun main() {
    arithmeticExpressions(arrayOf(22, 79, 21), 101).printValue()
    arithmeticExpressions(arrayOf(55, 3, 45, 33, 25), 101).printValue()
    arithmeticExpressions(arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1), 101).printValue()
    arithmeticExpressions(Array(3) { 100 }, 101).printValue()
    arithmeticExpressions(Array(4) { 100 }, 101).printValue()
    arithmeticExpressions(Array(10) { 100 }, 101).printValue()
    arithmeticExpressions(Array(100) { 100 }, 101).printValue()
}

private fun arithmeticExpressions(arr: Array<Int>, divisor: Int): String {
    val operators = arrayOf('*', '+', '-')
    val combinations = arrayListOf<String>()
    arithmeticExpressions(arr, divisor, operators, 1, arr[0], arr[0].toString(), combinations)
    return combinations.first()
}

private fun arithmeticExpressions(array: Array<Int>, divisor: Int, operators: Array<Char>, index: Int, currentSum: Int, result: String, allCombinations: ArrayList<String>): Boolean {
    if (currentSum % divisor == 0) {
        var tempResult = result
        val arrayLength = array.size
        if (index < arrayLength) {
            var arrIndex = index
            while (arrIndex < arrayLength) {
                tempResult += '*' + array[arrIndex].toString()
                arrIndex += 1
            }
        }
        allCombinations.add(tempResult)
        return true
    }
    if (index >= array.size) {
        return false
    }
    val currentValue = array[index]
    var sum = currentSum
    var midResult = result
    operators.forEach {
        sum = getResult(sum, currentValue, it, divisor)
        midResult = midResult + it + currentValue.toString()
        if(arithmeticExpressions(array, divisor, operators, index + 1, sum, midResult, allCombinations)) {
            return true
        }
        sum = currentSum
        midResult = result
    }
    return false
}

private fun getResult(sum: Int, value: Int, operator: Char, divisor: Int): Int {
    return when(operator) {
        '+' -> (sum % divisor + value % divisor) % divisor
        '-' -> (sum % divisor - value % divisor) % divisor
        else -> ((sum % divisor) * (value % divisor)) % divisor
    }
}

private fun moduloAddition(a: Int, b: Int, modulo: Int): Int {
    return (a % modulo) + (b % modulo)
}

private fun moduloSubtract(a: Int, b: Int, modulo: Int): Int {
    return (a % modulo) + (b % modulo)
}