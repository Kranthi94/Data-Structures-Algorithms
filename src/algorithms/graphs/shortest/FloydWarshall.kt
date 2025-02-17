package algorithms.graphs.shortest

import datastructures.graphs.IGraphNode
import datastructures.graphs.IWeightedGraph
import datastructures.graphs.WeightedDirectedGraph
import utils.printMatrix

// Floyd Warshall Algorithm is used to find the all pairs shortest paths
// Time Complexity : O (V^3)

fun main() {
    val weightedDirectedGraph = WeightedDirectedGraph<Int>()
    weightedDirectedGraph.addEdge(0, 1, 3)
    weightedDirectedGraph.addEdge(1, 0, 8)
    weightedDirectedGraph.addEdge(0, 3, 7)
    weightedDirectedGraph.addEdge(3, 0, 2)
    weightedDirectedGraph.addEdge(1, 2, 2)
    weightedDirectedGraph.addEdge(2, 3, 1)
    weightedDirectedGraph.addEdge(2, 0, 5)
    findAllPairsShortestPaths(weightedDirectedGraph).printMatrix()
}

private fun findAllPairsShortestPaths(graph: IWeightedGraph<Int>): Array<Array<Int>> {
    val allNodes = graph.getAllNodes()
    val initialWeights: Array<Array<Int>> = Array(allNodes.size) { row ->
        Array(allNodes.size) { column ->
            if (row == column) {
                0
            } else {
                1000
            }
        }
    }
    graph.getAllEdges().forEach { edge ->
        val source = edge.getSource()
        val destination = edge.getDestination()
        val weight = edge.getWeight()
        initialWeights[source.getValue()][destination.getValue()] = weight
    }
    return findAllPairsShortestPaths(allNodes, initialWeights)
}

private fun findAllPairsShortestPaths(allNodes: Set<IGraphNode<Int>>, initialWeights: Array<Array<Int>>): Array<Array<Int>> {
    allNodes.forEach { node ->
        for (row in initialWeights.indices) {
            for (column in initialWeights[row].indices) {
                 if (row == node.getValue() || column == node.getValue() || row == column) {
                    continue
                }
                initialWeights[row][column] = initialWeights[row][column].coerceAtMost(initialWeights[row][node.getValue()] + initialWeights[node.getValue()][column])
            }
        }
    }
    return initialWeights
}
