package algorithms.linkedlists.doubly

// https://www.geeksforgeeks.org/reverse-a-doubly-linked-list/

//fun main() {
//    val linkedList = DoublyLinkedList<Int>()
//    linkedList.push(1, 2, 3)
//    linkedList.printList(reverse(linkedList.getHeadNode()))
//}
//
//private fun <T> reverse(headNode: DoublyLinkedList.Node<T>?): DoublyLinkedList.Node<T>? {
//    var previousNode = headNode
//    var currentNode = headNode?.nextNode
//    while (previousNode != null && currentNode != null) {
//        val temp1 = DoublyLinkedList.Node(previousNode)
//        val temp2 = DoublyLinkedList.Node(currentNode)
//        previousNode.nextNode = temp1.previousNode
//        previousNode.previousNode = temp2
//        currentNode.previousNode = temp2.nextNode
//        currentNode.nextNode = previousNode
//        previousNode = previousNode.previousNode
//        currentNode = currentNode.previousNode
//    }
//    return previousNode
//}