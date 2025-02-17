package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

fun main() {
    val inputs = arrayOf(
        arrayOf(-10,-3,0,5,9)
    )
    inputs.forEach {
        sortedArrayToBST(it).print()
    }
}

fun sortedArrayToBST(array: Array<Int>): BinaryTree<Int> {
    val binaryTree = BinaryTree<Int>()
    binaryTree.setRootNode(buildTree(array, 0, array.size - 1))
    return binaryTree
}

fun buildTree(array: Array<Int>, startIndex: Int, endIndex: Int): TreeNode<Int>? {
    if (startIndex > endIndex) return null
    val mid = (startIndex + endIndex) / 2
    val node = TreeNode(array[mid])
    node.leftNode = buildTree(array, startIndex, mid - 1)
    node.rightNode = buildTree(array, mid + 1, endIndex)
    return node
}