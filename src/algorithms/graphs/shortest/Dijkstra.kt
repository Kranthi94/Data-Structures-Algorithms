package algorithms.graphs.shortest

import datastructures.graphs.*
import utils.printMap

// Time Complexity: O(E + ELogV)

fun main() {
    val weightedDirectedGraph = WeightedDirectedGraph<Int>()
    weightedDirectedGraph.addEdge(0, 1, 2)
    weightedDirectedGraph.addEdge(0, 2, 4)
    weightedDirectedGraph.addEdge(1, 2, 1)
    weightedDirectedGraph.addEdge(1, 3, 7)
    weightedDirectedGraph.addEdge(2, 4, 3)
    weightedDirectedGraph.addEdge(4, 3, 2)
    weightedDirectedGraph.addEdge(3, 5, 1)
    weightedDirectedGraph.addEdge(4, 5, 5)
    findShortestDistance(weightedDirectedGraph).printMap()
}

private fun <T> findShortestDistance(graph: IWeightedGraph<T>): MutableMap<IGraphNode<T>, Int> {
    val distanceMap: MutableMap<IGraphNode<T>, Int> = mutableMapOf()
    val allNodes = graph.getAllNodes()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    findShortestDistance(graph, visitedNodeSet, null, allNodes.elementAt(0), distanceMap)
    return distanceMap
}

private fun <T> findShortestDistanceBFS(
    graph: IWeightedGraph<T>,
) {

}

private fun <T> findShortestDistance(
    graph: IWeightedGraph<T>,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    currentEdge: IWeightedGraphEdge<T>?,
    currentNode: IGraphNode<T>,
    distanceMap: MutableMap<IGraphNode<T>, Int>
) {
//    visitedNodeSet.add(currentNode)
    var distance = 0
    if (currentEdge != null) {
        val source = currentEdge.getSource()
        distance = distanceMap[source]!! + currentEdge.getWeight()
    }
    if (distanceMap.containsKey(currentNode)) {
        if (distance < distanceMap[currentNode]!!) {
            distanceMap[currentNode] = distance
        }
    } else {
        distanceMap[currentNode] = distance
    }
    val adjacentEdges = graph.getAdjacentEdges(currentNode.getValue())
    adjacentEdges.forEach { edge ->
        if (!visitedNodeSet.contains(edge.getDestination())) {
            findShortestDistance(graph, visitedNodeSet, edge, edge.getDestination(), distanceMap)
        }
    }
//    visitedNodeSet.remove(currentNode)
}