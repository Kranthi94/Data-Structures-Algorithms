package algorithms.graphs.traversals

import datastructures.graphs.*
import datastructures.stack.Stack
import utils.printValue

fun main() {
    val graph = UnDirectedGraph<Int>()
    graph.addEdge(1, 2)
    graph.addEdge(1, 3)
    graph.addEdge(1, 4)
    graph.addEdge(2, 3)
    graph.addEdge(4, 5)
    graph.addEdge(2, 5)
    graph.addEdge(6, 7)
    doGraphDFS(graph).printValue()
}

private fun <T> doGraphDFS(graph: IGraph<T>): String {
    val nodesList = graph.getAllNodes()
    val stack: Stack<IGraphNode<T>> = Stack(nodesList.size)
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val stringBuilder = StringBuilder("DFS START -> ")
    nodesList.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            stack.push(node)
            visitedNodeSet.add(node)
            while (!stack.isEmpty()) {
                val dequeNode = stack.pop()
                stringBuilder.append("${dequeNode.getValue()} -> ")
                graph.getAdjacentNodes(dequeNode.getValue()).forEach { adjacentNode ->
                    if (!visitedNodeSet.contains(adjacentNode)) {
                        stack.push(adjacentNode)
                        visitedNodeSet.add(adjacentNode)
                    }
                }
            }
        }
    }
    stringBuilder.append("END")
    return stringBuilder.toString()
}