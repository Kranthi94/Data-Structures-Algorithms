package datastructures.trees

import utils.printValue

fun main() {
    val segmentTree = SegmentTree(arrayOf(3, 8, 7, 6, -2, -8, 4, 9))
    segmentTree.constructTree()
    segmentTree.findSumInRange(0, 7).printValue()
    segmentTree.update(0, 0).printValue()
    segmentTree.update(4, 2).printValue()
    segmentTree.findSumInRange(0, 7).printValue()
}

class SegmentTree(val array: Array<Int>) : ISegmentTree {

    private var rootNode: SegmentTreeNode? = null

    override fun constructTree() {
        rootNode = constructTree(array, 0, array.size - 1)
    }

    private fun constructTree(array: Array<Int>, startIndex: Int, endIndex: Int): SegmentTreeNode? {
        if (array.isEmpty() || startIndex > endIndex) {
            return null
        }
        if (array.size == 1 || startIndex == endIndex) {
            return SegmentTreeNode(startIndex, endIndex).apply {
                value = array[startIndex]
            }
        }
        val midIndex = startIndex + (endIndex - startIndex) / 2
        val currentNode = SegmentTreeNode(startIndex, endIndex)
        currentNode.leftNode = constructTree(array, startIndex, midIndex)
        currentNode.rightNode = constructTree(array, midIndex + 1, endIndex)
        currentNode.leftNode?.let {
            currentNode.value += it.value
        }
        currentNode.rightNode?.let {
            currentNode.value += it.value
        }
        return currentNode
    }

    override fun update(index: Int, value: Int): Boolean {
        rootNode?.let {
            if (index in array.indices) {
                rootNode = update(it, index, value)
                return true
            }
        }
        return false
    }

    private fun update(node: SegmentTreeNode, index: Int, value: Int): SegmentTreeNode {
        if (node.startIndex == index && node.endIndex == index) {
            node.value = value
            return node
        }
        val startIndex = node.startIndex
        val endIndex = node.endIndex
        val midIndex = startIndex + (endIndex - startIndex) / 2
        if (index <= midIndex) {
            node.leftNode = update(node.leftNode!!, index, value)
        } else {
            node.rightNode = update(node.rightNode!!, index, value)
        }
        node.value = node.leftNode!!.value + node.rightNode!!.value
        return node
    }

    override fun findSumInRange(startIndex: Int, endIndex: Int): Int {
        return findValueInRange(rootNode!!, startIndex, endIndex)
    }

    private fun findValueInRange(node: SegmentTreeNode?, startIndex: Int, endIndex: Int): Int {
        if (node == null || startIndex > endIndex) {
            return 0
        }
        return if (endIndex < node.startIndex || startIndex > node.endIndex) {
            0
        } else if (startIndex <= node.startIndex && node.endIndex <= endIndex) {
            node.value
        } else {
            val leftValue = findValueInRange(node.leftNode, startIndex, endIndex)
            val rightValue = findValueInRange(node.rightNode, startIndex, endIndex)
            leftValue + rightValue
        }
    }
}

class SegmentTreeNode(val startIndex: Int, val endIndex: Int) {
    var value: Int = 0
    var leftNode: SegmentTreeNode? = null
    var rightNode: SegmentTreeNode? = null
}