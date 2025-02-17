package algorithms.graphs.traversals

import datastructures.queue.Queue
import utils.printList
import utils.printValue

// https://leetcode.com/problems/word-ladder/description/

fun main() {
    findSequence(arrayOf("hot", "dot", "dog", "lot", "log", "cog"), "hit", "cog").printValue()
    findSequence(arrayOf("dot", "dog", "lot", "log", "cog"), "hit", "cog").printValue()
    findSequence(arrayOf("hot", "dot", "dog", "lot", "log"), "hit", "cog").printValue()
    findSequence(arrayOf("hot", "dot", "lot", "cog"), "hit", "cog").printValue()
}

private fun findSequence(wordsList: Array<String>, startWord: String, endWord: String): Int {
    if (!wordsList.contains(endWord)) {
        return 0
    }
    val queue: Queue<Triple<String, Int, Boolean>> = Queue()
    queue.enQueue(Triple(startWord, 1, true))
    val parentMap: MutableMap<String, String> = mutableMapOf()
    parentMap[startWord] = ""
    var isPathFound = false
    var pathLength = 0
    val levelNodesList: MutableList<String> = mutableListOf()
    val visitedSet: MutableSet<String> = mutableSetOf(startWord)
    while (!queue.isEmpty()) {
        val poppedString = queue.deQueue()
        val nextWordsTriple = findNextWords(wordsList, endWord, poppedString, visitedSet, parentMap)
        val nextWords = nextWordsTriple.first
        val containsEndWord = nextWordsTriple.second
        if (nextWords.isEmpty()) {
            break
        }
        if (containsEndWord) {
            pathLength = nextWords[0].second
            isPathFound = true
            break
        }
        nextWords.forEach {
            levelNodesList.add(it.first)
            queue.enQueue(it)
        }
        val isLastLevelNode = poppedString.third
        if (isLastLevelNode) {
            visitedSet.addAll(levelNodesList)
            levelNodesList.clear()
        }
    }
    if (isPathFound) {
        var currentString = endWord
        val path: MutableList<String> = mutableListOf()
        while (parentMap[currentString] != "") {
            path.add(0, currentString)
            currentString = parentMap[currentString]!!
        }
        path.add(0, startWord)
        path.printList()
    }
    return pathLength
}

private fun findNextWords(
    wordsList: Array<String>,
    endWord: String,
    currentWord: Triple<String, Int, Boolean>,
    visitedSet: Set<String>,
    parentMap: MutableMap<String, String>
): Pair<List<Triple<String, Int, Boolean>>, Boolean> {
    val result: MutableList<Triple<String, Int, Boolean>> = mutableListOf()
    var containsEndWord = false
    wordsList.forEach { word ->
        if (visitedSet.contains(word)) {
            return@forEach
        }
        var diff = 0
        var index = 0
        while (diff <= 1 && index in currentWord.first.indices) {
            if (word[index] != currentWord.first[index]) {
                diff += 1
            }
            index += 1
        }
        if (diff == 1) {
            containsEndWord = containsEndWord || word == endWord
            result.add(Triple(word, currentWord.second + 1, false))
            parentMap[word] = currentWord.first
        }
    }
    if (result.isNotEmpty()) {
        val last = result[result.size - 1]
        result[result.size - 1] = Triple(last.first, last.second, true)
    }
    return Pair(result, containsEndWord)
}