package algorithms.stack

// https://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/
// https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
// https://leetcode.com/problems/min-stack/description/

class GetMinStack(private val stackSize: Int = 20) {

    class TopNode<T>(value: T) {
        var value: T? = value
        var previousNode: TopNode<T>? = null
    }

    private var minTopNode: TopNode<Int>? = null
    private var topNode: TopNode<Int>? = null
    private var size = 0

    fun isEmpty() = size == 0

    fun push(value: Int) {
        if (size >= stackSize) {
            return
        }
        val node = TopNode(value)
        val temp: TopNode<Int>?
        if (!isEmpty()) {
            node.previousNode = topNode
            temp = if (minTopNode?.value!! < value) {
                TopNode(minTopNode?.value!!)
            } else {
                TopNode(value)
            }
            temp.previousNode = minTopNode
        } else {
            temp = TopNode(value)
        }
        minTopNode = temp
        topNode = node
        size++
    }

    fun push(vararg elements: Int) {
        for (i in elements) {
            push(i)
        }
    }

    fun pop(): Int? {
        if (isEmpty()) {
            return null
        }
        minTopNode = minTopNode?.previousNode
        val tempNode = topNode
        topNode = topNode?.previousNode
        size--
        return tempNode?.value
    }
}