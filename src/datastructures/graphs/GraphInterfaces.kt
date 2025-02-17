package datastructures.graphs

import utils.compareTo

interface IGraphNode<T> {
    fun getValue(): T
}

interface IGraphEdge<T> {
    fun getSource(): IGraphNode<T>
    fun getDestination(): IGraphNode<T>
}

interface IWeightedGraphEdge<T>: IGraphEdge<T> {
    fun getWeight(): Int
}

interface IGraph<T> {
    fun addEdge(source: T, destination: T)
    fun getAllNodes(): MutableSet<IGraphNode<T>>
    fun getAllEdges(): MutableSet<IGraphEdge<T>>
    fun getAdjacentEdges(value: T): MutableSet<IGraphEdge<T>>
    fun getAdjacentNodes(value: T): MutableSet<IGraphNode<T>>
    fun removeNode(value: T)
    fun removeEdge(source: T, destination: T)
    fun clearGraph()
    fun printGraph()
}

interface IWeightedGraph<T> {
    fun addEdge(source: T, destination: T, weight: Int)
    fun getAllNodes(): MutableSet<IGraphNode<T>>
    fun getAllEdges(): MutableSet<IWeightedGraphEdge<T>>
    fun getAdjacentEdges(value: T): MutableSet<IWeightedGraphEdge<T>>
    fun getAdjacentNodes(value: T): MutableSet<IGraphNode<T>>
    fun removeNode(value: T)
    fun removeEdge(source: T, destination: T)
    fun clearGraph()
    fun printGraph()
}

class GraphEdge<T>(
    private val source: IGraphNode<T>,
    private val destination: IGraphNode<T>
) : IGraphEdge<T> {

    override fun getSource(): IGraphNode<T> {
        return source
    }

    override fun getDestination(): IGraphNode<T> {
        return destination
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is GraphEdge<*> && this.source == other.source && this.destination == other.destination
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + destination.hashCode()
        return result
    }

    override fun toString(): String {
        return "${source.getValue()} -> ${destination.getValue()}"
    }
}

class WeightedGraphEdge<T>(
    private val source: IGraphNode<T>,
    private val destination: IGraphNode<T>,
    private val weight: Int
) : IWeightedGraphEdge<T> {

    override fun getWeight(): Int {
        return weight
    }

    override fun getSource(): IGraphNode<T> {
        return source
    }

    override fun getDestination(): IGraphNode<T> {
        return destination
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is WeightedGraphEdge<*> && this.source == other.source && this.destination == other.destination && this.weight == other.weight
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + destination.hashCode()
        result = 31 * result + weight
        return result
    }

    override fun toString(): String {
        return "${source.getValue()} -> ${destination.getValue()} (W: $weight)"
    }
}

class GraphNode<T>(private val value: T): IGraphNode<T>, Comparable<IGraphNode<T>>  {

    override fun getValue(): T {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is GraphNode<*> && this.value == other.value
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "$value"
    }

    override fun compareTo(other: IGraphNode<T>): Int {
        return this.getValue().compareTo(other.getValue())
    }
}