package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
// https://leetcode.com/problems/linked-list-cycle/description/

fun main() {
    val linkedList = SingleLinkedList<Int>()
    linkedList.insertAtStart(1)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(3)
    linkedList.insertAtEnd(4)
    linkedList.insertAtEnd(5)
    linkedList.insertAtEnd(6)
    linkedList.insertAtEnd(7)
    linkedList.getNode(7)?.nextNode = linkedList.getNode(4)
    println("Loop Detected - ${detectLoop(linkedList.getHeadNode())}")
    println("Loop Length - ${findLoopLength(linkedList.getHeadNode())}")
    println("Loop Start Node Value - ${findLoopStartNode(linkedList.getHeadNode())?.value}")
    println("After Removing Loop")
    findAndRemoveLoop(linkedList.getHeadNode())?.printLinkedList()
}

private fun <T> detectLoop(headNode: SingleLinkedList.Node<T>?): Boolean {
    if (headNode == null) {
        return false
    }
    var slowPointer: SingleLinkedList.Node<T>? = headNode
    var fastPointer: SingleLinkedList.Node<T>? = headNode.nextNode
    do {
        if (slowPointer == fastPointer) {
            return true
        }
        slowPointer = slowPointer?.nextNode
        fastPointer = fastPointer?.nextNode?.nextNode
    } while (slowPointer != null && fastPointer != null)
    return false
}

private fun <T> findLoopLength(headNode: SingleLinkedList.Node<T>?): Int {
    if (headNode == null) {
        return -1
    }
    var slowPointer: SingleLinkedList.Node<T>? = headNode
    var fastPointer: SingleLinkedList.Node<T>? = headNode.nextNode
    do {
        if (slowPointer == fastPointer) {
            var length = 0
            do {
                length += 1
                slowPointer = slowPointer?.nextNode
            } while (slowPointer != fastPointer)
            return length
        }
        slowPointer = slowPointer?.nextNode
        fastPointer = fastPointer?.nextNode?.nextNode
    } while (slowPointer != null && fastPointer != null)
    return -1
}

private fun <T> findLoopStartNode(headNode: SingleLinkedList.Node<T>?): SingleLinkedList.Node<T>? {
    if (headNode == null) {
        return null
    }
    val loopLength = findLoopLength(headNode)
    if (loopLength != -1) {
        var slowPointer = headNode
        var fastPointer = headNode
        var index = 0
        while (index < loopLength) {
            slowPointer = slowPointer?.nextNode
            index += 1
        }
        while (slowPointer != null && fastPointer != null) {
            if (slowPointer == fastPointer) {
                return slowPointer
            }
            slowPointer = slowPointer.nextNode
            fastPointer = fastPointer.nextNode
        }
    }
    return null
}

private fun <T> findAndRemoveLoop(headNode: SingleLinkedList.Node<T>?): SingleLinkedList.Node<T>? {
    if (headNode == null) {
        return null
    }
    val loopStartNode = findLoopStartNode(headNode)
    val loopLength = findLoopLength(headNode)
    if (loopStartNode != null) {
        var slowPointer = loopStartNode
        var index = 1
        while (index < loopLength) {
            slowPointer = slowPointer?.nextNode
            index += 1
        }
        slowPointer?.nextNode = null
    }
    return headNode
}