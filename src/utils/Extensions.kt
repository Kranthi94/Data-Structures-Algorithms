package utils

import datastructures.linkedlists.CircularLinkedList
import datastructures.linkedlists.DoubleLinkedList
import datastructures.linkedlists.SingleLinkedList
import datastructures.trees.TreeNode

fun <T> T.printValue() {
    println(this.toString())
}

fun <T> T.compareTo(value: T): Int {
    return when {
        this is String && value is String -> this.compareTo(value)
        this is Int && value is Int -> this.compareTo(value)
        else -> 0
    }
}

fun <T> TreeNode<T>?.printPair(): String {
    if (this == null) {
        println("null")
        return "null"
    }
    println(this.value)
    return this.value.toString()
}

fun <T> Array<T>.getElement(index: Int): T? {
    if (index < 0 || index >= this.size) {
        return null
    }
    return this[index]
}

fun IntArray.swap(firstIndex: Int, secondIndex: Int) {
    val temp = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = temp
}

fun Array<Int>.swap(firstIndex: Int, secondIndex: Int) {
    val temp = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = temp
}

fun <T> ArrayList<T>.swap(firstIndex: Int, secondIndex: Int) {
    if (firstIndex == secondIndex) {
        return
    }
    val temp = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = temp
}

fun IntArray.printArray(): String {
    val stringBuilder = StringBuilder("[ ")
    for (i in this.indices) {
        stringBuilder.append(this[i])
        stringBuilder.append(" ")
    }
    stringBuilder.append("]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun CharArray.printArray(): String {
    val stringBuilder = StringBuilder("[ ")
    for (i in this.indices) {
        stringBuilder.append(this[i])
        stringBuilder.append(" ")
    }
    stringBuilder.append("]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun DoubleArray.printArray(): String {
    val stringBuilder = StringBuilder("[ ")
    for (i in this.indices) {
        stringBuilder.append(this[i])
        stringBuilder.append(" ")
    }
    stringBuilder.append("]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun <T> List<T>.printList(): String {
    val stringBuilder = StringBuilder("[ ")
    for (i in this.indices) {
        stringBuilder.append(this[i].toString())
        stringBuilder.append(" ")
    }
    stringBuilder.append("]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun <T> Set<T>.printSet(): String {
    val stringBuilder = StringBuilder("[ ")
    for (i in this.indices) {
        stringBuilder.append(this.elementAt(i).toString())
        stringBuilder.append(" ")
    }
    stringBuilder.append("]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun <K, V> Map<K, V>.printMap(): String {
    val stringBuilder = StringBuilder("")
    this.forEach { (key, value) ->
        stringBuilder.append("$key --------> $value")
        stringBuilder.appendLine()
    }
    stringBuilder.append("")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun <T> Array<T>.printArray(): String {
    val stringBuilder = StringBuilder("[ ")
    for (i in this.indices) {
        stringBuilder.append(this[i].toString())
        stringBuilder.append(" ")
    }
    stringBuilder.append("]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun <T> Array<Array<T>>.printMatrix() {
    for (i in this.indices) {
        val stringBuilder = StringBuilder("[ ")
        for (j in this[i].indices) {
            stringBuilder.append(this[i][j].toString())
            stringBuilder.append(" ")
        }
        stringBuilder.append("]")
        println(stringBuilder)
    }
}

fun <A, B> Pair<A, B>.printPair(): String {
    val stringBuilder = StringBuilder("[ ")
    stringBuilder.append(this.first.toString())
    stringBuilder.append(" <====> ")
    stringBuilder.append(this.second.toString())
    stringBuilder.append(" ]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun <A, B, C> Triple<A, B, C>.printTriple(): String {
    val stringBuilder = StringBuilder("[ ")
    stringBuilder.append(this.first.toString())
    stringBuilder.append(" <====> ")
    stringBuilder.append(this.second.toString())
    stringBuilder.append(" <====> ")
    stringBuilder.append(this.third.toString())
    stringBuilder.append(" ]")
    println(stringBuilder)
    return stringBuilder.toString()
}

fun getIntMatrix(rows: Int, columns: Int): Array<Array<Int>> {
    return Array(rows) {
        Array(columns) {
            -1
        }
    }
}

fun getStringListMatrix(rows: Int, columns: Int): Array<Array<List<String>>> {
    return Array(rows) {
        Array(columns) {
            arrayListOf()
        }
    }
}

fun <N> SingleLinkedList.Node<N>?.printLinkedList(): String {
    val stringBuilder = StringBuilder("[ ")
    var node = this
    while (node != null) {
        stringBuilder.append(node.value)
        stringBuilder.append(" ")
        node = node.nextNode
    }
    stringBuilder.append("]")
    println(stringBuilder.toString())
    return stringBuilder.toString()
}

fun <N> DoubleLinkedList.Node<N>?.printLinkedList(): String {
    val stringBuilder = StringBuilder("[ ")
    var node = this
    while (node != null) {
        stringBuilder.append(node.value)
        stringBuilder.append(" ")
        node = node.nextNode
    }
    stringBuilder.append("]")
    println(stringBuilder.toString())
    return stringBuilder.toString()
}

fun <N> CircularLinkedList.Node<N>?.printLinkedList(): String {
    val stringBuilder = StringBuilder("[ ")
    var node = this
    while (node != null) {
        stringBuilder.append(node.value)
        stringBuilder.append(" ")
        node = node.nextNode
    }
    stringBuilder.append("]")
    println(stringBuilder.toString())
    return stringBuilder.toString()
}