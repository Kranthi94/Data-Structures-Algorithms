package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/

fun main() {
    val list = SingleLinkedList<Int>()
    list.insertAtStart(1)
    list.insertAtEnd(1)
    list.insertAtEnd(1)
    list.insertAtEnd(2)
    list.insertAtEnd(4)
    list.insertAtEnd(4)
    list.print()
    removeDuplicates(list.getHeadNode()).printLinkedList()
}

private fun removeDuplicates(headNode: SingleLinkedList.Node<Int>?): SingleLinkedList.Node<Int>? {
    if (headNode?.nextNode == null) {
        return headNode
    }
    var currentNode = headNode
    while (currentNode?.nextNode != null) {
        if (currentNode.value == currentNode.nextNode!!.value) {
            currentNode.nextNode = currentNode.nextNode?.nextNode
        } else {
            currentNode = currentNode.nextNode
        }
    }
    return headNode
}