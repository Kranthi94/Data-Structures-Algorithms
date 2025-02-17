package datastructures.trees

class TreeNode<T>(var value: T) {
    var leftNode: TreeNode<T>? = null
    var rightNode: TreeNode<T>? = null

    override fun toString(): String {
        return value.toString()
    }
}