package datastructures.linkedlists

fun main() {
    val list = SingleLinkedList<Int>()
    list.insertAtStart(2)
    list.insertAtEnd(4)
    list.insertAfter(4, 3)
    list.insertAfter(4, 5)
    list.insertAtEnd(6)
    list.insertAtIndex(3, 8)
    list.insertAtIndex(3, 0)
    list.insertAtIndex(7, 10)
    list.print()
}

class SingleLinkedList<T>: ISingleLinkedList<T, SingleLinkedList.Node<T>> {

    class Node<T> (var value: T) {
        var nextNode: Node<T>? = null
    }

    private var headNode: Node<T>? = null
    private var tailNode: Node<T>? = null
    private var size = 0

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun getHeadNode(): Node<T>? {
        return headNode
    }

    override fun getTailNode(): Node<T>? {
        return tailNode
    }

    override fun setHeadNode(node: Node<T>?) {
        this.headNode = node
    }

    override fun setTailNode(node: Node<T>?) {
        this.tailNode = node
    }

    override fun getPreviousNode(target: T): Node<T>? {
        if (isEmpty()) {
            return null
        }
        var currentNode = headNode
        var previousNode: Node<T>? = null
        abc@ while (currentNode != null) {
            if (currentNode.value == target) {
                break@abc
            }
            previousNode = currentNode
            currentNode = currentNode.nextNode
        }
        return if (currentNode != null) previousNode else null
    }

    override fun getNode(target: T): Node<T>? {
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
        if(isEmpty()) {
            this.headNode = node
            this.tailNode = node
        } else {
            node.nextNode = headNode
            setHeadNode(node)
        }
        size += 1
    }

    override fun insertBefore(target: T, value: T) {
        if (isEmpty()) {
            return
        }
        val previousNode = getPreviousNode(target)
        if (previousNode == null) {
            insertAtStart(value)
            return
        }
        val node = Node(value)
        node.nextNode = previousNode.nextNode
        previousNode.nextNode = node
        size += 1
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
            val node = Node(value)
            node.nextNode = targetNode.nextNode
            targetNode.nextNode = node
            size += 1
        }
    }

    override fun insertAtEnd(value: T) {
        if (isEmpty()) {
            insertAtStart(value)
            return
        }
        tailNode?.let {
            val node= Node(value)
            node.nextNode = it.nextNode
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
            previousNode.nextNode = node
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
            setHeadNode(nextNode)
        }
        size -= 1
        return headNode!!.value
    }

    override fun deleteAtEnd(): T? {
        if (isEmpty()) {
            return null
        }
        val previousNode = getPreviousNode(tailNode!!.value)
        if (previousNode == null) {
            setHeadNode(null)
            setTailNode(null)
        } else {
            previousNode.nextNode = null
            setTailNode(previousNode)
        }
        size -= 1
        return headNode!!.value
    }

    override fun delete(target: T) {
        if (isEmpty()) {
            return
        }
        val previousNode = getPreviousNode(target)
        val targetNode = getNode(target)
        targetNode?.let {
            if (previousNode == null) {
                deleteAtStart()
                return
            }
            previousNode.nextNode = targetNode.nextNode
            targetNode.nextNode = null
            size -= 1
        }
    }

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
}