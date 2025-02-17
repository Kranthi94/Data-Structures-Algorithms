package algorithms.trees

import datastructures.queue.Queue
import datastructures.trees.BinarySearchTree
import datastructures.trees.TreeNode
import utils.printArray

// https://leetcode.com/problems/average-of-levels-in-binary-tree/description/

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
    binaryTree.print()
    levelAverage(binaryTree.getRootNode()).printArray()
}

private fun levelAverage(rootNode: TreeNode<Int>?): DoubleArray {
    var result = doubleArrayOf()
    if (rootNode == null) {
        return result
    }
    val queue: Queue<TreeNode<Int>> = Queue()
    queue.enQueue(rootNode)
    while (!queue.isEmpty()) {
        val queueSize = queue.getSize()
        var sum = 0.0
        repeat(queueSize) {
            val currentNode = queue.deQueue()
            sum += (currentNode.value.toFloat())
            currentNode.leftNode?.let { leftNode ->
                queue.enQueue(leftNode)
            }
            currentNode.rightNode?.let { rightNode ->
                queue.enQueue(rightNode)
            }
        }
        result = result.plus(sum / queueSize)
    }
    return result
}