package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printPair

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

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
    lowestCommonAncestor(binaryTree.getRootNode(), 4, 5).printPair()
    lowestCommonAncestor(binaryTree.getRootNode(), 5, 7).printPair()
    lowestCommonAncestor(binaryTree.getRootNode(), 7, 5).printPair()
    lowestCommonAncestor(binaryTree.getRootNode(), 1, 7).printPair()
    lowestCommonAncestor(binaryTree.getRootNode(), 2, 3).printPair()
    lowestCommonAncestor(binaryTree.getRootNode(), 2, 6).printPair()
}

private fun <T> lowestCommonAncestor(rootNode: TreeNode<T>?, value1: T, value2: T): TreeNode<T>? {
    if (rootNode == null) {
        return null
    }
    if (rootNode.value == value1 || rootNode.value == value2) {
        return rootNode
    }
    val left = lowestCommonAncestor(rootNode.leftNode, value1, value2)
    val right = lowestCommonAncestor(rootNode.rightNode, value1, value2)
    if (left != null && right != null) {
        return rootNode
    }
    return left ?: right
}