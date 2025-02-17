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

private fun <T> findLargestComponentBFS(graph: IGraph<T>): List<IGraphNode<T>> {
    val allNodes = graph.getAllNodes()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val result: MutableList<IGraphNode<T>> = mutableListOf()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            val list: MutableList<IGraphNode<T>> = mutableListOf()
            findLargestComponentBFS(graph, node, visitedNodeSet, list)
            if (result.size < list.size) {
                result.clear()
                result.addAll(list)
            }
        }
    }
    return result
}

private fun <T> findLargestComponentBFS(graph: IGraph<T>, currentNode: IGraphNode<T>, visitedNodeSet: MutableSet<IGraphNode<T>>, nodeList: MutableList<IGraphNode<T>>) {
    visitedNodeSet.add(currentNode)
    nodeList.add(currentNode)
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findLargestComponentBFS(graph, node, visitedNodeSet, nodeList)
        }
    }
}