package algorithms.graphs.spanning

import datastructures.graphs.IWeightedGraph
import datastructures.graphs.WeightedUnDirectedGraph
import utils.printValue

fun main() {
    val weightedUnDirectedGraph = WeightedUnDirectedGraph<Int>()
    weightedUnDirectedGraph.addEdge(0, 1, 10)
    weightedUnDirectedGraph.addEdge(0, 2, 15)
    weightedUnDirectedGraph.addEdge(0, 3, 30)
    weightedUnDirectedGraph.addEdge(1, 3, 40)
    weightedUnDirectedGraph.addEdge(2, 3, 50)
    findMST(weightedUnDirectedGraph).getAllEdges().printValue()
}

private fun <T> findMST(graph: IWeightedGraph<T>): IWeightedGraph<T> {
    val parentNodeMap: MutableMap<T, T> = mutableMapOf()
    val allNodes = graph.getAllNodes()
    allNodes.forEach { node ->
        parentNodeMap[node.getValue()] = node.getValue()
    }
    return findMST(graph, allNodes.size - 1, parentNodeMap)
}

private fun <T> findMST(graph: IWeightedGraph<T>, maxEdgesCount: Int, parentNodeMap: MutableMap<T, T>): IWeightedGraph<T> {
    val directedGraph: IWeightedGraph<T> = WeightedUnDirectedGraph()
    val allEdges = graph.getAllEdges().sortedBy { it.getWeight() }
    allEdges.forEach { edge ->
        val source = edge.getSource()
        val destination = edge.getDestination()
        val sourceParent = findAbsoluteParent(source.getValue(), parentNodeMap)
        val destinationParent = findAbsoluteParent(destination.getValue(), parentNodeMap)
        if (sourceParent != destinationParent) {
            parentNodeMap[sourceParent] = destinationParent
            directedGraph.addEdge(source.getValue(), destination.getValue(), edge.getWeight())
        }
        if (directedGraph.getAllEdges().size / 2 == maxEdgesCount) {
            return directedGraph
        }
    }
    return directedGraph
}

private fun <T> findAbsoluteParent(node: T, parentNodeMap: MutableMap<T, T>): T {
    var currentNode = node
    while (parentNodeMap[currentNode] != currentNode) {
        currentNode = parentNodeMap[currentNode]!!
    }
    if (parentNodeMap[node] != currentNode) {
        parentNodeMap[node] != currentNode
    }
    return currentNode
}