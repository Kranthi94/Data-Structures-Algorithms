package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://www.geeksforgeeks.org/rotate-a-linked-list/
// https://leetcode.com/problems/rotate-list/description/

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
    rotateLinkedList(linkedList.getHeadNode(), 20).printLinkedList()
}

private fun rotateLinkedList(headNode: SingleLinkedList.Node<Int>?, rotations: Int): SingleLinkedList.Node<Int>? {
    if (headNode?.nextNode == null) {
        return headNode
    }
    val headNodeRef: SingleLinkedList.Node<Int>?
    var length = 1
    var tailNode = headNode
    while (tailNode?.nextNode != null) {
        length += 1
        tailNode = tailNode.nextNode
    }
    val totalRotations = rotations % length
    if (totalRotations == 0) {
        return headNode
    }
    tailNode?.nextNode = headNode
    val skips = length - totalRotations
    var newLast = headNode
    var index = 1
    while (index < skips) {
        newLast = newLast?.nextNode
        index += 1
    }
    headNodeRef = newLast?.nextNode
    newLast?.nextNode = null
    return headNodeRef
}