package datastructures.queue

class Queue<T>(private val size: Int = DEFAULT_SIZE): QueueInterface<T> {

    private val arrayList: ArrayList<T> = ArrayList(size)
    private var pointerIndex: Int = -1

    companion object {
        const val DEFAULT_SIZE = 100
    }

    override fun getSize(): Int {
        return arrayList.size
    }

    override fun isEmpty(): Boolean {
        return arrayList.isEmpty()
    }

    override fun isFull(): Boolean {
        return arrayList.size == size
    }

    override fun enQueue(value: T): Boolean {
        if (isFull()) {
            return false
        }
        pointerIndex += 1
        arrayList.add(pointerIndex, value)
        return true
    }

    override fun enQueueAll(vararg elements: T): Boolean {
        var allElementsInserted = true
        elements.forEach {
            allElementsInserted = allElementsInserted && enQueue(it)
        }
        return allElementsInserted
    }

    override fun deQueue(): T {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        pointerIndex -= 1
        return arrayList.removeAt(0)
    }

    override fun peek(): T {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        return arrayList[0]
    }

    override fun rear(): T {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        return arrayList[pointerIndex]
    }

    override fun print() {
        val stringBuilder = StringBuilder("[")
        arrayList.indices.forEach {
            stringBuilder.append(" ${arrayList[it]} ")
        }
        stringBuilder.append("]")
        println(stringBuilder.toString())
    }
}