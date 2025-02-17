package algorithms.trees

import datastructures.trees.BinarySearchTree
import datastructures.trees.TreeNode
import utils.printValue

fun main() {
    val binaryTree = BinarySearchTree<Int>()
    binaryTree.insert(4)
    binaryTree.insert(2)
    binaryTree.insert(1)
    binaryTree.insert(3)
    binaryTree.insert(6)
    binaryTree.insert(5)
    binaryTree.insert(7)
    binaryTree.print()
    findKthSmallest(binaryTree.getRootNode(), 1).printValue()
    findKthSmallest(binaryTree.getRootNode(), 2).printValue()
    findKthSmallest(binaryTree.getRootNode(), 3).printValue()
    findKthSmallest(binaryTree.getRootNode(), 4).printValue()
    findKthSmallest(binaryTree.getRootNode(), 5).printValue()
    findKthSmallest(binaryTree.getRootNode(), 6).printValue()
    findKthSmallest(binaryTree.getRootNode(), 7).printValue()
}

private fun findKthSmallest(rootNode: TreeNode<Int>?, k: Int): Int {
    return findKthSmallest(rootNode, k, Array(1) { 0 })
}

private fun findKthSmallest(rootNode: TreeNode<Int>?, k: Int, countArray: Array<Int>): Int {
    if (rootNode == null) {
        return -1
    }
    val left = findKthSmallest(rootNode.leftNode, k, countArray)
    if (left != -1) {
        return left
    }
    countArray[0] += 1
    if (countArray[0] == k) {
        return rootNode.value
    }
    val right = findKthSmallest(rootNode.rightNode, k, countArray)
    return right
}