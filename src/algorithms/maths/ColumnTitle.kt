package algorithms.maths

// https://leetcode.com/problems/excel-sheet-column-title/description/

fun main() {
    println(getColumnTitle(28))
    println(getColumnTitle(52))
    println(getColumnTitle(701))
}

private fun getColumnTitle(num: Int): String {
    val list = arrayListOf<Char>()
    var n = num
    while(n > 0) {
        val reminder = n % 26
        if (reminder == 0) {
            list.add('Z')
            n = (n - 1) / 26
        } else {
            list.add('A' + reminder - 1)
            n /= 26
        }
    }
    val stringBuilder = StringBuilder()
    for (i in list.size - 1 downTo 0) {
        stringBuilder.append(list[i])
    }
    return stringBuilder.toString()
}