package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://leetcode.com/problems/reverse-linked-list/description/
// https://leetcode.com/problems/reverse-linked-list-ii/description/

fun main() {
    val linkedList = SingleLinkedList<Int>()
    linkedList.insertAtStart(1)
    linkedList.insertAtEnd(2)
    linkedList.insertAtEnd(3)
    linkedList.insertAtEnd(4)
    linkedList.insertAtEnd(5)
    linkedList.insertAtEnd(6)
    println("Before Reversing")
    linkedList.getHeadNode().printLinkedList()
    println("After Reversing")
//    reverseLinkedList(linkedList.getHeadNode(), 1, 3).printLinkedList()
//    reverseLinkedList(linkedList.getHeadNode(), 2, 5).printLinkedList()
//    reverseLinkedList(linkedList.getHeadNode(), 4, 6).printLinkedList()
//    reverseLinkedList(linkedList.getHeadNode(), 1, 6).printLinkedList()
//    reverseLinkedList(linkedList.getHeadNode(), 1, 1).printLinkedList()
//    reverseLinkedList(linkedList.getHeadNode(), 0, 1).printLinkedList()
    reverseLinkedList(linkedList.getHeadNode(), 2, 7).printLinkedList()
}

private fun reverseLinkedList(
    headNode: SingleLinkedList.Node<Int>?,
    leftPosition: Int,
    rightPosition: Int
): SingleLinkedList.Node<Int>? {
    if (headNode?.nextNode == null || leftPosition == rightPosition || leftPosition < 1) {
        return headNode
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
            return rightCurrentNode
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
    return headNode
}