package algorithms.recursion

fun main() {
    println(skipCharacter1("bcabajjadahewb", 'a', ""))
    println(skipCharacter1("bcabajjadahewb", 'b', ""))

    println()

    println(skipCharacter2("bcabajjadahewb", 'a'))
    println(skipCharacter2("bcabajjadahewb", 'b'))
}

private fun skipCharacter1(string: String, charToAvoid: Char, ans: String): String {
    if (string.isEmpty()) {
        return ans
    }
    val char = string[0]
    if (char != charToAvoid) {
        return skipCharacter1(string.substring(1), charToAvoid, ans + char)
    }
    return skipCharacter1(string.substring(1), charToAvoid, ans)
}

private fun skipCharacter2(string: String, charToAvoid: Char): String {
    if (string.isEmpty()) {
        return ""
    }
    val char = string[0]
    if (char != charToAvoid) {
        return char + skipCharacter2(string.substring(1), charToAvoid)
    }
    return skipCharacter2(string.substring(1), charToAvoid)
}