package basics

import utils.printArray
import utils.printValue

fun main() {
    val num = 10
    numberExample(num)
    num.printValue()

    val string = "abc"
    stringExample(string)
    string.printValue()

    val array = arrayOf(1, 2, 3, 4)
    objectExample(array)
    array.printArray()
}

/**
 * When passing primitive values those are passed using pass by value
 * Throws error saying val cannot be reassigned
 */
private fun numberExample(num: Int) {
    var local = num
    local += 5
    local.printValue()
}

/**
 * When passing primitive values those are passed using pass by value
 * Throws error saying val cannot be reassigned
 */
private fun stringExample(string: String) {
    var local = string
    local += 'q'
    local.printValue()
}

/**
 * When passing objects those are passed using pass by reference so any changes
 * made inside the object are reflected in the next sequence of steps.
 */
private fun objectExample(array: Array<Int>) {
    array[0] = 5
    array.printArray()
}