package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode

// https://leetcode.com/problems/invert-binary-tree/description/

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
    invertBinaryTree(binaryTree.getRootNode())
    binaryTree.print()
}

private fun <T> invertBinaryTree(rootNode: TreeNode<T>?): TreeNode<T>? {
    if (rootNode == null) {
        return null
    }
    val leftTree = invertBinaryTree(rootNode.leftNode)
    val rightTree = invertBinaryTree(rootNode.rightNode)
    rootNode.rightNode = leftTree
    rootNode.leftNode = rightTree
    return rootNode
}