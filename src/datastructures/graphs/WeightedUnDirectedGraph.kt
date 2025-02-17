package datastructures.graphs

class WeightedUnDirectedGraph<T>: IWeightedGraph<T> {

    private val hashMap: HashMap<IGraphNode<T>, MutableList<IWeightedGraphEdge<T>>> = HashMap()
    private val nodesList: MutableList<IGraphNode<T>> = mutableListOf()

    override fun getAllNodes(): MutableSet<IGraphNode<T>> {
        return nodesList.toMutableSet()
    }

    override fun addEdge(source: T, destination: T, weight: Int) {
        val sourceNode = GraphNode(source)
        val destinationNode = GraphNode(destination)
        val weightedGraphEdge = WeightedGraphEdge(sourceNode, destinationNode, weight)
        if (!hashMap.containsKey(sourceNode)) {
            hashMap[sourceNode] = arrayListOf()
        }
        if (!nodesList.contains(sourceNode)) {
            nodesList.add(sourceNode)
        }
        hashMap[sourceNode]!!.add(weightedGraphEdge)
        if (!hashMap.containsKey(destinationNode)) {
            hashMap[destinationNode] = arrayListOf()
        }
        if (!nodesList.contains(destinationNode)) {
            nodesList.add(destinationNode)
        }
        hashMap[destinationNode]!!.add(WeightedGraphEdge(destinationNode, sourceNode, weight))
    }

    override fun getAdjacentEdges(value: T): MutableSet<IWeightedGraphEdge<T>> {
        val node = GraphNode(value)
        return if(hashMap.containsKey(node)) hashMap[node]!!.toMutableSet() else mutableSetOf()
    }

    override fun getAllEdges(): MutableSet<IWeightedGraphEdge<T>> {
        val result = mutableSetOf<IWeightedGraphEdge<T>>()
        hashMap.values.forEach {
            result.addAll(it)
        }
        return result
    }

    override fun getAdjacentNodes(value: T): MutableSet<IGraphNode<T>> {
        val node = GraphNode(value)
        if (hashMap.containsKey(node)) {
            val adjacentEdges = hashMap[node]!!
            return adjacentEdges.map { it.getDestination() }.toMutableSet()
        }
        return mutableSetOf()
    }

    override fun removeNode(value: T) {
        val node = GraphNode(value)
        hashMap.remove(node)
        hashMap.keys.forEach { key ->
            val nodeEdges: MutableList<IWeightedGraphEdge<T>> = hashMap[key] ?: mutableListOf()
            val newNodeEdges: MutableList<IWeightedGraphEdge<T>> = mutableListOf()
            nodeEdges.forEach { edge ->
                if (edge.getDestination() != node) {
                    newNodeEdges.add(WeightedGraphEdge(edge.getSource(), edge.getDestination(), edge.getWeight()))
                }
            }
            hashMap[key] = newNodeEdges
        }
    }

    override fun removeEdge(source: T, destination: T) {
        val sourceNode = GraphNode(source)
        val destinationNode = GraphNode(destination)
        val edgesFromSource = getAdjacentEdges(source)
        val newEdgesFromSource: MutableList<IWeightedGraphEdge<T>> = mutableListOf()
        edgesFromSource.forEach { edge ->
            if (edge.getDestination() != destinationNode) {
                newEdgesFromSource.add(WeightedGraphEdge(sourceNode, edge.getDestination(), edge.getWeight()))
            }
        }
        val edgesFromDestination = getAdjacentEdges(destination)
        val newEdgesFromDestination: MutableList<IWeightedGraphEdge<T>> = mutableListOf()
        edgesFromDestination.forEach { edge ->
            if (edge.getSource() != sourceNode) {
                newEdgesFromDestination.add(WeightedGraphEdge(edge.getSource(), destinationNode, edge.getWeight()))
            }
        }
        hashMap[sourceNode] = newEdgesFromSource
        hashMap[destinationNode] = newEdgesFromDestination
    }

    override fun clearGraph() {
        hashMap.clear()
        nodesList.clear()
    }

    override fun printGraph() {
        hashMap.keys.forEach { key ->
            val stringBuilder = StringBuilder("${key.getValue()} ===>  ")
            hashMap[key]?.forEach {
                stringBuilder.append(it)
                stringBuilder.append(" , ")
            }
            println(stringBuilder.toString())
        }
    }
}