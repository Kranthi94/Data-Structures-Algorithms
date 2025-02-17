package datastructures.disjointsets

interface IDisjointSet<T> {
    fun findRank(value: T): Int
    fun findParent(value: T): T
    fun findUnion(value1: T, value2: T)
    fun print(): String
}