package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printList

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
    allPaths(binaryTree.getRootNode()).printList()
}

private fun allPaths(rootNode: TreeNode<Int>?): ArrayList<ArrayList<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    if (rootNode == null) {
        return result
    }
    if (rootNode.leftNode == null && rootNode.rightNode == null) {
        result.add(arrayListOf(rootNode.value))
        return result
    }
    val currentValue = rootNode.value
    val leftList = allPaths(rootNode.leftNode)
    val rightList = allPaths(rootNode.rightNode)
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