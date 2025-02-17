package algorithms.graphs.bridges

import datastructures.graphs.*
import utils.printList

fun main() {
    val unDirectedGraph = UnDirectedGraph<Int>()
    unDirectedGraph.addEdge(0, 1)
    unDirectedGraph.addEdge(0, 2)
    unDirectedGraph.addEdge(1, 2)
    unDirectedGraph.addEdge(0, 3)
    unDirectedGraph.addEdge(3, 4)
    unDirectedGraph.addEdge(3, 5)
    unDirectedGraph.addEdge(4, 5)
    findArticulationPoints(unDirectedGraph).printList()
    unDirectedGraph.clearGraph()
    unDirectedGraph.addEdge(1, 2)
    unDirectedGraph.addEdge(1, 3)
    unDirectedGraph.addEdge(2, 6)
    unDirectedGraph.addEdge(6, 7)
    unDirectedGraph.addEdge(3, 4)
    unDirectedGraph.addEdge(3, 5)
    findArticulationPoints(unDirectedGraph).printList()
}

private fun <T> findArticulationPoints(graph: IGraph<T>): List<IGraphNode<T>> {
    val articulationPoints: MutableSet<IGraphNode<T>> = mutableSetOf()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val allNodes = graph.getAllNodes()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            findArticulationPoints(
                graph,
                null,
                node,
                visitedNodeSet,
                mutableMapOf(),
                mutableMapOf(),
                articulationPoints
            )
        }
    }
    return articulationPoints.toList()
}

private var time = 0

private fun <T> findArticulationPoints(
    graph: IGraph<T>,
    parentNode: IGraphNode<T>?,
    currentNode: IGraphNode<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    distanceMap: MutableMap<IGraphNode<T>, Int>,
    lowestDistanceMap: MutableMap<IGraphNode<T>, Int>,
    articulationPoints: MutableSet<IGraphNode<T>>
) {
    visitedNodeSet.add(currentNode)
    distanceMap[currentNode] = time
    lowestDistanceMap[currentNode] = time
    time += 1
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (parentNode != node) {
            if (!visitedNodeSet.contains(node)) {
                findArticulationPoints(graph, currentNode, node, visitedNodeSet, distanceMap, lowestDistanceMap, articulationPoints)
                lowestDistanceMap[currentNode] = lowestDistanceMap[currentNode]!!.coerceAtMost(lowestDistanceMap[node]!!)
                if (distanceMap[currentNode]!! < lowestDistanceMap[node]!!) {
                    articulationPoints.add(currentNode)
                    if (graph.getAdjacentNodes(node.getValue()).size > 1) {
                        articulationPoints.add(node)
                    }
                }
            } else {
                lowestDistanceMap[currentNode] = lowestDistanceMap[currentNode]!!.coerceAtMost(distanceMap[node]!!)
            }
        }
    }
}

