package algorithms.trees

import datastructures.queue.Queue
import datastructures.trees.BinarySearchTree
import datastructures.trees.TreeNode
import utils.printPair

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
    findNextNode(binaryTree.getRootNode(), 11).printPair()
}

private fun <T> findNextNode(rootNode: TreeNode<T>?, target: T): TreeNode<T>? {
    if (rootNode == null) {
        return null
    }
    val queue: Queue<TreeNode<T>> = Queue()
    queue.enQueue(rootNode)
    while (!queue.isEmpty()) {
        val currentNode = queue.deQueue()
        currentNode.leftNode?.let {
            queue.enQueue(it)
        }
        currentNode.rightNode?.let {
            queue.enQueue(it)
        }
        if (currentNode.value == target) {
            return if (queue.isEmpty()) null else queue.peek()
        }
    }
    return null
}