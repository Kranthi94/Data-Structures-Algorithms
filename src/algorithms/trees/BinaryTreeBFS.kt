package algorithms.trees

import datastructures.queue.Queue
import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printList

// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/

fun main() {
    val binaryTree = BinaryTree<Int>()
    binaryTree.insert(null, 1)
    binaryTree.insert(1, 3)
    binaryTree.insert(1, -2)
    binaryTree.insert(3, 4)
    binaryTree.insert(3, -5)
    binaryTree.insert(-2, 6)
    binaryTree.insert(-2, 7)
    levelOrderTraversal1(binaryTree.getRootNode()).printList()
    levelOrderTraversal2(binaryTree.getRootNode()).printList()
}

private fun <T> levelOrderTraversal1(rootNode: TreeNode<T>?): ArrayList<ArrayList<T>> {
    val result: ArrayList<ArrayList<T>> = ArrayList()
    if (rootNode == null) {
        return result
    }
    result.add(arrayListOf(rootNode.value))
    val currentNodes: ArrayList<TreeNode<T>> = arrayListOf(rootNode)
    while (currentNodes.isNotEmpty()) {
        val newList = arrayListOf<T>()
        val currentNodesSize = currentNodes.size
        repeat(currentNodesSize) {
            val node = currentNodes.removeAt(0)
            node.leftNode?.let { leftNode ->
                currentNodes.add(leftNode)
                newList.add(leftNode.value)
            }
            node.rightNode?.let { rightNode ->
                currentNodes.add(rightNode)
                newList.add(rightNode.value)
            }
        }
        if (newList.isNotEmpty()) {
            result.add(newList)
        }
    }
    return result
}

private fun <T> levelOrderTraversal2(rootNode: TreeNode<T>?): ArrayList<ArrayList<T>> {
    val result: ArrayList<ArrayList<T>> = ArrayList()
    if (rootNode == null) {
        return result
    }
    val queue: Queue<TreeNode<T>> = Queue()
    queue.enQueue(rootNode)
    result.add(arrayListOf(rootNode.value))
    while (!queue.isEmpty()) {
        val queueSize = queue.getSize()
        val newList: ArrayList<T> = arrayListOf()
        repeat(queueSize) {
            val currentNode = queue.deQueue()
            currentNode.let {
                it.leftNode?.let { leftNode ->
                    queue.enQueue(leftNode)
                    newList.add(leftNode.value)
                }
                it.rightNode?.let { rightNode ->
                    queue.enQueue(rightNode)
                    newList.add(rightNode.value)
                }
            }
        }
        if (newList.isNotEmpty()) {
            result.add(newList)
        }
    }
    return result
}

