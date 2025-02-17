package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printValue

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
    sumRoot(binaryTree.getRootNode(), 0).printValue()
}

private fun sumRoot(rootNode: TreeNode<Int>?, currentSum: Int): Int {
    if (rootNode == null) {
        return 0
    }
    if (rootNode.leftNode == null && rootNode.rightNode == null) {
        return currentSum * 10 + rootNode.value
    }
    val leftSum = sumRoot(rootNode.leftNode, currentSum * 10 + rootNode.value)
    val rightSum = sumRoot(rootNode.rightNode, currentSum * 10 + rootNode.value)
    return leftSum + rightSum
}