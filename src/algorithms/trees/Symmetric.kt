package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printValue

// https://leetcode.com/problems/symmetric-tree/description/

fun main() {
    val binaryTree = BinaryTree<Int>()
    binaryTree.insert(null, 1)
    binaryTree.insert(1, 2)
    binaryTree.insert(1, 2)
    binaryTree.print()
    isSymmetric(binaryTree.getRootNode()?.leftNode, binaryTree.getRootNode()?.rightNode).printValue()
}

private fun <T> isSymmetric(rootNode1: TreeNode<T>?, rootNode2: TreeNode<T>?): Boolean {
    if (rootNode1 == null || rootNode2 == null) {
        return rootNode1 == rootNode2
    }
    return rootNode1.value == rootNode2.value && isSymmetric(rootNode1.leftNode, rootNode2.rightNode) && isSymmetric(rootNode1.rightNode, rootNode2.leftNode)
}