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
    mergeSort(linkedList.getHeadNode()).printLinkedList()
}

private fun mergeSort(leftHeadNode: SingleLinkedList.Node<Int>?): SingleLinkedList.Node<Int>? {
    if (leftHeadNode?.nextNode == null) {
        return leftHeadNode
    }
    val middleNode = findMiddleNode(leftHeadNode)
    return mergeTwoLists(mergeSort(leftHeadNode), mergeSort(middleNode))
}

private fun findMiddleNode(headNode: SingleLinkedList.Node<Int>?): SingleLinkedList.Node<Int>? {
    if (headNode == null) {
        return null
    }
    var slowPointerPrevious: SingleLinkedList.Node<Int>? = null
    var fastPointer = headNode
    while (fastPointer?.nextNode != null) {
        slowPointerPrevious = if(slowPointerPrevious == null) headNode else slowPointerPrevious.nextNode
        fastPointer = fastPointer.nextNode?.nextNode
    }
    val midPointer = slowPointerPrevious?.nextNode
    slowPointerPrevious?.nextNode = null
    return midPointer
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

//private fun mergeTwoLists(
//    headNode1: SingleLinkedList.Node<Int>?,
//    headNode2: SingleLinkedList.Node<Int>?
//): SingleLinkedList.Node<Int>? {
//    if (headNode1 == null) {
//        return headNode2
//    }
//    if (headNode2 == null) {
//        return headNode1
//    }
//    var node1 = headNode1
//    var node2 = headNode2
//    var headNode: SingleLinkedList.Node<Int>? = null
//    var currentNode: SingleLinkedList.Node<Int>? = null
//    while (node1 != null && node2 != null) {
//        val v1 = node1.value
//        val v2 = node2.value
//        val node: SingleLinkedList.Node<Int>
//        if (v1 <= v2) {
//            node = SingleLinkedList.Node(v1)
//            node1 = node1.nextNode
//        } else {
//            node = SingleLinkedList.Node(v2)
//            node2 = node2.nextNode
//        }
//        if (headNode == null) {
//            headNode = node
//            currentNode = headNode
//        } else {
//            currentNode?.nextNode = node
//            currentNode = node
//        }
//    }
//    if (node1 != null) {
//        currentNode?.nextNode = node1
//    }
//    if (node2 != null) {
//        currentNode?.nextNode = node2
//    }
//    return headNode
//}