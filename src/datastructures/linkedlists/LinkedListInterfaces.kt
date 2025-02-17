package datastructures.linkedlists

interface ILinkedList<T, N> {
    fun insertAtStart(value: T)
    fun insertBefore(target: T, value: T)
    fun insertAfter(target: T, value: T)
    fun insertAtEnd(value: T)
    fun insertAtIndex(index: Int, value: T)
    fun deleteAtStart(): T?
    fun deleteAtEnd(): T?
    fun delete(target: T)
    fun getHeadNode(): N?
    fun getTailNode(): N?
    fun setHeadNode(node: N?)
    fun setTailNode(node: N?)
    fun getNode(target: T): N?
    fun isEmpty(): Boolean
    fun print()
}

interface ISingleLinkedList<T, N> : ILinkedList<T, N> {
    fun getPreviousNode(target: T): N?
}

interface IDoubleLinkedList<T, N> : ILinkedList<T, N> {
    fun reverse(): ILinkedList<T, N>?
}

interface ICircularLinkedList<T, N> : ILinkedList<T, N> {

}