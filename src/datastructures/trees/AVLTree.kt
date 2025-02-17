package datastructures.trees

class AVLTree<T> : BinarySearchTree<T>(), IAVLTree<T> {

    override fun insert(value: T): TreeNode<T>? {
        return doBalance(super.insert(value))
    }

    override fun doBalance(node: TreeNode<T>?): TreeNode<T>? {
        if (node == null) {
            return null
        }
        var nodeRef = node
        val isRootNode = nodeRef == mRootNode
        val leftTreeHeight = getNodeHeight(node.leftNode)
        val rightTreeHeight = getNodeHeight(node.rightNode)
        if (leftTreeHeight - rightTreeHeight > 2) {
            doBalance(node.leftNode)
        } else if (rightTreeHeight - leftTreeHeight > 2) {
            doBalance(node.rightNode)
        } else if (leftTreeHeight - rightTreeHeight == 2) {
            nodeRef = rotateRight(node)
        } else if (rightTreeHeight - leftTreeHeight == 2) {
            nodeRef = rotateLeft(node)
        }
        if (isRootNode) {
            mRootNode = nodeRef
        }
        return nodeRef
    }

    override fun rotateLeft(node: TreeNode<T>?): TreeNode<T>? {
        if (node == null) {
            return null
        }
        val childNode = node.rightNode
        val childLeftNode = childNode?.leftNode
        childNode?.leftNode = node
        node.rightNode = childLeftNode
        return childNode
    }

    override fun rotateRight(node: TreeNode<T>?): TreeNode<T>? {
        if (node == null) {
            return null
        }
        val childNode = node.leftNode
        val childRightNode = childNode?.rightNode
        childNode?.rightNode = node
        node.leftNode = childRightNode
        return childNode
    }
}