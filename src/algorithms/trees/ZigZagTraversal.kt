package algorithms.trees

import datastructures.trees.BinarySearchTree
import datastructures.trees.TreeNode
import utils.printList
import java.util.Deque
import java.util.LinkedList

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/

fun main() {
    val binaryTree = BinarySearchTree<Int>()
    binaryTree.insert(5)
    binaryTree.insert(4)
    binaryTree.insert(3)
    binaryTree.insert(7)
    binaryTree.insert(2)
    binaryTree.insert(1)
    binaryTree.insert(0)
    binaryTree.insert(8)
    binaryTree.insert(9)
    binaryTree.insert(10)
    binaryTree.print()
    traverseZigZag(binaryTree.getRootNode()).printList()
}

private fun <T> traverseZigZag(rootNode: TreeNode<T>?): ArrayList<ArrayList<T>> {
    val result = ArrayList<ArrayList<T>>()
    if (rootNode == null) {
        return result
    }
    val deque: Deque<TreeNode<T>> = LinkedList()
    deque.offerFirst(rootNode)
    var reverse = true
    while (deque.isNotEmpty()) {
        val queueSize = deque.size
        val newList: ArrayList<T> = arrayListOf()
        repeat(queueSize) {
            val currentNode = if (reverse) deque.removeFirst() else deque.removeLast()
            if (reverse) {
                currentNode?.leftNode?.let { leftNode ->
                    deque.offerLast(leftNode)
                }
                currentNode?.rightNode?.let { rightNode ->
                    deque.offerLast(rightNode)
                }
            } else {
                currentNode?.rightNode?.let { rightNode ->
                    deque.offerFirst(rightNode)
                }
                currentNode?.leftNode?.let { leftNode ->
                    deque.offerFirst(leftNode)
                }

            }
            newList.add(currentNode.value)
        }
        result.add(newList)
        reverse = !reverse
    }
    return result
}

