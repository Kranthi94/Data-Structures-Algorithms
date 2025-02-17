package algorithms.graphs.shortest

import datastructures.graphs.IGraphNode
import datastructures.graphs.IWeightedGraph
import datastructures.graphs.WeightedDirectedGraph
import utils.printMap

fun main() {
    val weightedDirectedGraph = WeightedDirectedGraph<Int>()
    weightedDirectedGraph.addEdge(0, 1, 2)
    weightedDirectedGraph.addEdge(0, 2, 4)
    weightedDirectedGraph.addEdge(1, 2, -4)
    weightedDirectedGraph.addEdge(2, 3, 2)
    weightedDirectedGraph.addEdge(3, 4, 4)
    weightedDirectedGraph.addEdge(4, 1, -10)
    findShortestDistance(weightedDirectedGraph, 0).printMap()
}

private fun <T> findShortestDistance(weightedGraph: IWeightedGraph<T>, source: T): MutableMap<IGraphNode<T>, Int> {
    val allNodes = weightedGraph.getAllNodes()
    val distanceMap: MutableMap<IGraphNode<T>, Int> = mutableMapOf()
    allNodes.forEach { node ->
        if (node.getValue() == source) {
            distanceMap[node] = 0
        } else {
            distanceMap[node] = Int.MAX_VALUE
        }
    }
    findShortestDistance(weightedGraph, distanceMap)
    return distanceMap
}

private fun <T> findShortestDistance(
    weightedGraph: IWeightedGraph<T>,
    distanceMap: MutableMap<IGraphNode<T>, Int>
) {
    val allNodes = weightedGraph.getAllNodes()
    val allEdges = weightedGraph.getAllEdges()
    repeat(allNodes.size - 1) {
        allEdges.forEach { edge ->
            val source = edge.getSource()
            val destination = edge.getDestination()
            if (distanceMap[source]!! + edge.getWeight() < distanceMap[destination]!!) {
                distanceMap[destination] = distanceMap[source]!! + edge.getWeight()
            }
        }
    }
    allEdges.forEach { edge ->
        val source = edge.getSource()
        val destination = edge.getDestination()
        if (distanceMap[source]!! + edge.getWeight() < distanceMap[destination]!!) {
            println("Negative Weight Cycle Exists")
        }
    }
}