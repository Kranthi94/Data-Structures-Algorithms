package algorithms.arrays.rotation

import utils.printArray

fun main() {
    val inputs = arrayOf(
        arrayOf(1, 2, 3, 4, 5, 6, 7),
        arrayOf("a", "b", "c", "d", "e")
    )
    inputs.forEach {
        rotateArray(it, 3).printArray()
    }
}

private fun <T> rotateArray(array: Array<T>, rotations: Int): Array<T> {
    val finalRotations = rotations % array.size
    array.reverse(0, array.size)
    array.reverse(0, finalRotations)
    array.reverse(finalRotations, array.size)
    return array
}