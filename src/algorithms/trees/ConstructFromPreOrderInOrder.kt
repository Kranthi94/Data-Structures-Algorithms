package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
// https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/

fun main() {
    val binaryTree = BinaryTree<Int>()
    binaryTree.setRootNode(constructFromPreOrderInOrder(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7)))
    binaryTree.print()
    binaryTree.setRootNode(constructFromPreOrderInOrder(intArrayOf(-1), intArrayOf(-1)))
    binaryTree.print()
}


private fun constructFromPreOrderInOrder(preOrderArray: IntArray, inOrderArray: IntArray): TreeNode<Int>? {
    if (preOrderArray.isEmpty() || inOrderArray.isEmpty()) {
        return null
    }
    val inOrderIndexMap: MutableMap<Int, Int> = mutableMapOf()
    inOrderArray.forEachIndexed { index, value ->
        inOrderIndexMap[value] = inOrderIndexMap.getOrDefault(value, 0) + index
    }
    return constructFromPreOrderInOrder(preOrderArray, Array(1) { 0 }, inOrderIndexMap, 0, inOrderArray.size - 1)
}

private fun constructFromPreOrderInOrder(
    preOrderArray: IntArray,
    preOrderIndexArray: Array<Int>,
    inOrderIndexMap: Map<Int, Int>,
    inOrderStartIndex: Int,
    inOrderEndIndex: Int
): TreeNode<Int>? {
    if (preOrderIndexArray[0] !in preOrderArray.indices || inOrderStartIndex > inOrderEndIndex) {
        return null
    }
    val currentPreOrderValue = preOrderArray[preOrderIndexArray[0]]
    val inOrderIndex = inOrderIndexMap[currentPreOrderValue]!!
    val rootNode = TreeNode(currentPreOrderValue)
    preOrderIndexArray[0] += 1
    rootNode.leftNode = constructFromPreOrderInOrder(
        preOrderArray,
        preOrderIndexArray,
        inOrderIndexMap,
        inOrderStartIndex,
        inOrderIndex - 1
    )
    rootNode.rightNode = constructFromPreOrderInOrder(
        preOrderArray,
        preOrderIndexArray,
        inOrderIndexMap,
        inOrderIndex + 1,
        inOrderEndIndex
    )
    return rootNode
}
