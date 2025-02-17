package datastructures.queue

interface QueueInterface<T> {
    fun getSize(): Int
    fun isEmpty(): Boolean
    fun isFull(): Boolean
    fun enQueue(value: T): Boolean
    fun enQueueAll(vararg elements: T): Boolean
    fun deQueue(): T
    fun peek(): T
    fun rear(): T
    fun print()
}