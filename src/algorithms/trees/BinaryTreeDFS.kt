package algorithms.trees

import datastructures.stack.Stack
import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printList

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
    depthOrderTraversal(binaryTree.getRootNode()).printList()
}

private fun <T> depthOrderTraversal(rootNode: TreeNode<T>?): List<T> {
    val result = mutableListOf<T>()
    if (rootNode == null) {
        return result
    }
    val stack = Stack<TreeNode<T>>()
    stack.push(rootNode)
    while (!stack.isEmpty()) {
        val currentNode = stack.pop()
        currentNode.rightNode?.let { rightNode ->
            stack.push(rightNode)
        }
        currentNode.leftNode?.let { leftNode ->
            stack.push(leftNode)
        }
        result.add(currentNode.value)
    }
    return result
}

