package algorithms.graphs.traversals

import datastructures.graphs.*
import datastructures.queue.Queue
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
    doGraphBFS(graph).printValue()
}

private fun <T> doGraphBFS(graph: IGraph<T>): String {
    val nodesList = graph.getAllNodes()
    val queue: Queue<IGraphNode<T>> = Queue(nodesList.size)
    val visitedNodeSet: MutableSet<IGraphNode<T>> = mutableSetOf()
    val stringBuilder = StringBuilder("BFS START -> ")
    nodesList.forEach { node ->
        if (!visitedNodeSet.contains(node)) {
            queue.enQueue(node)
            visitedNodeSet.add(node)
            while (!queue.isEmpty()) {
                val dequeNode = queue.deQueue()
                stringBuilder.append("${dequeNode.getValue()} -> ")
                graph.getAdjacentNodes(dequeNode.getValue()).forEach { adjacentNode ->
                    if (!visitedNodeSet.contains(adjacentNode)) {
                        queue.enQueue(adjacentNode)
                        visitedNodeSet.add(adjacentNode)
                    }
                }
            }
        }
    }
    stringBuilder.append("END")
    return stringBuilder.toString()
}