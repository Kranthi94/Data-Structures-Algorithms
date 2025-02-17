package algorithms.disjointsets

import datastructures.disjointsets.DisJointSets
import utils.printValue

fun main() {
    val inputs = arrayOf(
        mutableListOf(
            mutableListOf(0, 1),
            mutableListOf(1, 2),
            mutableListOf(2, 3),
            mutableListOf(3, 4),
        ),
        mutableListOf(
            mutableListOf(0, 1),
            mutableListOf(1, 2),
            mutableListOf(2, 3),
            mutableListOf(3, 4),
            mutableListOf(4, 0)
        ),
        mutableListOf(
            mutableListOf(0, 1),
            mutableListOf(1, 2),
            mutableListOf(2, 0),
            mutableListOf(4, 0)
        )
    )
    inputs.forEach {
        hasCycle(it).printValue()
    }
}

private fun hasCycle(edges: MutableList<MutableList<Int>>): Boolean {
    val disjointSets = DisJointSets<Int>()
    var hasCycle = false
    run {
        edges.forEach { edge ->
            val source = edge[0]
            val destination = edge[1]
            if (disjointSets.findParent(source) != disjointSets.findParent(destination)) {
                disjointSets.findUnion(source, destination)
            } else {
                hasCycle = true
                return@run
            }
        }
    }
    return hasCycle
}