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
    findPathToNode(binaryTree.getRootNode(), 4).printList()
    findPathToNode(binaryTree.getRootNode(), 5).printList()
    findPathToNode(binaryTree.getRootNode(), 6).printList()
    findPathToNode(binaryTree.getRootNode(), 7).printList()
}

private fun <T> findPathToNode(currentNode: TreeNode<T>?, value: T): MutableList<TreeNode<T>> {
    if (currentNode == null) {
        return mutableListOf()
    }
    if (currentNode.value == value) {
        return mutableListOf(currentNode)
    }
    val result: MutableList<TreeNode<T>> = mutableListOf()
    var path = findPathToNode(currentNode.leftNode, value)
    if (path.isEmpty()) {
        path = findPathToNode(currentNode.rightNode, value)
    }
    if (path.isNotEmpty()) {
        result.add(currentNode)
        result.addAll(path)
    }
    return result
}

private fun <T> findPathToNode(rootNode: TreeNode<T>?, node: TreeNode<T>?): MutableList<TreeNode<T>> {
    if (rootNode == null) {
        return mutableListOf()
    }
    if (rootNode == node) {
        return mutableListOf(node)
    }
    val result: MutableList<TreeNode<T>> = mutableListOf()
    var path = findPathToNode(rootNode.leftNode, node)
    if (path.isEmpty()) {
        path = findPathToNode(rootNode.rightNode, node)
    }
    if (path.isNotEmpty()) {
        result.add(rootNode)
        result.addAll(path)
    }
    return result
}