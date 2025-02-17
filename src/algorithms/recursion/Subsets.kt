package algorithms.recursion

fun main() {
//    println(findStringSubsets("abc", 0, "").contentToString())
//    println(findStringSubsets("abc", "abc", 0).contentToString())
    println(findStringSubsets("ababcb").contentToString())
    println(findStringSubsetDuplicates("ababcb").contentToString())
}

private fun findStringSubsets(string: String, index: Int, result: String): Array<String> {
    if (index == string.length) {
        return arrayOf(result)
    }
    val char = string[index]
    val included = findStringSubsets(string, index + 1, result + char)
    val notIncluded = findStringSubsets(string, index + 1, result)
    return included.plus(notIncluded)
}

private fun findStringSubsets(originalString: String, string: String, index: Int): Array<String> {
    if (index == originalString.length) {
        return arrayOf(string)
    }
    val char = originalString[index]
    val charIndex = string.lastIndexOf(char)
    val notIncluded = findStringSubsets(originalString, string.substring(0, charIndex).plus(string.substring(charIndex + 1)), index + 1)
    val included = findStringSubsets(originalString, string, index + 1)
    return notIncluded + included
}

private fun findStringSubsets(string: String): Array<String> {
    var array = arrayOf<String>()
    var index = 0
    while (index < string.length) {
        val char = string[index]
        if (array.isEmpty()) {
            array = array.plus("").plus(char.toString())
        } else {
            var newArr = arrayOf<String>()
            array.forEach {
                newArr = newArr.plus(it + char)
            }
            array = array.plus(newArr)
        }
        index++
    }
    return array
}

private fun findStringSubsetDuplicates(s: String): Array<String> {
    val string = s.toCharArray().sorted().joinToString("")
    var array = arrayOf<String>()
    var index = 0
    var previousChar = ' '
    while (index < string.length) {
        val currentChar = string[index]
        if (array.isEmpty()) {
            array = array.plus("").plus(currentChar.toString())
        } else {
            var newArr = arrayOf<String>()
            if (previousChar != currentChar) {
                array.forEach {
                    newArr = newArr.plus(it + currentChar)
                }
            } else {
                val mid = array.size / 2
                array.copyOfRange(mid, array.size).forEach {
                    newArr = newArr.plus(it + currentChar)
                }
            }
            array = array.plus(newArr)
        }
        previousChar = currentChar
        index++
    }
    return array
}