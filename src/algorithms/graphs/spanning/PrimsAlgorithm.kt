package algorithms.graphs.spanning

import datastructures.graphs.*
import utils.printValue
import utils.printList
import java.util.*

fun main() {
    val weightedUnDirectedGraph = WeightedUnDirectedGraph<Int>()
    weightedUnDirectedGraph.addEdge(0, 1, 10)
    weightedUnDirectedGraph.addEdge(0, 2, 15)
    weightedUnDirectedGraph.addEdge(0, 3, 30)
    weightedUnDirectedGraph.addEdge(1, 3, 40)
    weightedUnDirectedGraph.addEdge(2, 3, 50)
    primsAlgorithmMSTCost(weightedUnDirectedGraph).printValue()
    primsAlgorithmMSTEdges(weightedUnDirectedGraph).printList()
}

private class NodeCost<T>(
    val parentNode: IGraphNode<T>?,
    val currentNode: IGraphNode<T>,
    val cost: Int
): Comparable<NodeCost<T>> {

    override fun compareTo(other: NodeCost<T>): Int {
        return this.cost - other.cost
    }
}

private fun <T> primsAlgorithmMSTCost(graph: IWeightedGraph<T>): Int {
    val edgesList = primsAlgorithmMSTEdges(graph)
    var minimumSpanningCost = 0
    edgesList.forEach { edge ->
        minimumSpanningCost += edge.getWeight()
    }
    return minimumSpanningCost
}

private fun <T> primsAlgorithmMSTEdges(graph: IWeightedGraph<T>): List<IWeightedGraphEdge<T>> {
    val priorityQueue: PriorityQueue<NodeCost<T>> = PriorityQueue()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val allNodes = graph.getAllNodes()
    priorityQueue.add(NodeCost(null, allNodes.elementAt(0), 0))
    val edgesList: MutableSet<IWeightedGraphEdge<T>> = mutableSetOf()
    while (priorityQueue.isNotEmpty()) {
        val poppedNode = priorityQueue.remove()
        if (!visitedNodeSet.contains(poppedNode.currentNode)) {
            if (poppedNode.parentNode != null) {
                edgesList.add(WeightedGraphEdge(poppedNode.parentNode, poppedNode.currentNode, poppedNode.cost))
            }
            visitedNodeSet.add(poppedNode.currentNode)
            val adjacentEdges = graph.getAdjacentEdges(poppedNode.currentNode.getValue())
            adjacentEdges.forEach { edge ->
                if (!visitedNodeSet.contains(edge.getDestination())) {
                    priorityQueue.add(NodeCost(poppedNode.currentNode, edge.getDestination(), edge.getWeight()))
                }
            }
        }
    }
    return edgesList.toList()
}