package algorithms.graphs.cycle

import datastructures.graphs.*
import utils.printValue

fun main() {
    val directedGraph = DirectedGraph<Int>()
    directedGraph.addEdge(0, 1)
    directedGraph.addEdge(0, 4)
    directedGraph.addEdge(1, 2)
    directedGraph.addEdge(2, 3)
    directedGraph.addEdge(4, 1)
    directedGraph.addEdge(4, 5)
    directedGraph.addEdge(6, 7)
    directedGraph.addEdge(7, 8)
    directedGraph.addEdge(8, 6)
    hasCycleDirectedDFS(directedGraph).printValue()
}

private fun <T> hasCycleDirectedTopologicalSort(graph: IGraph<T>): Boolean {
    return false
}

private fun <T> hasCycleDirectedDFS(graph: IGraph<T>): Boolean {
    val allNodes = graph.getAllNodes()
    val visitedNodeSet = mutableSetOf<IGraphNode<T>>()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            if (hasCycleDirectedDFS(graph, node, visitedNodeSet, mutableMapOf())) {
                return true
            }
        }
    }
    return false
}

private fun <T> hasCycleDirectedDFS(
    graph: IGraph<T>,
    currentNode: IGraphNode<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    parentNodesMap: MutableMap<IGraphNode<T>, Int>
): Boolean {
    visitedNodeSet.add(currentNode)
    parentNodesMap[currentNode] = parentNodesMap.getOrDefault(currentNode, 0) + 1
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (parentNodesMap.containsKey(node)) {
            return true
        } else if (!visitedNodeSet.contains(node)) {
            if (hasCycleDirectedDFS(graph, node, visitedNodeSet, parentNodesMap)) {
                return true
            }
        }
    }
    parentNodesMap.remove(currentNode)
    return false
}