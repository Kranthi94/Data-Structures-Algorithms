package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://www.geeksforgeeks.org/swap-nodes-in-a-linked-list-without-swapping-data/
// https://leetcode.com/problems/swapping-nodes-in-a-linked-list/description/

/**
 * Edge Cases are important
 * K can be on the other side of mid
 * Take care when handling both the swap nodes are beside.
 */

fun main() {
    val linkedList = SingleLinkedList<Int>()
    linkedList.insertAtStart(1)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(3)
    linkedList.insertAtEnd(4)
    linkedList.insertAtEnd(5)
    linkedList.insertAtEnd(6)
    linkedList.insertAtEnd(7)
    linkedList.insertAtEnd(8)
    linkedList.insertAtEnd(9)
    linkedList.insertAtEnd(10)
    swapNodes(linkedList.getHeadNode(), 3).printLinkedList()
    swapNodes(linkedList.getHeadNode(), 2).printLinkedList()
    swapNodes(linkedList.getHeadNode(), 5).printLinkedList()
    swapNodes(linkedList.getHeadNode(), 2).printLinkedList()
}

private fun swapNodes(headNode: SingleLinkedList.Node<Int>?, k: Int): SingleLinkedList.Node<Int>? {
    if (headNode?.nextNode == null) {
        return headNode
    }
    var headNodeRef = headNode
    val length = findLength(headNode)
    val swapLength = k.coerceAtMost(length - k + 1)
    if (length % 2 == 1 && k == length - k) {
        return headNodeRef
    }
    var previousPointer1: SingleLinkedList.Node<Int>? = null
    var currentPointer1: SingleLinkedList.Node<Int>? = headNode
    var nextPointer1: SingleLinkedList.Node<Int>? = headNode.nextNode
    var previousPointer2: SingleLinkedList.Node<Int>? = null
    var currentPointer2: SingleLinkedList.Node<Int>? = headNode
    var nextPointer2: SingleLinkedList.Node<Int>? = headNode.nextNode
    var index = 1
    while (index <= length - swapLength + 1) {
        if (index < swapLength) {
            previousPointer1 = currentPointer1
            currentPointer1 = nextPointer1
            nextPointer1 = nextPointer1?.nextNode
        }
        if (index <= length - swapLength) {
            previousPointer2 = currentPointer2
            currentPointer2 = nextPointer2
            nextPointer2 = nextPointer2?.nextNode
        }
        index += 1
    }
    if (previousPointer1 == null && nextPointer2 == null) {
        // Swapping Head and Tail
        if (currentPointer1?.nextNode == currentPointer2) {
            currentPointer2?.nextNode = currentPointer1
            currentPointer1?.nextNode = null
            headNodeRef = currentPointer2
        } else {
            currentPointer2?.nextNode = nextPointer1
            previousPointer2?.nextNode = currentPointer1
            currentPointer1?.nextNode = null
            headNodeRef = currentPointer2
        }
    } else {
        if (currentPointer1?.nextNode == currentPointer2) {
            previousPointer1?.nextNode = currentPointer2
            currentPointer2?.nextNode = currentPointer1
            currentPointer1?.nextNode = nextPointer2
        } else {
            previousPointer1?.nextNode = currentPointer2
            currentPointer2?.nextNode = nextPointer1
            previousPointer2?.nextNode = currentPointer1
            currentPointer1?.nextNode = nextPointer2
        }
    }
    return headNodeRef
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