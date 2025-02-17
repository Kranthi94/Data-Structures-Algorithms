package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://leetcode.com/problems/reverse-nodes-in-k-group/description/
// https://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/

fun main() {
    val linkedList = SingleLinkedList<Int>()
    linkedList.insertAtStart(1)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(3)
    linkedList.insertAtEnd(4)
    linkedList.insertAtEnd(5)
    linkedList.insertAtEnd(6)
    reverseLinkedList(linkedList.getHeadNode(), 7).printLinkedList()
}

private fun reverseLinkedList(
    headNode: SingleLinkedList.Node<Int>?,
    groupSize: Int
): SingleLinkedList.Node<Int>? {
    var endReached = false
    var startIndex = 1
    var endIndex = startIndex + groupSize - 1
    var resultHeadNode: SingleLinkedList.Node<Int>? = headNode
    while (!endReached) {
        val pair = reverseLinkedList(resultHeadNode, startIndex, endIndex)
        resultHeadNode = pair.first
        endReached = pair.second
        startIndex = endIndex + 1
        endIndex = startIndex + groupSize - 1
    }
    return resultHeadNode
}

private fun reverseLinkedList(
    headNode: SingleLinkedList.Node<Int>?,
    leftPosition: Int,
    rightPosition: Int
): Pair<SingleLinkedList.Node<Int>?, Boolean> {
    if (headNode?.nextNode == null || leftPosition == rightPosition || leftPosition < 1) {
        return Pair(headNode, false)
    }
    var leftBeforeNode: SingleLinkedList.Node<Int>? = null
    var leftCurrentNode: SingleLinkedList.Node<Int>? = null
    var rightCurrentNode: SingleLinkedList.Node<Int>? = null
    var rightNextNode: SingleLinkedList.Node<Int>? = null

    var previousNode: SingleLinkedList.Node<Int>? = null
    var currentNode = headNode
    var nextNode: SingleLinkedList.Node<Int>? = currentNode.nextNode
    var index = 1
    var leftFound = false
    var rightFound = false
    var nodeReversed = 0
    while (currentNode != null && index <= rightPosition) {
        if (index == leftPosition) {
            leftBeforeNode = previousNode
            leftCurrentNode = currentNode
            leftFound = true
        }
        if (index == rightPosition) {
            rightCurrentNode = currentNode
            rightNextNode = nextNode
            rightFound = true
        }
        if (index in leftPosition..rightPosition) {
            currentNode.nextNode = previousNode
            nodeReversed += 1
        }
        if (leftFound && rightFound) {
            break
        }
        previousNode = currentNode
        currentNode = nextNode
        nextNode = nextNode?.nextNode
        index += 1
    }
    if (leftFound && rightFound) {
        if (leftBeforeNode == null) {
            leftCurrentNode?.nextNode = rightNextNode
            return Pair(rightCurrentNode, false)
        }
        leftBeforeNode.nextNode = rightCurrentNode
        leftCurrentNode?.nextNode = rightNextNode
    } else if (nodeReversed > 0) {
        var currentReversingNode: SingleLinkedList.Node<Int>? = previousNode
        var previousReversingNode: SingleLinkedList.Node<Int>? = null
        while (currentReversingNode != leftBeforeNode) {
            val temp: SingleLinkedList.Node<Int>? = currentReversingNode?.nextNode
            currentReversingNode?.nextNode = previousReversingNode
            previousReversingNode = currentReversingNode
            currentReversingNode = temp
        }
    }
    return Pair(headNode, !(leftFound && rightFound))
}