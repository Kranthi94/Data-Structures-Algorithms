package basics

import utils.printValue

/**
 * Strings are immutable in java and created inside string pool
 * Whenever a string is changed it creates a new copy of string and original string
 * remains unchanged in the pool
 * String constructors can be used to created string objects in the heap memory.
 * == operator checks for value alone
 * === operator checks for value and reference both
 */
fun main() {
    val string1 = "abc"
    val string2 = String(charArrayOf('a', 'b', 'c'))

    valueCheck(string1, string2).printValue()
    valueAndReferenceCheck(string1, string2).printValue()
}

private fun valueCheck(string1: String, string2: String): Boolean = string1 == string2

private fun valueAndReferenceCheck(string1: String, string2: String): Boolean = string1 === string2