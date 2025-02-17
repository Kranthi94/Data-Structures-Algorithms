package algorithms.trees

import datastructures.queue.Queue
import datastructures.trees.BinarySearchTree
import datastructures.trees.TreeNode
import utils.printList

// https://leetcode.com/problems/binary-tree-right-side-view/description/

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
    rightSideView(binaryTree.getRootNode()).printList()
}

private fun <T> rightSideView(rootNode: TreeNode<T>?): List<T> {
    val result: ArrayList<T> = arrayListOf()
    if (rootNode == null) {
        return result
    }
    val queue: Queue<TreeNode<T>> = Queue()
    queue.enQueue(rootNode)
    while (!queue.isEmpty()) {
        val queueSize = queue.getSize()
        repeat(queueSize) {
            val currentNode = queue.deQueue()
            if (it == queueSize - 1) {
                result.add(currentNode.value)
            }
            currentNode.leftNode?.let { leftNode ->
                queue.enQueue(leftNode)
            }
            currentNode.rightNode?.let { rightNode ->
                queue.enQueue(rightNode)
            }
        }
    }
    return result
}