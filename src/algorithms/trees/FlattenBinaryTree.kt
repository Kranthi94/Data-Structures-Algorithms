package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

fun main() {
    val binaryTree = BinaryTree<Int>()
    binaryTree.insert(null, 1)
    binaryTree.insert(1, 2)
    binaryTree.insert(1, 3)
    binaryTree.insert(2, 4)
    binaryTree.insert(2, 5)
    binaryTree.insert(3, 6)
    binaryTree.insert(3, 7)
    binaryTree.print()
    flattenBinaryTree(binaryTree.getRootNode())
    binaryTree.print()
}

private fun <T> flattenBinaryTree(rootNode: TreeNode<T>?): TreeNode<T>? {
    if (rootNode == null) {
        return null
    }
    var currentNode = rootNode
    while (currentNode != null) {
        val leftNode = currentNode.leftNode
        if (leftNode != null) {
            val rightMostChild = findRightMostChild(leftNode)
            rightMostChild.rightNode = currentNode.rightNode
            currentNode.rightNode = leftNode
            currentNode.leftNode = null
        }
        currentNode = currentNode.rightNode
    }
    return rootNode
}

private fun <T> findRightMostChild(node: TreeNode<T>): TreeNode<T> {
    var currentNode = node
    while (currentNode.rightNode != null) {
        currentNode = currentNode.rightNode!!
    }
    return currentNode
}