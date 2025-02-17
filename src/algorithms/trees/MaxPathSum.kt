package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printPair

// https://leetcode.com/problems/binary-tree-maximum-path-sum

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
    maxPathSum(binaryTree.getRootNode()).printPair()
}

private fun maxPathSum(rootNode: TreeNode<Int>?): Pair<Int, Int> {
    if (rootNode == null) {
        return Pair(0, 0)
    }
    val leftSum = maxPathSum(rootNode.leftNode)
    val rightSum = maxPathSum(rootNode.rightNode)
    return Pair(rootNode.value + maxOf(leftSum.first, rightSum.first), maxOf(leftSum.second, rightSum.second, leftSum.first + rightSum.first + rootNode.value))
}