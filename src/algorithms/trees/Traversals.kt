package algorithms.trees

import datastructures.trees.BinarySearchTree
import datastructures.trees.TreeNode
import utils.printValue

fun main() {
    val binaryTree = BinarySearchTree<Int>()
    binaryTree.insert(5)
    binaryTree.insert(4)
    binaryTree.insert(6)
    binaryTree.insert(3)
    binaryTree.insert(7)
    binaryTree.insert(2)
    binaryTree.insert(8)
    preOrderTraversal(binaryTree.getRootNode()).printValue()
    inOrderTraversal(binaryTree.getRootNode()).printValue()
    postOrderTraversal(binaryTree.getRootNode()).printValue()
}

private fun <T> preOrderTraversal(rootNode: TreeNode<T>?): String {
    if (rootNode == null) {
        return ""
    }
    val stringBuilder = StringBuilder()
    stringBuilder.append(rootNode.value.toString())
    stringBuilder.append(preOrderTraversal(rootNode.leftNode))
    stringBuilder.append(preOrderTraversal(rootNode.rightNode))
    return stringBuilder.toString()
}

private fun <T> inOrderTraversal(rootNode: TreeNode<T>?): String {
    if (rootNode == null) {
        return ""
    }
    val stringBuilder = StringBuilder()
    stringBuilder.append(inOrderTraversal(rootNode.leftNode))
    stringBuilder.append(rootNode.value.toString())
    stringBuilder.append(inOrderTraversal(rootNode.rightNode))
    return stringBuilder.toString()
}

private fun <T> postOrderTraversal(rootNode: TreeNode<T>?): String {
    if (rootNode == null) {
        return ""
    }
    val stringBuilder = StringBuilder()
    stringBuilder.append(postOrderTraversal(rootNode.leftNode))
    stringBuilder.append(postOrderTraversal(rootNode.rightNode))
    stringBuilder.append(rootNode.value.toString())
    return stringBuilder.toString()
}