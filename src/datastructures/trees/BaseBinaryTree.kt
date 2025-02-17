package datastructures.trees

import kotlin.math.pow

abstract class BaseBinaryTree<T>: IBaseBinaryTree<T> {

    protected var mRootNode: TreeNode<T>? = null
    protected var count = 0

    override fun isEmpty(): Boolean {
        return count == 0
    }

    override fun setRootNode(node: TreeNode<T>?) {
        this.mRootNode = node
    }

    override fun getRootNode(): TreeNode<T>? {
        return mRootNode
    }

    override fun getNodesCount(): Int {
        return count
    }

    override fun getTreeHeight(): Int {
        return getNodeHeight(mRootNode)
    }

    override fun getNodeHeight(node: TreeNode<T>?): Int {
        if (node == null) {
            return 0
        }
        return getNodeHeight(node.leftNode).coerceAtLeast(getNodeHeight(node.rightNode)) + 1
    }

    override fun print(): String {
        val stringBuilder = StringBuilder()
        if (mRootNode == null) {
            stringBuilder.append("null")
            println(stringBuilder.toString())
            return stringBuilder.toString()
        }
        val string = recursivePrint(1, mutableListOf(mRootNode)).second
        println(string)
        return string
    }

    private fun recursivePrint(level: Int, nodesList: MutableList<TreeNode<T>?>): Pair<Int, String> {
        val nextLevelNodes = mutableListOf<TreeNode<T>?>()
        var allNodesNull = true
        nodesList.forEach {
            nextLevelNodes.add(it?.leftNode)
            nextLevelNodes.add(it?.rightNode)
            allNodesNull = allNodesNull && it?.leftNode == null && it?.rightNode == null
        }
        if (allNodesNull) {
            val currentLevelNodesCount = 2.0.pow(level - 1).toInt()
            val stringBuilder = StringBuilder()
            for (i in 0 until currentLevelNodesCount) {
                if (i in nodesList.indices && nodesList[i] != null) {
                    stringBuilder.append(nodesList[i]?.value)
                } else {
                    stringBuilder.append("#")
                }
                stringBuilder.append("  ")
            }
            return Pair(currentLevelNodesCount, stringBuilder.toString())
        }
        val result = recursivePrint(level + 1, nextLevelNodes)
        val previousLevelString = result.second

        val currentLevelNodesCount = 2.0.pow(level - 1).toInt()
        val currentLevelSpacesCount = (currentLevelNodesCount - 1) * 2
        val currentLevelTotalCount = currentLevelNodesCount + currentLevelSpacesCount

        val stringBuilder = StringBuilder()
        for (i in 0 until currentLevelNodesCount) {
            if (i in nodesList.indices && nodesList[i] != null) {
                stringBuilder.append(nodesList[i]?.value)
            } else {
                stringBuilder.append("#")
            }
            stringBuilder.append("  ")
        }
        val previousLevelNodesCount = result.first
        val previousLevelSpacesCount = (previousLevelNodesCount - 1) * 2
        val previousLevelTotalCount = previousLevelNodesCount + previousLevelSpacesCount
        val spacesDifference = previousLevelTotalCount - currentLevelTotalCount
        val finalStringBuilder = StringBuilder()
        repeat(spacesDifference / 2) {
            finalStringBuilder.append(" ")
        }
        finalStringBuilder.append(stringBuilder)
        repeat(spacesDifference / 2) {
            finalStringBuilder.append(" ")
        }
        finalStringBuilder.appendLine()
        finalStringBuilder.append(previousLevelString)
        return Pair(previousLevelNodesCount, finalStringBuilder.toString())
    }
}