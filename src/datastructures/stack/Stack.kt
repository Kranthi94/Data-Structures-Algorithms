package datastructures.stack

import java.util.*

class Stack<T>(private val size: Int = DEFAULT_SIZE): StackInterface<T> {

    private var arrayList: ArrayList<T> = ArrayList(size)
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

    override fun push(value: T): Boolean {
        if (isFull()) {
            return false
        }
        arrayList.add(++pointerIndex, value)
        return true
    }

    override fun pushAll(vararg elements: T): Boolean {
        var allElementsInserted = true
        elements.forEach {
            allElementsInserted = allElementsInserted && push(it)
        }
        return allElementsInserted
    }

    override fun peek(): T {
        if (isEmpty()) {
            throw EmptyStackException()
        }
        return arrayList[pointerIndex]
    }

    override fun pop(): T {
        if (isEmpty()) {
            throw EmptyStackException()
        }
        return arrayList.removeAt(pointerIndex--)
    }

    override fun print() {
        val stringBuilder = StringBuilder("[ ")
        arrayList.indices.reversed().forEach {
            stringBuilder.append(arrayList[it])
            stringBuilder.append(" ")
        }
        stringBuilder.append("]")
        println(stringBuilder.toString())
    }
}