package algorithms.recursion

import utils.printList

fun main() {
    findPalindromicPartitions("eaninax").printList()
    findPalindromicPartitions("bababcbadcede").printList()
}

private fun findPalindromicPartitions(string: String, startIndex: Int = 0, endIndex: Int = string.length - 1): List<String> {
    if (startIndex == endIndex) {
        return arrayListOf(string[startIndex].toString())
    }
    val list = arrayListOf<String>()
    val startChar = string[startIndex]
    val sameCharLastIndex1 = checkForSameLetter(string, startIndex, endIndex, startChar)
    if (sameCharLastIndex1 in (startIndex + 1)..endIndex) {
        val isPalindrome = checkPalindrome(string.substring(startIndex + 1, sameCharLastIndex1))
        if (isPalindrome) {
            list.add(string.substring(startIndex, sameCharLastIndex1 + 1))
            list.addAll(findPalindromicPartitions(string, sameCharLastIndex1 + 1, endIndex))
        } else {
            list.addAll(findPalindromicPartitions(string, startIndex, sameCharLastIndex1 - 1))
            list.addAll(findPalindromicPartitions(string, sameCharLastIndex1, endIndex))
        }
    } else {
        list.add(startChar.toString())
        list.addAll(findPalindromicPartitions(string, startIndex + 1, endIndex))
    }
    return list
}

private fun checkForSameLetter(string: String, startIndex: Int, endIndex: Int, char: Char): Int {
    return string.substring(startIndex, endIndex + 1).lastIndexOf(char) + startIndex
}

private fun checkPalindrome(string: String): Boolean {
    return string.reversed() == string
}

//private fun palindromicPartitions(string: String): String {
//    val array = arrayOf<String>()
//    val palindrome = findPalindrome(string)
//    val startIndex = string.indexOf(palindrome)
//    val endIndex = startIndex + palindrome.length - 1
//    var leftPart = string.substring(0, startIndex).replace("", " ").trim()
//    val rightPart = string.substring(endIndex + 1).replace("", " ").trim()
//    var result = palindrome.replace("", " ").trim()
//    return leftPart + " " + " " + rightPart
//}
//
//private  fun findPalindrome(string: String, startIndex: Int = 0, endIndex: Int = string.length - 1): String {
//    if (startIndex == endIndex) {
//        return string[startIndex].toString()
//    }
//    if (startIndex + 1 == endIndex && string[startIndex] == string[endIndex]) {
//        return string[startIndex].toString()
//    }
//    val char = string[startIndex]
//    val sameCharIndex = checkForSameLetter(string, startIndex, endIndex, char)
//    if (sameCharIndex in (startIndex + 1)..endIndex) {
//        val result = findPalindrome(string, startIndex + 1, sameCharIndex - 1)
//        if (result.isNotEmpty()) {
//            return char + result + char
//        }
//    }
//    findPalindrome(string, startIndex + 1, endIndex)
//    return ""
//}



