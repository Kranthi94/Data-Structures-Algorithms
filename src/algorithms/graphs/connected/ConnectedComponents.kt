package algorithms.graphs.connected

import datastructures.graphs.IGraph
import datastructures.graphs.IGraphNode
import datastructures.graphs.UnDirectedGraph
import utils.printValue

fun main() {
    val unDirectedGraph = UnDirectedGraph<Int>()
    unDirectedGraph.addEdge(0, 1)
    unDirectedGraph.addEdge(2, 3)
    unDirectedGraph.addEdge(2, 4)
    unDirectedGraph.addEdge(2, 5)
    unDirectedGraph.addEdge(2, 6)
    unDirectedGraph.addEdge(1, 7)
    unDirectedGraph.addEdge(8, 9)
    unDirectedGraph.addEdge(8, 10)
    unDirectedGraph.addEdge(8, 11)
    unDirectedGraph.addEdge(8, 12)
    unDirectedGraph.addEdge(8, 13)
    findLargestComponentBFS(unDirectedGraph).printValue()
}

private fun <T> findLargestComponentDisjointSets(graph: IGraph<T>): Int {
    return 0
}

private fun <T> findLargestComponentBFS(graph: IGraph<T>): Int {
    val allNodes = graph.getAllNodes()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    var maxCount = 0
    var previousVisitedNodeCount = 0
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findLargestComponentBFS(graph, node, visitedNodeSet)
            maxCount = maxCount.coerceAtLeast(visitedNodeSet.size - previousVisitedNodeCount)
            previousVisitedNodeCount = visitedNodeSet.size
        }
    }
    return maxCount
}

private fun <T> findLargestComponentBFS(graph: IGraph<T>, currentNode: IGraphNode<T>, visitedNodeSet: MutableSet<IGraphNode<T>>) {
    visitedNodeSet.add(currentNode)
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findLargestComponentBFS(graph, node, visitedNodeSet)
        }
    }
}