package datastructures.trees

class BinaryTree<T>: BaseBinaryTree<T>(), IBinaryTree<T> {

    override fun insert(parentValue: T?, value: T) {
        val node = TreeNode(value)
        if(parentValue == null) {
            mRootNode = node
        } else {
            val parentNode = search(parentValue) ?: return
            when {
                parentNode.leftNode == null -> {
                    parentNode.leftNode = node
                }
                parentNode.rightNode == null -> {
                    parentNode.rightNode = node
                }
            }
        }
        count += 1
    }

    override fun delete(value: T): TreeNode<T>? {
        return null
    }

    override fun search(value: T): TreeNode<T>? {
        return recursiveSearch(mRootNode, value)
    }

    private fun recursiveSearch(node: TreeNode<T>?, value: T): TreeNode<T>? {
        if (node == null) {
            return null
        }
        if (node.value == value) {
            return node
        }
        var foundNode = recursiveSearch(node.leftNode, value)
        if (foundNode == null) {
            foundNode = recursiveSearch(node.rightNode, value)
        }
        return foundNode
    }
}