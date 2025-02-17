package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
// https://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder/

fun main() {
    val binaryTree = BinaryTree<Int>()
    binaryTree.setRootNode(constructFromInOrderPostOrder(intArrayOf(9, 3, 15, 20, 7), intArrayOf(9, 15, 7, 20, 3)))
    binaryTree.print()
    binaryTree.setRootNode(constructFromInOrderPostOrder(intArrayOf(-1), intArrayOf(-1)))
    binaryTree.print()
}

private fun constructFromInOrderPostOrder(inOrderArray: IntArray, postOrderArray: IntArray): TreeNode<Int>? {
    if (inOrderArray.isEmpty() || postOrderArray.isEmpty()) {
        return null
    }
    val inOrderIndexMap: MutableMap<Int, Int> = mutableMapOf()
    inOrderArray.forEachIndexed { index, value ->
        inOrderIndexMap[value] = inOrderIndexMap.getOrDefault(value, 0) + index
    }
    return constructFromInOrderPostOrder(
        postOrderArray,
        Array(1) { postOrderArray.size - 1 },
        inOrderIndexMap,
        0,
        postOrderArray.size - 1
    )
}

private fun constructFromInOrderPostOrder(
    postOrderArray: IntArray,
    postOrderIndexArray: Array<Int>,
    inOrderIndexMap: Map<Int, Int>,
    inOrderStartIndex: Int,
    inOrderEndIndex: Int
): TreeNode<Int>? {
    if (postOrderIndexArray[0] !in postOrderArray.indices || inOrderStartIndex > inOrderEndIndex) {
        return null
    }
    val currentPostOrderValue = postOrderArray[postOrderIndexArray[0]]
    val inOrderIndex = inOrderIndexMap[currentPostOrderValue]!!
    postOrderIndexArray[0] -= 1
    val rootNode = TreeNode(currentPostOrderValue)
    rootNode.rightNode = constructFromInOrderPostOrder(postOrderArray, postOrderIndexArray, inOrderIndexMap, inOrderIndex + 1, inOrderEndIndex)
    rootNode.leftNode = constructFromInOrderPostOrder(postOrderArray, postOrderIndexArray, inOrderIndexMap, inOrderStartIndex, inOrderIndex - 1)
    return rootNode
}

//
//
//private fun constructFromInOrderPostOrder(
//    inOrderArray: IntArray,
//    postOrderArray: IntArray,
//    inOrderStartIndex: Int,
//    inOrderEndIndex: Int,
//    postOrderStartIndex: Int,
//    postOrderEndIndex: Int
//): TreeNode<Int>? {
//    if (inOrderStartIndex > inOrderEndIndex || postOrderStartIndex > postOrderEndIndex) {
//        return null
//    }
//    if (postOrderStartIndex == postOrderEndIndex) {
//        return TreeNode(postOrderArray[postOrderStartIndex])
//    }
//    val rootValue = postOrderArray[postOrderEndIndex]
//    val indexInInOrder = inOrderArray.indexOf(rootValue)
//    val rightTreeSize = inOrderEndIndex - indexInInOrder
//    val rootNode = TreeNode(rootValue)
//    rootNode.rightNode = constructFromInOrderPostOrder(
//        inOrderArray,
//        postOrderArray,
//        indexInInOrder + 1,
//        inOrderEndIndex,
//        postOrderEndIndex - rightTreeSize,
//        postOrderEndIndex - 1
//    )
//    rootNode.leftNode = constructFromInOrderPostOrder(
//        inOrderArray,
//        postOrderArray,
//        inOrderStartIndex,
//        indexInInOrder - 1,
//        postOrderStartIndex,
//        postOrderEndIndex - rightTreeSize - 1
//    )
//    return rootNode
//}
