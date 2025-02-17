package algorithms.arrays.searching

fun main() {
    val array = IntArray(14)
    array[0] = 2
    array[1] = 7
    array[2] = 13
    array[3] = 21
    array[4] = 30
    array[5] = 45
    array[6] = 59
    array[7] = 73
    array[8] = 87
    array[9] = 99
    array[10] = 116
    array[11] = 134
    array[12] = 147
    array[13] = 160
    println(findTargetInInfiniteArray(array, 45))
    println(findTargetInInfiniteArray(array, 1))
    println(findTargetInInfiniteArray(array, 116))
}

private fun findTargetInInfiniteArray(array: IntArray, target: Int): Int {
    var startIndex = 0
    var endIndex = 1
    while(target > array[endIndex]) {
        val temp = endIndex + 1
        endIndex += 2 * (endIndex - startIndex + 1)
        startIndex = temp
    }
    return binarySearch(array, target, startIndex, endIndex)
}

private fun binarySearch(array: IntArray, target: Int, start: Int, end: Int): Int {
    var startIndex = start
    var endIndex = end
    if (target < array[startIndex]) {
        return -1
    }
    while (startIndex <= endIndex) {
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val midValue = array[midIndex]
        if (target == midValue) {
            return midIndex
        }
        if (target < midValue) {
            endIndex = midIndex - 1
        } else {
            startIndex = midIndex + 1
        }
    }
    return -1
}