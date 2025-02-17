package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printList

// // https://leetcode.com/problems/path-sum-ii/description/

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
    findPaths(binaryTree.getRootNode(), 10).printList()
}

private fun findPaths(rootNode: TreeNode<Int>?, targetSum: Int): ArrayList<ArrayList<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    if (rootNode == null) {
        return result
    }
    if (rootNode.leftNode == null && rootNode.rightNode == null) {
        if (targetSum - rootNode.value == 0) {
            result.add(arrayListOf(rootNode.value))
        }
        return result
    }
    val currentValue = rootNode.value
    val leftList = findPaths(rootNode.leftNode, targetSum - currentValue)
    val rightList = findPaths(rootNode.rightNode, targetSum - currentValue)
    leftList.forEach {
        if (it.isNotEmpty()) {
            it.add(0, currentValue)
            result.add(it)
        }
    }
    rightList.forEach {
        if (it.isNotEmpty()) {
            it.add(0, currentValue)
            result.add(it)
        }
    }
    return result
}
