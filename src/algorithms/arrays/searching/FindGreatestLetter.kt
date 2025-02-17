package algorithms.arrays.searching

// https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/

fun main() {
    val array = CharArray(3)
    array[0] = 'c'
    array[1] = 'f'
    array[2] = 'j'
    println("Greatest Letter after a in the array is ${array[findGreatestLetter(array, 'a')]}")
    println("Greatest Letter after c in the array is ${array[findGreatestLetter(array, 'c')]}")
    println("Greatest Letter after z in the array is ${array[findGreatestLetter(array, 'z')]}")
    println("Greatest Letter after f in the array is ${array[findGreatestLetter(array, 'f')]}")
    println("Greatest Letter after j in the array is ${array[findGreatestLetter(array, 'j')]}")
}

private fun findGreatestLetter(array: CharArray, target: Char): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    if (target < array[startIndex] || target > array[endIndex]) {
        return 0
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        if (midValue == target) {
            startIndex = midIndex + 1
            break
        }
        if (target < midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return startIndex % array.size
}