package algorithms.graphs.connected

import datastructures.graphs.DirectedGraph
import datastructures.graphs.IGraph
import datastructures.graphs.IGraphNode
import datastructures.stack.Stack
import utils.printList

// Time Complexity : O(V + E)

fun main() {
    val directedGraph: IGraph<Int> = DirectedGraph()
    directedGraph.addEdge(0, 2)
    directedGraph.addEdge(0, 3)
    directedGraph.addEdge(1, 0)
    directedGraph.addEdge(2, 1)
    directedGraph.addEdge(3, 4)
    findStronglyConnectedComponents(directedGraph).printList()
}

private fun <T> findStronglyConnectedComponents(graph: IGraph<T>): MutableList<MutableList<IGraphNode<T>>> {
    val stack: Stack<IGraphNode<T>> = Stack()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val allNodes = graph.getAllNodes()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findTopologicalStack(graph, node, visitedNodeSet, stack)
        }
    }
    val transposedGraph = findGraphTranspose(graph)
    visitedNodeSet.clear()
    val totalConnectedComponents: MutableList<MutableList<IGraphNode<T>>> = mutableListOf()
    while (!stack.isEmpty()) {
        val poppedNode = stack.pop()
        if (!visitedNodeSet.contains(poppedNode)) {
            val connectedComponents: MutableList<IGraphNode<T>> = mutableListOf()
            doDFSTraversal(transposedGraph, poppedNode, visitedNodeSet, connectedComponents)
            totalConnectedComponents.add(connectedComponents)
        }
    }
    return totalConnectedComponents
}

private fun <T> findTopologicalStack(
    graph: IGraph<T>,
    currentNode: IGraphNode<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    stack: Stack<IGraphNode<T>>
) {
    visitedNodeSet.add(currentNode)
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findTopologicalStack(graph, node, visitedNodeSet, stack)
        }
    }
    stack.push(currentNode)
}

private fun <T> findGraphTranspose(graph: IGraph<T>): IGraph<T> {
    val transposeGraph: IGraph<T> = DirectedGraph()
    graph.getAllEdges().forEach { edge ->
        transposeGraph.addEdge(edge.getDestination().getValue(), edge.getSource().getValue())
    }
    return transposeGraph
}

private fun <T> doDFSTraversal(
    graph: IGraph<T>,
    currentNode: IGraphNode<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    connectedComponents: MutableList<IGraphNode<T>>
) {
    visitedNodeSet.add(currentNode)
    connectedComponents.add(currentNode)
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            doDFSTraversal(graph, node, visitedNodeSet, connectedComponents)
        }
    }
}

















