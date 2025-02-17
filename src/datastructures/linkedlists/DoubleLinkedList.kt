package datastructures.linkedlists

fun main() {
    val list = DoubleLinkedList<Int>()
    list.insertAtStart(2)
    list.insertAtStart(3)
    list.insertAtEnd(4)
    list.insertBefore(2, 5)
    list.insertAfter(2, 6)
    list.print()
    list.insertAtIndex(2, 0)
    list.insertAtIndex(5, 9)
    list.insertAtIndex(7, 10)
    list.print()
}

class DoubleLinkedList<T>: IDoubleLinkedList<T, DoubleLinkedList.Node<T>> {

    class Node<T>(var value: T) {
        var previousNode: Node<T>? = null
        var nextNode: Node<T>? = null
    }

    private var headNode: Node<T>? = null
    private var tailNode: Node<T>? = null
    private var size = 0

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun setHeadNode(node: Node<T>?) {
        this.headNode = node
    }

    override fun setTailNode(node: Node<T>?) {
        this.tailNode = node
    }

    override fun getHeadNode(): Node<T>? {
        return headNode
    }

    override fun getTailNode(): Node<T>? {
        return tailNode
    }

    override fun getNode(target: T): Node<T>? {
        if (isEmpty()) {
            return null
        }
        var node = headNode
        while (node != null) {
            if (node.value == target) {
                return node
            }
            node = node.nextNode
        }
        return null
    }

    override fun insertAtStart(value: T) {
        val node = Node(value)
        if (isEmpty()) {
            setHeadNode(node)
            setTailNode(node)
        } else {
            node.nextNode = headNode
            headNode!!.previousNode = node
            setHeadNode(node)
        }
        size += 1
    }

    override fun insertBefore(target: T, value: T) {
        if (isEmpty()) {
            return
        }
        val targetNode = getNode(target)
        targetNode?.let {
            val previousNode = targetNode.previousNode
            if (previousNode == null) {
                insertAtStart(value)
                return
            }
            val node = Node(value)
            node.nextNode = targetNode
            node.previousNode = previousNode
            previousNode.nextNode = node
            targetNode.previousNode = node
            size += 1
        }
    }

    override fun insertAfter(target: T, value: T) {
        if (isEmpty()) {
            return
        }
        val targetNode = getNode(target)
        targetNode?.let {
            val nextNode = targetNode.nextNode
            if (nextNode == null) {
                insertAtEnd(value)
                return
            }
            val node = Node(value)
            node.previousNode = targetNode
            node.nextNode = nextNode
            targetNode.nextNode = node
            nextNode.previousNode = node
            size += 1
        }
    }

    override fun insertAtEnd(value: T) {
        if (isEmpty()) {
            insertAtStart(value)
            return
        }
        tailNode?.let {
            val node = Node(value)
            node.previousNode = tailNode
            it.nextNode = node
            setTailNode(node)
            size += 1
        }
    }

    override fun insertAtIndex(index: Int, value: T) {
        if (index !in 0 until size) {
            return
        }
        insertAtIndex(null, headNode, index, value)
    }

    private fun insertAtIndex(previousNode: Node<T>?, nextNode: Node<T>?, index: Int, value: T) {
        if (index == 0) {
            if (previousNode == null) {
                insertAtStart(value)
                return
            }
            if (nextNode == null) {
                insertAtEnd(value)
                return
            }
            val node = Node(value)
            node.nextNode = nextNode
            node.previousNode = previousNode
            previousNode.nextNode = node
            nextNode.previousNode = node
            size += 1
            return
        }
        return insertAtIndex(nextNode, nextNode?.nextNode, index - 1, value)
    }

    override fun deleteAtStart(): T? {
        if (isEmpty()) {
            return null
        }
        val nextNode = headNode!!.nextNode
        if (nextNode == null) {
            setHeadNode(null)
            setTailNode(null)
        } else {
            nextNode.previousNode = null
            setHeadNode(nextNode)
        }
        size -= 1
        return headNode!!.value
    }

    override fun deleteAtEnd(): T? {
        if (isEmpty()) {
            return null
        }
        val previousNode = tailNode!!.previousNode
        if (previousNode == null) {
           deleteAtStart()
        } else {
            previousNode.nextNode = null
            setTailNode(previousNode)
        }
        size -= 1
        return tailNode!!.value
    }

    override fun delete(target: T) {
        if (isEmpty()) {
            return
        }
        val targetNode = getNode(target)
        targetNode?.let {
            val previousNode = it.previousNode
            val nextNode = it.nextNode
            if (previousNode == null && nextNode != null) {
                deleteAtStart()
            } else if (previousNode != null && nextNode == null) {
                deleteAtEnd()
            } else {
                previousNode!!.nextNode = nextNode
                nextNode!!.previousNode = previousNode
            }
            size -= 1
        }
    }

//    fun push(value: T) {
//        val node: Node<T> = Node(value)
//        if (isEmpty()) {
//            headNode = node
//        } else {
//            headNode?.previousNode = node
//            node.nextNode = headNode
//            headNode = node
//        }
//        size++
//    }
//
//    fun push(vararg elements: T) {
//        for (element in elements) {
//            push(element)
//        }
//    }
//
//    fun pushAfter(value: T , k: T) {
//        val currentNode = getNode(headNode, value)
//        currentNode?.let {
//            val newNode = Node(k)
//            val nextNode = currentNode.nextNode
//            currentNode.nextNode = newNode
//            newNode.previousNode = currentNode
//            newNode.nextNode = nextNode
//            nextNode?.previousNode = newNode
//            size++
//        }
//    }
//
//    fun pushBefore(value: T , k: T) {
//        val currentNode = getNode(headNode, value)
//        currentNode?.let {
//            val newNode = Node(k)
//            val previousNode = currentNode.previousNode
//            when (currentNode) {
//                headNode -> {
//                    headNode?.previousNode = newNode
//                    newNode.nextNode = headNode
//                    headNode = newNode
//                }
//                else -> {
//                    previousNode?.nextNode = newNode
//                    newNode.previousNode = previousNode
//                    newNode.nextNode = currentNode
//                    currentNode.previousNode = newNode
//                }
//            }
//            size++
//        }
//    }
//
//    fun pushEnd(value: T) {
//        val currentNode = getEndNode(headNode)
//        currentNode?.let {
//            val newNode = Node(value)
//            currentNode.nextNode = newNode
//            newNode.previousNode = currentNode
//            size++
//        }
//    }
//
//    fun deleteNode(value: T) {
//        val currentNode = getNode(headNode, value)
//        currentNode?.let {
//            when (currentNode) {
//                headNode -> {
//                    val nextNode = currentNode.nextNode
//                    nextNode?.previousNode = null
//                    headNode = nextNode
//                }
//                else -> {
//                    val previousNode = currentNode.previousNode
//                    val nextNode = currentNode.nextNode
//                    previousNode?.nextNode = nextNode
//                    nextNode?.previousNode = previousNode
//                }
//            }
//            size--
//        }
//    }
//
//    fun deleteEnd() {
//        val currentNode = getEndNode(headNode)
//        currentNode?.let {
//            currentNode.previousNode?.nextNode = null
//            size--
//        }
//    }

    override fun print() {
        val stringBuilder = StringBuilder("[ ")
        if (!isEmpty()) {
            var node = headNode
            while (node != null) {
                stringBuilder.append(node.value)
                stringBuilder.append(" ")
                node = node.nextNode
            }
        }
        stringBuilder.append("]")
        println(stringBuilder.toString())
    }

    override fun reverse(): ILinkedList<T, Node<T>>? {
        if (isEmpty()) {
            return null
        }
        val doublyLinkedList = DoubleLinkedList<T>()
        var startNode = headNode
        var endNode = tailNode
        var sValue: T? = endNode!!.value
        var eValue: T? = startNode!!.value
        var startIndex = 1
        var endIndex = size
        while (startIndex < endIndex) {
            if (doublyLinkedList.isEmpty()) {
                doublyLinkedList.insertAtStart(sValue!!)
                doublyLinkedList.insertAtEnd(eValue!!)
            } else {
                doublyLinkedList.insertAfter(sValue!!, endNode!!.value)
                doublyLinkedList.insertBefore(eValue!!, startNode!!.value)
            }
            sValue = endNode!!.value
            eValue = startNode!!.value
            startNode = startNode.nextNode
            endNode = endNode.previousNode
            startIndex += 1
            endIndex -= 1
        }
        if (startIndex == endIndex) {
            doublyLinkedList.insertAfter(sValue!!, endNode!!.value)
        }
        return doublyLinkedList
    }
}