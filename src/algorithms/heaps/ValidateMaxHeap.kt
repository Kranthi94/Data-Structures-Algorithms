package algorithms.heaps

import datastructures.queue.Queue
import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.compareTo
import utils.printValue

fun main() {
    val binaryTree: BinaryTree<Int> = BinaryTree()
    binaryTree.insert(null, -1)
    binaryTree.insert(3, 1)
    binaryTree.insert(3, 2)
    binaryTree.insert(2, 0)
    validateMaxHeap(binaryTree).printValue()
}

private fun <T> validateMaxHeap(binaryTree: BinaryTree<T>): Boolean {
    if (binaryTree.isEmpty() || binaryTree.getRootNode() == null) {
        return false
    }
    var treeEndReached = false
    val queue: Queue<TreeNode<T>> = Queue()
    queue.enQueue(binaryTree.getRootNode()!!)
    while (!queue.isEmpty()) {
        val poppedNode: TreeNode<T> = queue.deQueue()
        val leftNode = poppedNode.leftNode
        val rightNode = poppedNode.rightNode
        if (leftNode != null) {
            if (treeEndReached || poppedNode.value.compareTo(leftNode.value) < 0) {
                return false
            } else {
                queue.enQueue(leftNode)
            }
        } else {
            treeEndReached = true
        }
        if (rightNode != null) {
            if (treeEndReached || poppedNode.value.compareTo(rightNode.value) < 0) {
                return false
            } else {
                queue.enQueue(rightNode)
            }
        } else {
            treeEndReached = true
        }
    }
    return true
}