package datastructures.stack

interface StackInterface<T> {
    fun getSize(): Int
    fun isEmpty(): Boolean
    fun isFull(): Boolean
    fun push(value: T): Boolean
    fun pushAll(vararg elements: T): Boolean
    fun peek(): T
    fun pop(): T
    fun print()
}