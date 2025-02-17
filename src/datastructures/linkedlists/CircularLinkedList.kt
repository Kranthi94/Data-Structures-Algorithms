package datastructures.linkedlists

fun main() {
    val list = CircularLinkedList<Int>()
    list.insertAtStart(1)
    list.insertAtEnd(2)
    list.insertAfter(1, 4)
    list.insertBefore(2, 5)
    list.print()
    list.insertAtIndex(0, 7)
//    list.insertAtIndex(4, 3)
//    list.insertAtIndex(8, 10)
    list.print()
}

class CircularLinkedList<T>: ICircularLinkedList<T, CircularLinkedList.Node<T>> {
    
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
        do {
            if (node!!.value == target) {
                return node
            }
            node = node.nextNode
        }
        while (node != headNode)
        return null
    }

    override fun insertAtStart(value: T) {
        val node = Node(value)
        if (isEmpty()) {
            setHeadNode(node)
            setTailNode(node)
        } else {
            node.nextNode = headNode
            node.previousNode = tailNode
            headNode!!.previousNode = node
            tailNode!!.nextNode = node
            setHeadNode(node)
        }
        size += 1
    }

    override fun insertBefore(target: T, value: T) {
        if (isEmpty()) {
            return
        }
        val targetNode = getNode(target)
        if (targetNode == headNode) {
            insertAtStart(value)
            return
        }
        targetNode?.let {
            val previousNode = targetNode.previousNode
            if (previousNode == tailNode) {
                insertAtStart(value)
                return
            }
            val node = Node(value)
            node.nextNode = targetNode
            node.previousNode = previousNode
            previousNode!!.nextNode = node
            targetNode.previousNode = node
            size += 1
        }
    }

    override fun insertAfter(target: T, value: T) {
        if (isEmpty()) {
            return
        }
        val targetNode = getNode(target)
        if (targetNode == tailNode) {
            insertAtEnd(value)
            return
        }
        targetNode?.let {
            val nextNode = targetNode.nextNode
            if (nextNode == headNode) {
                insertAtStart(value)
                return
            }
            val node = Node(value)
            node.previousNode = targetNode
            node.nextNode = nextNode
            targetNode.nextNode = node
            nextNode!!.previousNode = node
            size += 1
        }
    }

    override fun insertAtEnd(value: T) {
        val node = Node(value)
        if (isEmpty()) {
            setHeadNode(node)
            setTailNode(node)
        } else {
            node.nextNode = headNode
            node.previousNode = tailNode
            headNode!!.previousNode = node
            tailNode!!.nextNode = node
            setTailNode(node)
        }
        size += 1
    }

    override fun insertAtIndex(index: Int, value: T) {
        if (index !in 0 until size) {
            return
        }
//        insertAtIndex(tailNode, headNode, index, value)
        val result = insertAtIndex(headNode, index, value)
        setHeadNode(result)
        setTailNode(result.previousNode)
    }

    private fun insertAtIndex(previousNode: Node<T>?, nextNode: Node<T>?, index: Int, value: T) {
        if (index == 0) {
            if (previousNode == tailNode && nextNode == headNode) {
                insertAtStart(value)
                return
            }
            val node = Node(value)
            node.nextNode = nextNode
            node.previousNode = previousNode
            previousNode!!.nextNode = node
            nextNode!!.previousNode = node
            size += 1
            return
        }
        return insertAtIndex(nextNode, nextNode?.nextNode, index - 1, value)
    }

    private fun insertAtIndex(node: Node<T>?, index: Int, value: T): Node<T> {
        if (index == 0) {
            val newNode = Node(value)
            if (node == null) {
                setHeadNode(newNode)
                setTailNode(newNode)
            } else {
                newNode.nextNode = node
                newNode.previousNode = node.previousNode
                node.previousNode?.nextNode = newNode
                node.previousNode = newNode
            }
            size += 1
            return newNode
        }
        val resultNode = insertAtIndex(node!!.nextNode, index - 1, value)
        node.nextNode = resultNode
        resultNode.previousNode = node
        return node
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
            nextNode.previousNode = tailNode
            tailNode!!.nextNode = nextNode
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
            setHeadNode(null)
            setTailNode(null)
        } else {
            previousNode.nextNode = headNode
            headNode!!.previousNode = previousNode
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
        if (targetNode == headNode) {
            deleteAtStart()
            return
        }
        if (targetNode == tailNode) {
            deleteAtEnd()
            return
        }
        targetNode?.let {
            val previousNode = it.previousNode
            val nextNode = it.nextNode
            previousNode!!.nextNode = nextNode
            nextNode!!.previousNode = previousNode
            size -= 1
        }
    }

    override fun print() {
        val stringBuilder = StringBuilder("[ ")
        if (!isEmpty()) {
            var node = headNode
            do {
                stringBuilder.append(node!!.value)
                stringBuilder.append(" ")
                node = node.nextNode
            } while (node != headNode)
        }
        stringBuilder.append("]")
        println(stringBuilder.toString())
    }
}