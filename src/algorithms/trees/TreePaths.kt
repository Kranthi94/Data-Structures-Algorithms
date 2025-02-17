package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printValue

fun main() {
    val binaryTree = BinaryTree<Int>()
    binaryTree.insert(null, 1)
    binaryTree.insert(1, 3)
    binaryTree.insert(1, -2)
    binaryTree.insert(3, 4)
    binaryTree.insert(3, -5)
    binaryTree.insert(-2, 6)
    binaryTree.insert(-2, 7)
    binaryTree.print()
    pathExists(binaryTree.getRootNode(), arrayOf(1, 2, 4), 0).printValue()
    pathExists(binaryTree.getRootNode(), arrayOf(1, 4, 6), 0).printValue()
    pathExists(binaryTree.getRootNode(), arrayOf(1, 3, 7), 0).printValue()
    pathExists(binaryTree.getRootNode(), arrayOf(1, 2, 5), 0).printValue()
    pathExists(binaryTree.getRootNode(), arrayOf(1, 3, 5), 0).printValue()
}

private fun <T> pathExists(rootNode: TreeNode<T>?, path: Array<T>, currentIndex: Int): Boolean {
    if (rootNode == null) {
        return false
    }
    if (rootNode.leftNode == null && rootNode.rightNode == null) {
        return rootNode.value == path[currentIndex]
    }
    if (rootNode.value != path[currentIndex]) {
        return false
    }
    return pathExists(rootNode.leftNode, path, currentIndex + 1) || pathExists(rootNode.rightNode, path, currentIndex + 1)
}

