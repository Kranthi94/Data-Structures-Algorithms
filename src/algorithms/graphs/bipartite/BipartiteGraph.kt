package algorithms.graphs.bipartite

import datastructures.graphs.IGraph
import datastructures.graphs.IGraphNode
import datastructures.graphs.UnDirectedGraph
import datastructures.queue.Queue
import utils.printValue

// https://leetcode.com/problems/possible-bipartition/description/

fun main() {
    val adjacencyList = arrayOf(
        arrayOf(1, 2),
        arrayOf(1, 3),
        arrayOf(2, 3)
    )
    possibleBiPartition(adjacencyList).printValue()
}

private fun <T> possibleBiPartition(dislikes: Array<Array<T>>): Boolean {
    val unDirectedGraph = UnDirectedGraph<T>()
    dislikes.forEach { arr ->
        unDirectedGraph.addEdge(arr[0], arr[1])
    }
    val allNodes = unDirectedGraph.getAllNodes()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            if (!possibleBiPartition(unDirectedGraph, node, 0, visitedNodeSet, mutableMapOf())) {
                return false
            }
        }
    }
    return true
}

private fun <T> possibleBiPartition(
    graph: IGraph<T>,
    currentNode: IGraphNode<T>,
    currentColor: Int,
    visitedNodeSet: MutableSet<IGraphNode<T>>,
    nodeColorMap: MutableMap<IGraphNode<T>, Int>
): Boolean {
    val queue: Queue<IGraphNode<T>> = Queue()
    queue.enQueue(currentNode)
    visitedNodeSet.add(currentNode)
    nodeColorMap[currentNode] = currentColor
    while (!queue.isEmpty()) {
        val poppedNode = queue.deQueue()
        val adjacentNodes = graph.getAdjacentNodes(poppedNode.getValue())
        adjacentNodes.forEach { node ->
            if (visitedNodeSet.contains(node)) {
                if (nodeColorMap[node] == nodeColorMap[poppedNode]) {
                    return false
                }
            } else {
                queue.enQueue(node)
                visitedNodeSet.add(node)
                nodeColorMap[node] = 1 - nodeColorMap[poppedNode]!!
            }
        }
    }
    return true
}