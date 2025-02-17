package algorithms.graphs.traversals

import datastructures.graphs.DirectedGraph
import datastructures.graphs.GraphNode
import datastructures.graphs.IGraph
import datastructures.graphs.IGraphNode
import utils.printList

// https://leetcode.com/problems/reconstruct-itinerary/description/

// Time Complexity : O(E)

fun main() {
    val tickets = arrayListOf(
        arrayListOf("JFK", "KUL"),
        arrayListOf("JFK", "NRT"),
        arrayListOf("NRT", "JFK")
    )
//    val tickets = arrayListOf(
//        arrayListOf("JFK", "SFO"),
//        arrayListOf("JFK", "ATL"),
//        arrayListOf("SFO", "ATL"),
//        arrayListOf("ATL", "JFK"),
//        arrayListOf("ATL", "SFO")
//    )
//    val tickets = arrayListOf(
//        arrayListOf("EZE","AXA"),
//        arrayListOf("TIA","ANU"),
//        arrayListOf("ANU","JFK"),
//        arrayListOf("JFK","ANU"),
//        arrayListOf("ANU","EZE"),
//        arrayListOf("TIA","ANU"),
//        arrayListOf("AXA","TIA"),
//        arrayListOf("TIA","JFK"),
//        arrayListOf("ANU","TIA"),
//        arrayListOf("JFK","TIA")
//    )
//    val tickets = arrayListOf(
//        arrayListOf("EZE","TIA"),
//        arrayListOf("EZE","AXA"),
//        arrayListOf("AUA","EZE"),
//        arrayListOf("EZE","JFK"),
//        arrayListOf("JFK","ANU"),
//        arrayListOf("JFK","ANU"),
//        arrayListOf("AXA","TIA"),
//        arrayListOf("JFK","AUA"),
//        arrayListOf("TIA","JFK"),
//        arrayListOf("ANU","EZE"),
//        arrayListOf("ANU","EZE"),
//        arrayListOf("TIA","AUA")
//    )
//    val tickets = arrayListOf(
//        arrayListOf("AXA", "EZE"),
//        arrayListOf("EZE", "AUA"),
//        arrayListOf("ADL", "JFK"),
//        arrayListOf("ADL", "TIA"),
//        arrayListOf("AUA", "AXA"),
//        arrayListOf("EZE", "TIA"),
//        arrayListOf("EZE", "TIA"),
//        arrayListOf("AXA", "EZE"),
//        arrayListOf("EZE", "ADL"),
//        arrayListOf("ANU", "EZE"),
//        arrayListOf("TIA", "EZE"),
//        arrayListOf("JFK", "ADL"),
//        arrayListOf("AUA", "JFK"),
//        arrayListOf("JFK", "EZE"),
//        arrayListOf("EZE", "ANU"),
//        arrayListOf("ADL", "AUA"),
//        arrayListOf("ANU", "AXA"),
//        arrayListOf("AXA", "ADL"),
//        arrayListOf("AUA", "JFK"),
//        arrayListOf("EZE", "ADL"),
//        arrayListOf("ANU", "TIA"),
//        arrayListOf("AUA", "JFK"),
//        arrayListOf("TIA", "JFK"),
//        arrayListOf("EZE", "AUA"),
//        arrayListOf("AXA", "EZE"),
//        arrayListOf("AUA", "ANU"),
//        arrayListOf("ADL", "AXA"),
//        arrayListOf("EZE", "ADL"),
//        arrayListOf("AUA", "ANU"),
//        arrayListOf("AXA", "EZE"),
//        arrayListOf("TIA", "AUA"),
//        arrayListOf("AXA", "EZE"),
//        arrayListOf("AUA", "SYD"),
//        arrayListOf("ADL", "JFK"),
//        arrayListOf("EZE", "AUA"),
//        arrayListOf("ADL", "ANU"),
//        arrayListOf("AUA", "TIA"),
//        arrayListOf("ADL", "EZE"),
//        arrayListOf("TIA", "JFK"),
//        arrayListOf("AXA", "ANU"),
//        arrayListOf("JFK", "AXA"),
//        arrayListOf("JFK", "ADL"),
//        arrayListOf("ADL", "EZE"),
//        arrayListOf("AXA", "TIA"),
//        arrayListOf("JFK", "AUA"),
//        arrayListOf("ADL", "EZE"),
//        arrayListOf("JFK", "ADL"),
//        arrayListOf("ADL", "AXA"),
//        arrayListOf("TIA", "AUA"),
//        arrayListOf("AXA", "JFK"),
//        arrayListOf("ADL", "AUA"),
//        arrayListOf("TIA", "JFK"),
//        arrayListOf("JFK", "ADL"),
//        arrayListOf("JFK", "ADL"),
//        arrayListOf("ANU", "AXA"),
//        arrayListOf("TIA", "AXA"),
//        arrayListOf("EZE", "JFK"),
//        arrayListOf("EZE", "AXA"),
//        arrayListOf("ADL", "TIA"),
//        arrayListOf("JFK", "AUA"),
//        arrayListOf("TIA", "EZE"),
//        arrayListOf("EZE", "ADL"),
//        arrayListOf("JFK", "ANU"),
//        arrayListOf("TIA", "AUA"),
//        arrayListOf("EZE", "ADL"),
//        arrayListOf("ADL", "JFK"),
//        arrayListOf("ANU", "AXA"),
//        arrayListOf("AUA", "AXA"),
//        arrayListOf("ANU", "EZE"),
//        arrayListOf("ADL", "AXA"),
//        arrayListOf("ANU", "AXA"),
//        arrayListOf("TIA", "ADL"),
//        arrayListOf("JFK", "ADL"),
//        arrayListOf("JFK", "TIA"),
//        arrayListOf("AUA", "ADL"),
//        arrayListOf("AUA", "TIA"),
//        arrayListOf("TIA", "JFK"),
//        arrayListOf("EZE", "JFK"),
//        arrayListOf("AUA", "ADL"),
//        arrayListOf("ADL", "AUA"),
//        arrayListOf("EZE", "ANU"),
//        arrayListOf("ADL", "ANU"),
//        arrayListOf("AUA", "AXA"),
//        arrayListOf("AXA", "TIA"),
//        arrayListOf("AXA", "TIA"),
//        arrayListOf("ADL", "AXA"),
//        arrayListOf("EZE", "AXA"),
//        arrayListOf("AXA", "JFK"),
//        arrayListOf("JFK", "AUA"),
//        arrayListOf("ANU", "ADL"),
//        arrayListOf("AXA", "TIA"),
//        arrayListOf("ANU", "AUA"),
//        arrayListOf("JFK", "EZE"),
//        arrayListOf("AXA", "ADL"),
//        arrayListOf("TIA", "EZE"),
//        arrayListOf("JFK", "AXA"),
//        arrayListOf("AXA", "ADL"),
//        arrayListOf("EZE", "AUA"),
//        arrayListOf("AXA", "ANU"),
//        arrayListOf("ADL", "EZE"),
//        arrayListOf("AUA", "EZE")
//    )
    reconstructItinerary(tickets, "JFK").printList()
}

private fun <T> reconstructItinerary(tickets: List<List<T>>, source: T): List<IGraphNode<T>> {
    val directedGraph = DirectedGraph<T>()
    tickets.forEach { ticket ->
        val sourceCity = ticket[0]
        val destinationCity = ticket[1]
        directedGraph.addEdge(sourceCity, destinationCity)
    }
    val path: MutableList<IGraphNode<T>> = mutableListOf()
    reconstructItinerary(directedGraph, GraphNode(source), path)
    return path
}

private fun <T> reconstructItinerary(
    graph: IGraph<T>,
    sourceNode: IGraphNode<T>,
    path: MutableList<IGraphNode<T>>
) {
    while (graph.getAdjacentEdges(sourceNode.getValue()).isNotEmpty()) {
        val sortedEdges = graph.getAdjacentEdges(sourceNode.getValue()).sortedBy { it.getDestination().toString() }.toMutableList()
        val edge = sortedEdges.elementAt(0)
        graph.removeEdge(edge.getSource().getValue(), edge.getDestination().getValue())
        reconstructItinerary(graph, edge.getDestination(), path)
    }
    path.add(0, sourceNode)
}