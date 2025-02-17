package algorithms.trees

import datastructures.trees.TreeNode

// https://leetcode.com/problems/validate-binary-search-tree/description/

fun main() {

}

fun isValidBST(root: TreeNode<Int>?): Boolean = isValidBSTInternal(root)

private fun isValidBSTInternal(
    root: TreeNode<Int>?,
    min: Long = Long.MIN_VALUE,
    max: Long = Long.MAX_VALUE,
): Boolean {
    if (root == null) return true
    if (root.value !in min..max) return false
    return isValidBSTInternal(root.leftNode, min, root.value.toLong() - 1) && isValidBSTInternal(root.rightNode, root.value.toLong() + 1, max)
}