package algorithms.trees

import datastructures.trees.BinaryTree
import datastructures.trees.TreeNode
import utils.printList

// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/

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
    verticalOrderTraversal(binaryTree.getRootNode()).printList()
    verticalOrderTraversal1(binaryTree.getRootNode()).printList()
}

class Node(
    val value: Int,
    private val x: Int,
    private val y: Int
): Comparable<Node> {
    override fun compareTo(other: Node): Int {
        if (y != other.y) {
            return y.compareTo(other.y)
        }
        if (x != other.x) {
            return x.compareTo(other.x)
        }
        return value.compareTo(other.value)
    }
}

private fun verticalOrderTraversal1(rootNode: TreeNode<Int>?): List<Int> {
    if (rootNode == null) {
        return listOf()
    }
    val nodesList: MutableList<Node> = mutableListOf()
    addNodes(rootNode, 0, 0, nodesList)
    return nodesList.sorted().map { it.value }
}

private fun addNodes(rootNode: TreeNode<Int>?, x: Int, y: Int, list: MutableList<Node>) {
    if (rootNode == null) {
        return
    }
    list.add(Node(rootNode.value, x, y))
    addNodes(rootNode.leftNode, x + 1, y - 1, list)
    addNodes(rootNode.rightNode, x + 1, y + 1, list)
}


private fun verticalOrderTraversal(rootNode: TreeNode<Int>?): List<List<Int>> {
    val hashMap = HashMap<String, MutableList<Int>>()
    verticalOrderTraversal(rootNode, 0, 0, hashMap)
    val finalMap: HashMap<Int, MutableList<Int>> = hashMapOf()
    hashMap.forEach { (key, value) ->
        val column = key.split("$")[1].toInt()
        if (!finalMap.containsKey(column)) {
            finalMap[column] = value
        } else {
            finalMap[column]!!.addAll(value)
        }
    }
    return finalMap.toList().sortedBy { it.first }.map { it.second }
}

private fun verticalOrderTraversal(rootNode: TreeNode<Int>?, row: Int, column: Int, hashMap: HashMap<String, MutableList<Int>>) {
    if (rootNode == null) {
        return
    }
    if (rootNode.leftNode == null && rootNode.rightNode == null) {
        addValueToMap(hashMap, row, column, rootNode.value)
        return
    }
    verticalOrderTraversal(rootNode.leftNode, row + 1, column - 1, hashMap)
    addValueToMap(hashMap, row, column, rootNode.value)
    verticalOrderTraversal(rootNode.rightNode, row + 1, column + 1, hashMap)
}

private fun addValueToMap(hashMap: HashMap<String, MutableList<Int>>, row: Int, column: Int, value: Int) {
    if (!hashMap.containsKey("$row$$column")) {
        hashMap["$row$$column"] = arrayListOf()
    }
    val list = hashMap["$row$$column"]!!
    list.add(value)
    hashMap["$row$$column"] = list.sorted().toMutableList()
}