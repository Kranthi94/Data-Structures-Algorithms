package algorithms.graphs.topological

import datastructures.graphs.DirectedGraph
import datastructures.graphs.IGraph
import datastructures.graphs.IGraphNode
import datastructures.queue.Queue
import datastructures.stack.Stack
import utils.printList

// https://leetcode.com/problems/course-schedule-ii/description/

// Time Complexity : O(V + E)

fun main() {
    val directedGraph = DirectedGraph<Int>()
    directedGraph.addEdge(5, 0)
    directedGraph.addEdge(5, 2)
    directedGraph.addEdge(4, 0)
    directedGraph.addEdge(4, 1)
    directedGraph.addEdge(2, 3)
    directedGraph.addEdge(3, 1)
    topologicalSort1(directedGraph).print()
    topologicalSort2(directedGraph).printList()
}

private fun <T> topologicalSort1(graph: IGraph<T>): Stack<IGraphNode<T>> {
    val allNodes = graph.getAllNodes()
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val stack: Stack<IGraphNode<T>> = Stack()
    allNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            topologicalSort1(graph, node, visitedNodeSet, stack)
        }
    }
    return stack
}

private fun <T> topologicalSort1(graph: IGraph<T>, currentNode: IGraphNode<T>, visitedNodeSet: MutableSet<IGraphNode<T>>, stack: Stack<IGraphNode<T>>) {
    visitedNodeSet.add(currentNode)
    val adjacentNodes = graph.getAdjacentNodes(currentNode.getValue())
    adjacentNodes.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            topologicalSort1(graph, node, visitedNodeSet, stack)
        }
    }
    stack.push(currentNode)
}

// Find out the In-Degree Array
// BFS
// Track Count -> To Check whether the graph is DAG or not
// Kahn's Algorithm
private fun <T> topologicalSort2(graph: IGraph<T>): List<IGraphNode<T>> {
    val inDegreeMap: MutableMap<IGraphNode<T>, Int> = mutableMapOf()
    graph.getAllNodes().forEach { node ->
        inDegreeMap.getOrPut(node) { 0 }
        val allEdges = graph.getAdjacentEdges(node.getValue())
        allEdges.forEach { edge ->
            inDegreeMap[edge.getDestination()] = inDegreeMap.getOrPut(edge.getDestination()) { 0 } + 1
        }
    }
    return topologicalSort2(graph, inDegreeMap)
}

private fun <T> topologicalSort2(graph: IGraph<T>, inDegreeMap: MutableMap<IGraphNode<T>, Int>): List<IGraphNode<T>> {
    var count = 0
    val path: MutableList<IGraphNode<T>> = mutableListOf()
    val queue: Queue<IGraphNode<T>> = Queue()
    inDegreeMap.forEach { (key, value) ->
        if (value == 0) {
            queue.enQueue(key)
        }
    }
    while (!queue.isEmpty()) {
        val poppedNode = queue.deQueue()
        count += 1
        path.add(poppedNode)
        val adjacentNodes = graph.getAdjacentNodes(poppedNode.getValue())
        adjacentNodes.forEach { node ->
            inDegreeMap[node] = inDegreeMap[node]!! - 1
            if (inDegreeMap[node]!! == 0) {
                queue.enQueue(node)
            }
        }
    }
    return if (count == inDegreeMap.size) path else mutableListOf()
}