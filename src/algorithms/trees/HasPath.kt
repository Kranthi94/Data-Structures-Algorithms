package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printValue

// // https://leetcode.com/problems/path-sum/description/

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
    hasPath(binaryTree.getRootNode(), 10).printValue()
}

private fun hasPath(rootNode: TreeNode<Int>?, targetSum: Int): Boolean {
    if (rootNode == null) {
        return false
    }
    if (rootNode.leftNode == null && rootNode.rightNode == null && targetSum - rootNode.value == 0) {
        return true
    }
    val currentValue = rootNode.value
    return hasPath(rootNode.leftNode, targetSum - currentValue) || hasPath(rootNode.rightNode, targetSum - currentValue)
}