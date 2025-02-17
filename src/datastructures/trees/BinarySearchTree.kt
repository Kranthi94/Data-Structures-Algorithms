package datastructures.trees

import utils.compareTo
import kotlin.math.abs

open class BinarySearchTree<T> : BaseBinaryTree<T>(), IBalancedBinarySearchTree<T> {

    override fun isEmpty(): Boolean {
        return count == 0
    }

    override fun getNodesCount(): Int {
        return count
    }

    override fun getRootNode(): TreeNode<T>? {
        return mRootNode
    }

    override fun isBalanced(node: TreeNode<T>?): Boolean {
        if (node == null) {
            return true
        }
        return abs(getNodeHeight(node.leftNode) - getNodeHeight(node.rightNode)) <= 1
    }

    override fun insert(value: T): TreeNode<T>? {
        if (mRootNode == null) {
            this.mRootNode = TreeNode(value)
        } else {
            recursiveInsert(mRootNode, value)
        }
        count += 1
        return mRootNode
    }

    override fun delete(value: T): TreeNode<T>? {
        return null
    }

    override fun search(value: T): TreeNode<T>? {
        return recursiveSearch(mRootNode, value)
    }

    private fun recursiveInsert(node: TreeNode<T>?, value: T) {
        if (node == null) {
            return
        }
        val compare = value.compareTo(node.value)
        if (compare == -1 && node.leftNode == null) {
            node.leftNode = TreeNode(value)
            return
        }
        if (compare == 1 && node.rightNode == null) {
            node.rightNode = TreeNode(value)
            return
        }
        return when (compare) {
            -1 -> recursiveInsert(node.leftNode, value)
            1 -> recursiveInsert(node.rightNode, value)
            else -> {}
        }
    }

    private fun recursiveSearch(node: TreeNode<T>?, value: T): TreeNode<T>? {
        if (node == null) {
            return null
        }
        return when (value.compareTo(node.value)) {
            -1 -> recursiveSearch(node.leftNode, value)
            0 -> node
            else -> recursiveSearch(node.rightNode, value)
        }
    }
}