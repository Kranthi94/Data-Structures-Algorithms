package algorithms.disjointsets

import datastructures.disjointsets.DisJointSets

fun main() {
    val disjointSets = DisJointSets<Int>()
    disjointSets.findUnion(1, 2)
    disjointSets.findUnion(1, 3)
    disjointSets.findUnion(7, 8)
    disjointSets.findUnion(4, 5)
    disjointSets.findUnion(4, 6)
    disjointSets.print()
}

