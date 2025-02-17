package algorithms.maths

// https://www.geeksforgeeks.org/nth-even-length-palindrome/

fun main() {
    val number = getNthEvenPalindromicNumber(98)
    val charArray = number.toCharArray()
    val hashMap = HashMap<Char, Int?>()
    for (i in charArray.indices) {
        hashMap[charArray[i]] = if (hashMap[charArray[i]] == null) 0 else hashMap[charArray[i]]!! + 1
    }
    var mapping = 0
    var c = ' '
    for (character in hashMap.keys) {
        val value = hashMap[character]!!
        if (value > mapping) {
            mapping = value
            c = character
        } else if (value == mapping && character < c) {
            mapping = value
            c = character
        }
    }
    println(c)
}

private fun getNthEvenPalindromicNumber(n: Long): String {
    val stringBuilder = StringBuilder(n.toString())
    stringBuilder.append(stringBuilder.reversed())
    return stringBuilder.toString()
}