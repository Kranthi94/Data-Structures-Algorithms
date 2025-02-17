package algorithms.recursion

fun main() {
    println(findPermutations("abc").contentToString())
}

private fun findPermutations(string: String): Array<String> {
    if (string.length == 1) {
        return arrayOf(string)
    }
    var result = arrayOf<String>()
    for (i in string.indices)  {
        val char = string[i]
        val array = findPermutations(string.substring(0, i).plus(string.substring(i + 1)))
        var newArray = arrayOf<String>()
        array.forEach {
            newArray = newArray.plus(char + it)
        }
        result = result.plus(newArray)
    }
    return result
}