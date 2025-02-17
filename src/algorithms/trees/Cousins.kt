package algorithms.trees

import datastructures.queue.Queue
import datastructures.trees.BinarySearchTree
import datastructures.trees.TreeNode
import utils.printValue

// https://leetcode.com/problems/cousins-in-binary-tree/description/

fun main() {
    val binaryTree = BinarySearchTree<Int>()
    binaryTree.insert( 5)
    binaryTree.insert(1)
    binaryTree.insert(6)
    binaryTree.insert(2)
    binaryTree.insert(7)
    binaryTree.insert(3)
    binaryTree.insert(8)
    binaryTree.insert(4)
    binaryTree.insert(9)
    binaryTree.insert(10)
    binaryTree.print()
    checkCousins(binaryTree.getRootNode(), 4, 9).printValue()
}

private fun <T> checkCousins(rootNode: TreeNode<T>?, x: T, y: T): Boolean {
    if (rootNode == null) {
        return false
    }
    val queue: Queue<TreeNode<T>> = Queue()
    queue.enQueue(rootNode)
    while (!queue.isEmpty()) {
        val queueSize = queue.getSize()
        var xParentNode: TreeNode<T>? = null
        var yParentNode: TreeNode<T>? = null
        repeat(queueSize) {
            val currentNode = queue.deQueue()
            currentNode.leftNode?.let { leftNode ->
                queue.enQueue(leftNode)
                if (leftNode.value == x) {
                    xParentNode = currentNode
                }
                if (leftNode.value == y) {
                    yParentNode = currentNode
                }
            }
            currentNode.rightNode?.let { rightNode ->
                queue.enQueue(rightNode)
                if (rightNode.value == x) {
                    xParentNode = currentNode
                }
                if (rightNode.value == y) {
                    yParentNode = currentNode
                }
            }
        }
        if ((xParentNode != null && yParentNode == null) || (xParentNode == null && yParentNode != null)) {
            return false
        }
        if (xParentNode != null && yParentNode != null) {
            return xParentNode != yParentNode
        }
    }
    return false
}