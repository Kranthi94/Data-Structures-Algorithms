package algorithms.graphs.cycle

import datastructures.graphs.IGraph
import datastructures.graphs.IGraphNode
import datastructures.graphs.UnDirectedGraph
import utils.printValue

fun main() {
    val unDirectedGraph = UnDirectedGraph<Int>()
    unDirectedGraph.addEdge(0, 1)
    unDirectedGraph.addEdge(1, 2)
    unDirectedGraph.addEdge(1, 4)
    unDirectedGraph.addEdge(2, 3)
    unDirectedGraph.addEdge(4, 5)
    unDirectedGraph.addEdge(6, 7)
    unDirectedGraph.addEdge(7, 8)
    unDirectedGraph.addEdge(8, 6)
    hasCycleUnDirectedDFS(unDirectedGraph).printValue()
}

private fun <T> hasCycleUnDirectedBFS(graph: IGraph<T>): Boolean {
    return false
}

private fun <T> hasCycleUnDirectedUnionFind(graph: IGraph<T>): Boolean {
    return false
}

private fun <T> hasCycleUnDirectedDFS(graph: IGraph<T>): Boolean {
    val allNodes = graph.getAllNodes()
    val visitedNodeSet = mutableSetOf<IGraphNode<T>>()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            if (hasCycleUnDirectedDFS(graph, node, node, visitedNodeSet)) {
                return true
            }
        }
    }
    return false
}

private fun <T> hasCycleUnDirectedDFS(
    graph: IGraph<T>,
    parentNode: IGraphNode<T>,
    currentNode: IGraphNode<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>
): Boolean {
    visitedNodeSet.add(currentNode)
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (visitedNodeSet.contains(node)) {
            if (node != parentNode) {
                return true
            }
        } else {
            if (hasCycleUnDirectedDFS(graph, currentNode, node, visitedNodeSet)) {
                return true
            }
        }
    }
    return false
}