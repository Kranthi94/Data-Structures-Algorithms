package algorithms.graphs.bridges

import datastructures.graphs.*
import utils.printList

// https://leetcode.com/problems/critical-connections-in-a-network/description/

fun main() {
    val unDirectedGraph = UnDirectedGraph<Int>()
    unDirectedGraph.addEdge(0, 1)
    unDirectedGraph.addEdge(0, 2)
    unDirectedGraph.addEdge(1, 2)
    unDirectedGraph.addEdge(0, 3)
    unDirectedGraph.addEdge(3, 4)
    unDirectedGraph.addEdge(3, 5)
    unDirectedGraph.addEdge(4, 5)
    findBridges(unDirectedGraph).printList()
}

private fun <T> findBridges(graph: IGraph<T>): List<IGraphEdge<T>> {
    val bridges: MutableList<IGraphEdge<T>> = mutableListOf()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val allNodes = graph.getAllNodes()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findBridges(
                graph,
                null,
                node,
                visitedNodeSet,
                mutableMapOf(),
                mutableMapOf(),
                bridges
            )
        }
    }
    return bridges
}

private var time = 0

private fun <T> findBridges(
    graph: IGraph<T>,
    parentNode: IGraphNode<T>?,
    currentNode: IGraphNode<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    distanceMap: MutableMap<IGraphNode<T>, Int>,
    lowestDistanceMap: MutableMap<IGraphNode<T>, Int>,
    bridges: MutableList<IGraphEdge<T>>
) {
    visitedNodeSet.add(currentNode)
    distanceMap[currentNode] = time
    lowestDistanceMap[currentNode] = time
    time += 1
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (parentNode != node) {
            if (!visitedNodeSet.contains(node)) {
                findBridges(graph, currentNode, node, visitedNodeSet, distanceMap, lowestDistanceMap, bridges)
                lowestDistanceMap[currentNode] = lowestDistanceMap[currentNode]!!.coerceAtMost(lowestDistanceMap[node]!!)
                if (distanceMap[currentNode]!! < lowestDistanceMap[node]!!) {
                    bridges.add(GraphEdge(currentNode, node))
                }
            } else {
                lowestDistanceMap[currentNode] = lowestDistanceMap[currentNode]!!.coerceAtMost(distanceMap[node]!!)
            }
        }
    }
}

