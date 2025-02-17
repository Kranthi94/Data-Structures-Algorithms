package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://leetcode.com/problems/reorder-list/description/

fun main() {
    val linkedList1 = SingleLinkedList<Int>()
    linkedList1.insertAtStart(1)
    linkedList1.insertAtEnd(2)
    linkedList1.insertAtEnd(3)
    linkedList1.insertAtEnd(4)
    linkedList1.insertAtEnd(5)
    reorderList1(linkedList1.getHeadNode()).printLinkedList()
}

private fun reorderList1(headNode: SingleLinkedList.Node<Int>?): SingleLinkedList.Node<Int>? {
    if (headNode?.nextNode == null) {
        return headNode
    }
    val middleNode = findMiddleNode(headNode)
    return mergeTwoLists(headNode, reverseLinkedList(middleNode))
}

private fun mergeTwoLists(
    headNode1: SingleLinkedList.Node<Int>?,
    headNode2: SingleLinkedList.Node<Int>?
): SingleLinkedList.Node<Int>? {
    if (headNode1 == null && headNode2 == null) {
        return null
    }
    if (headNode1 == null) {
        return headNode2
    }
    if (headNode2 == null) {
        return headNode1
    }
    headNode1.nextNode = mergeTwoLists(headNode2, headNode1.nextNode)
    return headNode1
}

private fun reverseLinkedList(reverseNode: SingleLinkedList.Node<Int>?): SingleLinkedList.Node<Int>? {
    if (reverseNode?.nextNode == null) {
        return null
    }
    var previousNode: SingleLinkedList.Node<Int>? = null
    var currentNode = reverseNode
    var nextNode = currentNode.nextNode
    while (currentNode != null) {
        currentNode.nextNode = previousNode
        previousNode = currentNode
        currentNode = nextNode
        nextNode = nextNode?.nextNode
    }
    return previousNode
}

private fun findMiddleNode(headNode: SingleLinkedList.Node<Int>?): SingleLinkedList.Node<Int>? {
    if (headNode == null) {
        return null
    }
    var slowPointerPrevious: SingleLinkedList.Node<Int>? = null
    var fastPointer = headNode
    while (fastPointer?.nextNode != null) {
        slowPointerPrevious = if (slowPointerPrevious == null) headNode else slowPointerPrevious.nextNode
        fastPointer = fastPointer.nextNode?.nextNode
    }
    val middleNode = slowPointerPrevious?.nextNode
    slowPointerPrevious?.nextNode = null
    return middleNode
}