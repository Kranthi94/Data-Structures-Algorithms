package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printValue

// https://leetcode.com/problems/palindrome-linked-list/description/

fun main() {
    val linkedList = SingleLinkedList<Int>()
    linkedList.insertAtStart(1)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(1)
    isPalindrome(linkedList.getHeadNode()).printValue()
}

private fun isPalindrome(headNode: SingleLinkedList.Node<Int>?): Boolean {
    if (headNode?.nextNode == null) {
        return true
    }
    val midNode = findMiddleNode(headNode)!!
    var firstHead = headNode
    var secondHead = reverseLinkedList(midNode)
    while (firstHead != null && secondHead != null) {
        if (firstHead.value != secondHead.value) {
            return false
        }
        firstHead = firstHead.nextNode
        secondHead = secondHead.nextNode
    }
    return true
}

private fun reverseLinkedList(reverseNode: SingleLinkedList.Node<Int>?): SingleLinkedList.Node<Int>? {
    if (reverseNode?.nextNode == null) {
        return null
    }
    var previousNode: SingleLinkedList.Node<Int>? = null
    var currentNode = reverseNode.nextNode
    var nextNode = currentNode?.nextNode
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
    return slowPointerPrevious
}