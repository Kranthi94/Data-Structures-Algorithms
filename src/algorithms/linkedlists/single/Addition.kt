package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/

fun main() {
    val list1 = SingleLinkedList<Int>()
    list1.insertAtStart(3)
    list1.insertAtEnd(4)
    list1.insertAtEnd(5)
    val list2 = SingleLinkedList<Int>()
    list2.insertAtStart(4)
    list2.insertAtStart(7)
    list2.insertAtEnd(5)
    println("List-1")
    list1.print()
    println("List-2")
    list2.print()
    println("List after Addition")
    addNumbers(list1.getHeadNode(), list2.getHeadNode()).printLinkedList()
}

private fun addNumbers(
    firstHead: SingleLinkedList.Node<Int>?,
    secondHead: SingleLinkedList.Node<Int>?
): SingleLinkedList.Node<Int>? {
    if (firstHead == null) {
        return secondHead
    }
    if (secondHead == null) {
        return firstHead
    }
    val firstLength = findLength(firstHead)
    val secondLength = findLength(secondHead)
    val result = addNumbersRecursively(
        firstHead,
        firstLength,
        secondHead,
        secondLength
    )
    val headNode: SingleLinkedList.Node<Int>?
    if (result.first > 0) {
        headNode = SingleLinkedList.Node(result.first)
        headNode.nextNode = result.second
    } else {
        headNode = result.second
    }
    return headNode
}

private fun addNumbersRecursively(
    firstHead: SingleLinkedList.Node<Int>?,
    firstIndex: Int,
    secondHead: SingleLinkedList.Node<Int>?,
    secondIndex: Int
): Pair<Int, SingleLinkedList.Node<Int>> {
    if (firstHead != null && firstHead.nextNode == null && secondHead != null && secondHead.nextNode == null) {
        val sum = firstHead.value + secondHead.value
        val reminder = if(sum > 9) sum / 10 else 0
        val nodeValue = if(sum > 9) sum % 10 else sum
        return Pair(reminder, SingleLinkedList.Node(nodeValue))
    }
    val firstValue: Int
    val secondValue: Int
    var index1 = firstIndex
    var index2 = secondIndex
    var node1 = firstHead
    var node2 = secondHead
    if (index1 > index2) {
        firstValue = firstHead!!.value
        secondValue = 0
        node1 = node1.nextNode
        index1 -= 1
    } else if (index1 < index2) {
        firstValue = 0
        secondValue = secondHead!!.value
        node2 = node2.nextNode
        index2 -= 1
    } else {
        firstValue = firstHead!!.value
        secondValue = secondHead!!.value
        node1 = node1.nextNode
        node2 = node2.nextNode
        index1 -= 1
        index2 -= 1
    }
    val sum = firstValue + secondValue
    val result = addNumbersRecursively(node1, index1, node2, index2)
    val totalSum = sum + result.first
    val reminder = if(totalSum > 9) totalSum / 10 else 0
    val nodeValue = if(totalSum > 9) totalSum % 10 else totalSum
    val currentNode = SingleLinkedList.Node(nodeValue)
    currentNode.nextNode = result.second
    return Pair(reminder, currentNode)
}

private fun findLength(headNode: SingleLinkedList.Node<Int>?): Int {
    if (headNode == null) {
        return 0
    }
    if (headNode.nextNode == null) {
        return 1
    }
    var tailNode = headNode
    var length = 0
    while (tailNode != null) {
        length += 1
        tailNode = tailNode.nextNode
    }
    return length
}