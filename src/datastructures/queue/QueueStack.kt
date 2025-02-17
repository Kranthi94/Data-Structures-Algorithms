package datastructures.queue

import datastructures.stack.Stack
import datastructures.stack.StackInterface

class QueueStack<T>(size: Int = DEFAULT_SIZE): QueueInterface<T> {

    private val firstStack: StackInterface<T> = Stack(size)
    private val secondStack: StackInterface<T> = Stack(size)

    companion object {
        const val DEFAULT_SIZE = 100
    }

    override fun getSize(): Int {
        return firstStack.getSize()
    }

    override fun isEmpty(): Boolean {
        return firstStack.isEmpty()
    }

    override fun isFull(): Boolean {
        return firstStack.isFull()
    }

    override fun enQueue(value: T): Boolean {
        if (isFull()) {
            return false
        }
        return firstStack.push(value)
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
        while (!firstStack.isEmpty()) {
            secondStack.push(firstStack.pop())
        }
        val value = secondStack.pop()
        while (!secondStack.isEmpty()) {
            firstStack.push(secondStack.pop())
        }
        return value
    }

    override fun peek(): T {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        while (!firstStack.isEmpty()) {
            secondStack.push(firstStack.pop())
        }
        val value = secondStack.peek()
        while (!secondStack.isEmpty()) {
            firstStack.push(secondStack.pop())
        }
        return value
    }

    override fun rear(): T {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        return firstStack.peek()
    }

    override fun print() {
        firstStack.print()
    }
}