package algorithms.arrays.insertion

import utils.printArray

fun main() {
    val inputs = arrayOf(
        arrayOf(1, 4, 5, 8, 9, 11, 16)
    )
    inputs.forEach { array ->
        repeat(10) {
            binaryInsertion(array, (array[0] - 10..array[array.size - 1] + 10).random()).printArray()
        }
    }
}

private fun binaryInsertion(numbers: Array<Int>, target: Int): Array<Int> {
    var result: Array<Int> = arrayOf()
    if (target <= numbers[0]) {
        result = result.plus(target).plus(numbers)
    } else if (target >= numbers[numbers.size - 1]) {
        result = result.plus(numbers).plus(target)
    } else {
        var startIndex = 0
        var endIndex = numbers.size - 1
        var floorIndex = -1
        while (startIndex <= endIndex) {
            val midIndex = startIndex + (endIndex - startIndex) / 2
            val midValue = numbers[midIndex]
            if (target == midValue) {
                floorIndex = midIndex
                break
            }
            if (target < midValue) {
                endIndex = midIndex - 1
            } else {
                startIndex = midIndex + 1
            }
        }
        if (floorIndex == - 1) {
            floorIndex = endIndex
        }
        result = result.plus(numbers.copyOfRange(0, floorIndex + 1)).plus(target).plus(numbers.copyOfRange(floorIndex + 1, numbers.size))
    }
    return result
}