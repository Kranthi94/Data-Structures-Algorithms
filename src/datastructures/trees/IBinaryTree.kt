package datastructures.trees

interface IBaseBinaryTree<T> {
    fun isEmpty(): Boolean
    fun getNodesCount(): Int
    fun getRootNode(): TreeNode<T>?
    fun setRootNode(node: TreeNode<T>?)
    fun getTreeHeight(): Int
    fun getNodeHeight(node: TreeNode<T>?): Int
    fun print(): String
}

interface IBinaryTree<T>: IBaseBinaryTree<T> {
    fun insert(parentValue: T?, value: T)
    fun delete(value: T): TreeNode<T>?
    fun search(value: T): TreeNode<T>?
}

interface IBalancedBinarySearchTree<T>: IBaseBinaryTree<T> {
    fun insert(value: T): TreeNode<T>?
    fun delete(value: T): TreeNode<T>?
    fun search(value: T): TreeNode<T>?
    fun isBalanced(node: TreeNode<T>?): Boolean
}

interface IAVLTree<T> : IBalancedBinarySearchTree<T> {
    fun doBalance(node: TreeNode<T>?): TreeNode<T>?
    fun rotateRight(node: TreeNode<T>?): TreeNode<T>?
    fun rotateLeft(node: TreeNode<T>?): TreeNode<T>?
}

interface ISegmentTree {
    fun constructTree()
    fun update(index: Int, value: Int): Boolean
    fun findSumInRange(startIndex: Int, endIndex: Int): Int
}