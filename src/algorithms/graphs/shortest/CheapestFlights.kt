package algorithms.graphs.shortest

import datastructures.graphs.GraphNode
import datastructures.graphs.IGraphNode
import datastructures.graphs.IWeightedGraph
import datastructures.graphs.WeightedDirectedGraph
import utils.printPair
import java.util.PriorityQueue

// https://leetcode.com/problems/cheapest-flights-within-k-stops/description/

fun main() {
    val flights = arrayOf(
        arrayOf(3, 4, 4),
        arrayOf(2, 5, 6),
        arrayOf(4, 7, 10),
        arrayOf(9, 6, 5),
        arrayOf(7, 4, 4),
        arrayOf(6, 2, 10),
        arrayOf(6, 8, 6),
        arrayOf(7, 9, 4),
        arrayOf(1, 5, 4),
        arrayOf(1, 0, 4),
        arrayOf(9, 7, 3),
        arrayOf(7, 0, 5),
        arrayOf(6, 5, 8),
        arrayOf(1, 7, 6),
        arrayOf(4, 0, 9),
        arrayOf(5, 9, 1),
        arrayOf(8, 7, 3),
        arrayOf(1, 2, 6),
        arrayOf(4, 1, 5),
        arrayOf(5, 2, 4),
        arrayOf(1, 9, 1),
        arrayOf(7, 8, 10),
        arrayOf(0, 4, 2),
        arrayOf(7, 2, 8)
    )
    cheapestFlights(flights, 6, 0, 7).printPair()
}

private fun cheapestFlights(flights: Array<Array<Int>>, source: Int, destination: Int, stops: Int): Pair<Int, Int> {
    val weightedDirectedGraph = WeightedDirectedGraph<Int>()
    flights.forEach { arr ->
        weightedDirectedGraph.addEdge(arr[0], arr[1], arr[2])
    }
    val totalCostList = MutableList(1) { Int.MAX_VALUE }
    findPath(weightedDirectedGraph, GraphNode(source), GraphNode(destination), stops, mutableSetOf(), 0, totalCostList)
    val minCost1 = if (totalCostList.isNotEmpty() && totalCostList[0] != Int.MAX_VALUE) totalCostList.elementAt(0) else -1
    val minCost2 = findPath(weightedDirectedGraph, GraphNode(source), GraphNode(destination), stops)
    return Pair(minCost1, minCost2)
}

private fun findPath(
    graph: IWeightedGraph<Int>,
    currentNode: IGraphNode<Int>,
    destinationNode: IGraphNode<Int>,
    stops: Int,
    visitedNodeSet: MutableSet<IGraphNode<Int>>,
    cost: Int,
    result: MutableList<Int>
) {
    if (currentNode == destinationNode) {
        if (cost < result[0]) {
            result[0] = cost
        }
        return
    }
    if (stops < 0) {
        return
    }
    visitedNodeSet.add(currentNode)
    val adjacentNodes = graph.getAdjacentEdges(currentNode.getValue())
    adjacentNodes.forEach { edge ->
        if (!visitedNodeSet.contains(edge.getDestination()) && cost + edge.getWeight() < result.elementAt(0)) {
            findPath(graph, edge.getDestination(), destinationNode, stops - 1, visitedNodeSet, cost + edge.getWeight(), result)
        }
    }
    visitedNodeSet.remove(currentNode)
}

class NodeCostData(
    val node: IGraphNode<Int>,
    val cost: Int,
    val stops: Int
): Comparable<NodeCostData> {
    override fun compareTo(other: NodeCostData): Int {
        return this.cost - other.cost
    }
}

private fun findPath(
    graph: IWeightedGraph<Int>,
    sourceNode: IGraphNode<Int>,
    destinationNode: IGraphNode<Int>,
    totalStops: Int,
): Int {
    val minStopsFromSource: MutableMap<IGraphNode<Int>, Int> = mutableMapOf()
    val priorityQueue: PriorityQueue<NodeCostData> = PriorityQueue()
    priorityQueue.add(NodeCostData(sourceNode, 0, 0))
    while (priorityQueue.isNotEmpty()) {
        val poppedNodeCostData = priorityQueue.remove()
        val node = poppedNodeCostData.node
        val cost = poppedNodeCostData.cost
        val stops = poppedNodeCostData.stops
        if (node == destinationNode) {
            return cost
        }
        if (stops > totalStops || (minStopsFromSource[node] != null && stops > minStopsFromSource[node]!!)) {
            continue
        }
        minStopsFromSource[node] = stops
        val adjacentEdges = graph.getAdjacentEdges(node.getValue())
        adjacentEdges.forEach { edge ->
            priorityQueue.add(NodeCostData(edge.getDestination(), cost + edge.getWeight(), stops + 1))
        }
    }
    return -1
}