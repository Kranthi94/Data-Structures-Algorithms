package algorithms.graphs.traversals

import datastructures.graphs.*
import utils.printList

// Time Complexity: O(V ^ V)

fun main() {
    val graph = UnDirectedGraph<Int>()
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    graph.addEdge(3, 4)
    graph.addEdge(3, 5)
    graph.addEdge(4, 5)
    graph.addEdge(5, 6)
    val result = mutableListOf<String>()
    findAllPaths(graph, 0, 5, mutableSetOf(), "", result)
    result.printList()
}

private fun <T> findAllPaths(
    graph: IGraph<T>,
    currentValue: T,
    targetValue: T,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    path: String,
    result: MutableList<String>
) {
    var currentPath = path
    val currentNode = GraphNode(currentValue)
    val targetNode = GraphNode(targetValue)
    if (currentNode.getValue() == targetNode.getValue()) {
        currentPath += "${currentNode.getValue()} | "
        result.add(currentPath)
        return
    }
    currentPath += "${currentNode.getValue()} -> "
    visitedNodeSet.add(currentNode)
    graph.getAdjacentNodes(currentNode.getValue()).forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findAllPaths(graph, node.getValue(), targetNode.getValue(), visitedNodeSet, currentPath, result)
        }
    }
    visitedNodeSet.remove(currentNode)
}