package algorithms.recursion

import utils.printValue
import utils.printMatrix

// https://www.hackerrank.com/challenges/crossword-puzzle?isFullScreen=true

fun main() {
    fillCrossWord(
        arrayOf(
            arrayOf('+', '-', '-', '-', '-', '-', '-', '+', '+', '+'),
            arrayOf('+', '+', '+', '-', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '+', '+', '-', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '+', '+', '-', '-', '-', '-', '-', '+', '+'),
            arrayOf('+', '+', '+', '-', '+', '+', '-', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '-', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '-', '+', '+', '+'),
            arrayOf('+', '+', '-', '-', '-', '-', '-', '-', '+', '+'),
        ), arrayOf("NORWAY", "POLAND", "LHASA", "SPAIN", "INDIA")
    ).forEach {
        it.printMatrix()
    }
    println()
    fillCrossWord(
        arrayOf(
            arrayOf('+', '-', '+', '+', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '-', '-', '-', '-', '-', '+', '+', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '-', '+', '+', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '-', '+', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '-', '+', '+', '+', '+'),
            arrayOf('+', '+', '-', '-', '-', '-', '-', '-', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '-', '+', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '-', '+', '+', '+', '+'),
        ), arrayOf("LONDON", "DELHI", "ICELAND", "ANKARA")
    ).forEach {
        it.printMatrix()
    }
    println()
    fillCrossWord(
        arrayOf(
            arrayOf('+', '-', '+', '+', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '-', '-', '-', '-', '-', '-', '-', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '+', '+', '+', '+', '+'),
            arrayOf('+', '-', '-', '-', '-', '-', '-', '+', '+', '+'),
            arrayOf('+', '-', '+', '+', '+', '-', '+', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '-', '+', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '-', '+', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '+', '+', '+', '+'),
        ), arrayOf("AGRA", "NORWAY", "ENGLAND", "GWALIOR")
    ).forEach {
        it.printMatrix()
    }
    println()
    fillCrossWord(
        arrayOf(
            arrayOf('+', '+', '+', '+', '+', '+', '-', '+', '+', '+'),
            arrayOf('+', '+', '-', '-', '-', '-', '-', '-', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '-', '+', '+', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '-', '+', '+', '+'),
            arrayOf('+', '+', '+', '-', '-', '-', '-', '-', '-', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '-', '+', '-', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '-', '+', '-', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '+', '+', '-', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '+', '+', '-', '+'),
            arrayOf('+', '+', '+', '+', '+', '+', '+', '+', '-', '+'),
        ), arrayOf("ICELAND", "MEXICO", "PANAMA", "ALMATY")
    ).forEach {
        it.printMatrix()
    }
    println()
    fillCrossWord(
        arrayOf(
            "+-++++++++",
            "+-++-+++++",
            "+-------++",
            "+-++-++-++",
            "+-++-++-++",
            "+-++-++-++",
            "++++-++-++",
            "+--------+",
            "++++++++++",
            "----------"
        ), "CALIFORNIA;LASVEGAS;NIGERIA;CANADA;TELAVIV;ALASKA"
    ).forEach {
        it.printValue()
    }
}

private fun fillCrossWord(crossWord: Array<Array<Char>>, words: Array<String>): ArrayList<Array<Array<Char>>> {
    val allCombinations = arrayListOf<Array<Array<Char>>>()
    val result: Array<Array<Char>> = Array(crossWord.size) { row ->
        Array(crossWord[0].size) { column ->
            crossWord[row][column]
        }
    }
    val wordMap = mutableMapOf<Int, ArrayList<String>>()
    val placedMap: MutableMap<String, Boolean> = mutableMapOf()
    words.forEach {
        val size = it.length
        var list = wordMap[size]
        if (list == null) {
            list = arrayListOf()
        }
        list.add(it)
        wordMap[size] = list
        placedMap[it] = false
    }
    traverseCrossWord(
        crossWord,
        0,
        0,
        wordMap,
        placedMap,
        result,
        allCombinations
    )
    return allCombinations
}

private fun fillCrossWord(array: Array<String>, wordString: String): Array<String> {
    val allCombinations = arrayListOf<Array<Array<Char>>>()
    val crossWord: Array<Array<Char>> = Array(array.size) { row ->
        Array(array[row].length) { column ->
            array[row][column]
        }
    }
    val result: Array<Array<Char>> = Array(array.size) { row ->
        Array(array[row].length) { column ->
            array[row][column]
        }
    }
    val wordMap = mutableMapOf<Int, ArrayList<String>>()
    val placedMap: MutableMap<String, Boolean> = mutableMapOf()
    val words = wordString.split(";")
    words.forEach {
        val size = it.length
        var list = wordMap[size]
        if (list == null) {
            list = arrayListOf()
        }
        list.add(it)
        wordMap[size] = list
        placedMap[it] = false
    }
    traverseCrossWord(
        crossWord,
        0,
        0,
        wordMap,
        placedMap,
        result,
        allCombinations
    )
    var finalResult = arrayOf<String>()
    if (allCombinations.isNotEmpty()) {
        val combination = allCombinations.first()
        combination.forEach {
            var finalString = ""
            it.forEach { char ->
                finalString += char
            }
            finalResult = finalResult.plus(finalString)
        }
    }
    return finalResult
}

private fun traverseCrossWord(
    crossWord: Array<Array<Char>>,
    currentRow: Int,
    currentColumn: Int,
    wordMap: MutableMap<Int, ArrayList<String>>,
    placedMap: MutableMap<String, Boolean>,
    result: Array<Array<Char>>,
    allCombinations: ArrayList<Array<Array<Char>>>
) {
    var allWordsPlaced = true
    placedMap.forEach { (_, u) -> allWordsPlaced = allWordsPlaced && u }
    if (allWordsPlaced) {
        val newResult = Array(result.size) { row ->
            Array(result[0].size) { column ->
                result[row][column]
            }
        }
        allCombinations.add(newResult)
        return
    }
    if (currentRow == crossWord.size) {
        return
    }
    if (currentColumn == crossWord[0].size) {
        return traverseCrossWord(crossWord, currentRow + 1, 0, wordMap, placedMap, result, allCombinations)
    }
    val currentChar = result[currentRow][currentColumn]
    if (currentChar != '+') {
        val rightMaxLength = findMaxHorizontalLength(crossWord, result, currentRow, currentColumn)
        if (rightMaxLength > 1) {
            val possibleWords = findPossibleWords(wordMap, placedMap, rightMaxLength)
            possibleWords.forEach { word ->
                val wordPlaced = placeWordHorizontally(
                    crossWord,
                    currentRow,
                    currentColumn,
                    word,
                    placedMap,
                    result
                )
                if (wordPlaced) {
                    traverseCrossWord(
                        crossWord,
                        currentRow,
                        currentColumn + 1,
                        wordMap,
                        placedMap,
                        result,
                        allCombinations
                    )
                    removeWordHorizontally(
                        crossWord,
                        currentRow,
                        currentColumn,
                        word,
                        placedMap,
                        result
                    )
                }
            }
        }
        val downMaxLength = findMaxVerticalLength(crossWord, result, currentRow, currentColumn)
        if (downMaxLength > 1) {
            val possibleWords = findPossibleWords(wordMap, placedMap, downMaxLength)
            possibleWords.forEach { word ->
                val wordPlaced = placeWordVertically(
                    crossWord,
                    currentRow,
                    currentColumn,
                    word,
                    placedMap,
                    result
                )
                if (wordPlaced) {
                    traverseCrossWord(
                        crossWord,
                        currentRow,
                        currentColumn + 1,
                        wordMap,
                        placedMap,
                        result,
                        allCombinations
                    )
                    removeWordVertically(
                        crossWord,
                        currentRow,
                        currentColumn,
                        word,
                        placedMap,
                        result
                    )
                }
            }
        }
    }
    return traverseCrossWord(
        crossWord,
        currentRow,
        currentColumn + 1,
        wordMap,
        placedMap,
        result,
        allCombinations
    )
}

private fun findPossibleWords(
    wordMap: MutableMap<Int, ArrayList<String>>,
    placedMap: MutableMap<String, Boolean>,
    wordSize: Int
): List<String> {
    val list = wordMap[wordSize]
    if (list.isNullOrEmpty()) {
        return arrayListOf()
    }
    return list.filter { placedMap[it] != null && !placedMap[it]!! }
}

private fun removeWordHorizontally(
    crossWord: Array<Array<Char>>,
    currentRow: Int,
    currentColumn: Int,
    word: String,
    placedMap: MutableMap<String, Boolean>,
    result: Array<Array<Char>>,
) {
    var index = 0
    var column = currentColumn
    abc@ while (column in result[0].indices) {
        result[currentRow][column] = crossWord[currentRow][column]
        index += 1
        column += 1
    }
    placedMap[word] = false
}

private fun removeWordVertically(
    crossWord: Array<Array<Char>>,
    currentRow: Int,
    currentColumn: Int,
    word: String,
    placedMap: MutableMap<String, Boolean>,
    result: Array<Array<Char>>,
) {
    var index = 0
    var row = currentRow
    abc@ while (row in result.indices) {
        result[row][currentColumn] = crossWord[row][currentColumn]
        index += 1
        row += 1
    }
    placedMap[word] = false
}

private fun placeWordHorizontally(
    crossWord: Array<Array<Char>>,
    currentRow: Int,
    currentColumn: Int,
    word: String,
    placedMap: MutableMap<String, Boolean>,
    result: Array<Array<Char>>,
): Boolean {
    var isWordPlaced = true
    var index = 0
    var column = currentColumn
    abc@ while (column in result[0].indices && index < word.length) {
        val charInCrossWord = result[currentRow][column]
        val charInString = word[index]
        if (charInCrossWord == '-' || charInCrossWord == charInString) {
            result[currentRow][column] = word[index]
            index += 1
            column += 1
        } else {
            isWordPlaced = false
            break@abc
        }
    }
    if (isWordPlaced) {
        placedMap[word] = true
    } else {
        placedMap[word] = false
        index -= 1
        column -= 1
        while (column in result[0].indices && index >= 0) {
            result[currentRow][column] = crossWord[currentRow][column]
            column -= 1
            index -= 1
        }
    }
    return isWordPlaced
}

private fun placeWordVertically(
    crossWord: Array<Array<Char>>,
    currentRow: Int,
    currentColumn: Int,
    word: String,
    placedMap: MutableMap<String, Boolean>,
    result: Array<Array<Char>>
): Boolean {
    var isWordPlaced = true
    var index = 0
    var row = currentRow
    abc@ while (row in result.indices && index < word.length) {
        val charInCrossWord = result[row][currentColumn]
        val charInString = word[index]
        if (charInCrossWord == '-' || charInCrossWord == charInString) {
            result[row][currentColumn] = word[index]
            index += 1
            row += 1
        } else {
            isWordPlaced = false
            break@abc
        }
    }
    if (isWordPlaced) {
        placedMap[word] = true
    } else {
        placedMap[word] = false
        index -= 1
        row -= 1
        while (row in result.indices && index >= 0) {
            result[row][currentColumn] = crossWord[row][currentColumn]
            row -= 1
            index -= 1
        }
    }
    return isWordPlaced
}

private fun findMaxHorizontalLength(
    crossWord: Array<Array<Char>>,
    result: Array<Array<Char>>,
    currentRow: Int,
    currentColumn: Int,
): Int {
    var length = 1
    var column = currentColumn + 1
    var emptySpaceFound = false
    while (currentRow in crossWord.indices && column in crossWord[0].indices && result[currentRow][column] != '+') {
        emptySpaceFound = emptySpaceFound || result[currentRow][column] == '-'
        column++
        length += 1
    }
    return if(emptySpaceFound) length else 1
}

private fun findMaxVerticalLength(
    crossWord: Array<Array<Char>>,
    result: Array<Array<Char>>,
    currentRow: Int,
    currentColumn: Int,
): Int {
    var length = 1
    var row = currentRow + 1
    var emptySpaceFound = false
    while (row in crossWord.indices && currentColumn in crossWord[0].indices && result[row][currentColumn] != '+') {
        emptySpaceFound = emptySpaceFound || result[row][currentColumn] == '-'
        row++
        length += 1
    }
    return if(emptySpaceFound) length else 1
}