package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printList

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

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
    binaryTree.print()
    val serializedTree = serializeTree(binaryTree.getRootNode()).reversed().toMutableList()
    serializedTree.printList()
    binaryTree.setRootNode(deSerializeTree(serializedTree))
    binaryTree.print()
}

private fun serializeTree(rootNode: TreeNode<Int>?): MutableList<String?> {
    val result = mutableListOf<String?>()
    if (rootNode == null) {
        result.add(null)
        return result
    }
    result.add(rootNode.value.toString())
    result.addAll(serializeTree(rootNode.leftNode))
    result.addAll(serializeTree(rootNode.rightNode))
    return result
}

private fun deSerializeTree(list: MutableList<String?>): TreeNode<Int>? {
    if (list.isEmpty()) {
        return null
    }
    val element = list.removeAt(list.size - 1) ?: return null
    val rootNode: TreeNode<Int> = TreeNode(element.toInt())
    rootNode.leftNode = deSerializeTree(list)
    rootNode.rightNode = deSerializeTree(list)
    return rootNode
}