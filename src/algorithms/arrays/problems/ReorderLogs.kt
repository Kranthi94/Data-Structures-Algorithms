package algorithms.arrays.problems

import utils.printArray

// https://leetcode.com/problems/reorder-data-in-log-files/description/

fun main() {
    val inputs = arrayOf(
        arrayOf("dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"),
        arrayOf("a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"),
        arrayOf("1 n u", "r 527", "j 893", "6 14", "6 82"),
        arrayOf("r 527", "j 893", "6 14", "6 82")
    )
    inputs.forEach {
        reorderLogFiles(it).printArray()
    }
}

class Log(
    val identifier: String,
    val value: String
) : Comparable<Log> {
    override fun compareTo(other: Log): Int {
        val leftIdentifierIsString = other.identifier.trim()[0] - 'a' in 0..25
        val rightIdentifierIsString = identifier.trim()[0] - 'a' in 0..25
        val leftIsString = other.value.trim()[0] - 'a' in 0..25
        val rightIsString = value.trim()[0] - 'a' in 0..25
        when {
            leftIsString && rightIsString -> {
                val compare = value.compareTo(other.value)
                if (compare != 0) {
                    return compare
                }
                return when {
                    leftIdentifierIsString && rightIdentifierIsString -> {
                        identifier.compareTo(other.identifier)
                    }

                    leftIdentifierIsString -> {
                        1
                    }

                    rightIdentifierIsString -> {
                        -1
                    }

                    else -> {
                        1
                    }
                }
            }

            leftIsString -> {
                return 1
            }

            rightIsString -> {
                return -1
            }

            else -> {
                return 1
            }
        }
    }
}

private fun reorderLogFiles(logs: Array<String>): Array<String> {
    if (logs.isEmpty()) {
        return logs
    }
    var arrayOfLogs: Array<Log> = arrayOf()
    logs.forEach { log ->
        val firstSpaceIndex = log.indexOfFirst { it == ' ' }
        arrayOfLogs =
            arrayOfLogs.plus(Log(log.substring(0, firstSpaceIndex), log.substring(firstSpaceIndex + 1, log.length)))
    }
    arrayOfLogs.sort()
    var finalResult: Array<String> = arrayOf()
    arrayOfLogs.forEach { sortedLog ->
        finalResult = finalResult.plus(sortedLog.identifier.plus(" ").plus(sortedLog.value))
    }
    return finalResult
}