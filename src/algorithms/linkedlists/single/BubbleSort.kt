package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://leetcode.com/problems/sort-list/description/

fun main() {
    val linkedList = SingleLinkedList<Int>()
    linkedList.insertAtStart(14)
    linkedList.insertAtEnd(5)
    linkedList.insertAtEnd(9)
    linkedList.insertAtEnd(16)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(30)
    println("Before Sorting")
    linkedList.getHeadNode().printLinkedList()
    println("After Sorting")
    doRecursiveBubbleSort(linkedList.getHeadNode(), null).printLinkedList()
}

private fun doRecursiveBubbleSort(
    headNode: SingleLinkedList.Node<Int>?,
    tailNode: SingleLinkedList.Node<Int>?,
): SingleLinkedList.Node<Int>? {
    if (headNode?.nextNode == null) {
        return headNode
    }
    if (headNode == tailNode) {
        return headNode
    }
    var headNodeRef = headNode
    var previousNode: SingleLinkedList.Node<Int>? = null
    var firstNode = headNode
    var secondNode = firstNode.nextNode
    var swappingDone = false
    while (firstNode != null && secondNode != tailNode) {
        val firstValue = firstNode.value
        val secondValue = secondNode?.value!!
        if (firstValue > secondValue) {
            if (previousNode == null) {
                // Swap at Head Node
                headNodeRef = secondNode
                firstNode.nextNode = secondNode.nextNode
                secondNode.nextNode = firstNode
            } else if (secondNode.nextNode == null) {
                // Swap at Tail Node
                previousNode.nextNode = secondNode
                secondNode.nextNode = firstNode
                firstNode.nextNode = null
            } else {
                // Swap at Middle Node
                previousNode.nextNode = secondNode
                firstNode.nextNode = secondNode.nextNode
                secondNode.nextNode = firstNode
            }
            swappingDone = true
        }
        previousNode = if (previousNode == null) headNodeRef else previousNode.nextNode!!
        firstNode = previousNode?.nextNode
        secondNode = firstNode?.nextNode
    }
    if (!swappingDone) {
        return headNodeRef
    }
    return doRecursiveBubbleSort(headNodeRef, firstNode)
}