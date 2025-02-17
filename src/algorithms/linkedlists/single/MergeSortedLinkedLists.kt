package algorithms.linkedlists.single

import datastructures.linkedlists.SingleLinkedList
import utils.printLinkedList

// https://leetcode.com/problems/merge-two-sorted-lists/description/

fun main() {
    val list1 = SingleLinkedList<Int>()
    list1.insertAtEnd(1)
    list1.insertAtEnd(2)
    list1.insertAtEnd(4)
    list1.insertAtEnd(5)

    val list2 = SingleLinkedList<Int>()
    list2.insertAtEnd(-1)
    list2.insertAtEnd(3)
    list2.insertAtEnd(4)
    list2.insertAtEnd(7)

    mergeTwoLists(list1.getHeadNode(), list2.getHeadNode()).printLinkedList()
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
    if (headNode1.value > headNode2.value) {
        headNode2.nextNode = mergeTwoLists(headNode1, headNode2.nextNode)
        return headNode2
    } else {
        headNode1.nextNode = mergeTwoLists(headNode1.nextNode, headNode2)
        return headNode1
    }
}