package algorithms.graphs.topological

import datastructures.graphs.DirectedGraph
import datastructures.graphs.IGraph
import datastructures.graphs.IGraphNode
import utils.printValue

// https://leetcode.com/problems/course-schedule/description/

fun main() {
    val prerequisites = arrayOf(
        arrayOf(1, 0),
        arrayOf(0, 1)
    )
    canFinish(prerequisites).printValue()
}

private fun <T> canFinish(prerequisites: Array<Array<T>>): Boolean {
    val directedGraph = DirectedGraph<T>()
    prerequisites.forEach { arr ->
        directedGraph.addEdge(arr[0], arr[1])
    }
    val allNodes = directedGraph.getAllNodes()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            if (hasCycle(directedGraph, node, visitedNodeSet, mutableListOf())) {
                return false
            }
        }
    }
    return true
}

private fun <T> hasCycle(
    graph: IGraph<T>,
    currentNode: IGraphNode<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    parentNodesList: MutableList<IGraphNode<T>>
): Boolean {
    visitedNodeSet.add(currentNode)
    parentNodesList.add(currentNode)
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (parentNodesList.contains(node)) {
            return true
        } else if (!visitedNodeSet.contains(node)) {
            if (hasCycle(graph, node, visitedNodeSet, parentNodesList)) {
                return true
            }
        }
    }
    parentNodesList.remove(currentNode)
    return false
}