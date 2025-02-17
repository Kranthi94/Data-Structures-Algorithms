package datastructures.graphs

class UnDirectedGraph<T>: IGraph<T> {

    private val hashMap: HashMap<IGraphNode<T>, MutableList<IGraphEdge<T>>> = HashMap()
    private val nodesList: MutableList<IGraphNode<T>> = mutableListOf()

    override fun getAllNodes(): MutableSet<IGraphNode<T>> {
        return nodesList.toMutableSet()
    }

    override fun addEdge(source: T, destination: T) {
        val sourceNode = GraphNode(source)
        val destinationNode = GraphNode(destination)
        val graphEdge = GraphEdge(sourceNode, destinationNode)
        if (!hashMap.containsKey(sourceNode)) {
            hashMap[sourceNode] = arrayListOf()
        }
        if (!nodesList.contains(sourceNode)) {
            nodesList.add(sourceNode)
        }
        hashMap[sourceNode]!!.add(graphEdge)
        if (!hashMap.containsKey(destinationNode)) {
            hashMap[destinationNode] = arrayListOf()
        }
        if (!nodesList.contains(destinationNode)) {
            nodesList.add(destinationNode)
        }
        hashMap[destinationNode]!!.add(GraphEdge(destinationNode, sourceNode))
    }

    override fun getAdjacentEdges(value: T): MutableSet<IGraphEdge<T>> {
        val node = GraphNode(value)
        return if(hashMap.containsKey(node)) hashMap[node]!!.toMutableSet() else mutableSetOf()
    }

    override fun getAdjacentNodes(value: T): MutableSet<IGraphNode<T>> {
        val node = GraphNode(value)
        if (hashMap.containsKey(node)) {
            val adjacentEdges = hashMap[node]!!
            return adjacentEdges.map { it.getDestination() }.toMutableSet()
        }
        return mutableSetOf()
    }

    override fun getAllEdges(): MutableSet<IGraphEdge<T>> {
        val result = mutableSetOf<IGraphEdge<T>>()
        hashMap.values.forEach {
            result.addAll(it)
        }
        return result
    }

    override fun clearGraph() {
        hashMap.clear()
        nodesList.clear()
    }

    override fun removeNode(value: T) {
        val node = GraphNode(value)
        hashMap.remove(node)
        hashMap.keys.forEach { key ->
            val nodeEdges: MutableList<IGraphEdge<T>> = hashMap[key] ?: mutableListOf()
            val newNodeEdges: MutableList<IGraphEdge<T>> = mutableListOf()
            nodeEdges.forEach { edge ->
                if (edge.getDestination() != node) {
                    newNodeEdges.add(GraphEdge(edge.getSource(), edge.getDestination()))
                }
            }
            hashMap[key] = newNodeEdges
        }
    }

    override fun removeEdge(source: T, destination: T) {
        val sourceNode = GraphNode(source)
        val destinationNode = GraphNode(destination)
        val edgesFromSource = getAdjacentEdges(source)
        val newEdgesFromSource: MutableList<IGraphEdge<T>> = mutableListOf()
        edgesFromSource.forEach { edge ->
            if (edge.getDestination() != destinationNode) {
                newEdgesFromSource.add(GraphEdge(sourceNode, edge.getDestination()))
            }
        }
        val edgesFromDestination = getAdjacentEdges(destination)
        val newEdgesFromDestination: MutableList<IGraphEdge<T>> = mutableListOf()
        edgesFromDestination.forEach { edge ->
            if (edge.getSource() != sourceNode) {
                newEdgesFromDestination.add(GraphEdge(edge.getSource(), destinationNode))
            }
        }
        hashMap[sourceNode] = newEdgesFromSource
        hashMap[destinationNode] = newEdgesFromDestination
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