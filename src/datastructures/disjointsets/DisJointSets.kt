package datastructures.disjointsets

import utils.printMap

class DisJointSets<T> : IDisjointSet<T> {

    private val parentMap: MutableMap<T, T?> = mutableMapOf()
    private val rankMap: MutableMap<T, Int> = mutableMapOf()

    override fun findRank(value: T): Int {
        if (!rankMap.containsKey(value)) {
            return 0
        }
        return rankMap[value]!!
    }

    override fun findParent(value: T): T {
        if (parentMap[value] == null) {
            parentMap[value] = value
            return value
        }
        if (parentMap[value] != value) {
            parentMap[value] = findParent(parentMap[value]!!)
        }
        return parentMap[value]!!
    }

    override fun findUnion(value1: T, value2: T) {
        val parent1 = findParent(value1)
        val parent2 = findParent(value2)
        if (parent1 == parent2) {
            return
        }
        val rank1 = findRank(parent1)
        val rank2 = findRank(parent2)
        when {
            rank1 > rank2 -> {
                parentMap[value2] = parent1
            }

            rank1 < rank2 -> {
                parentMap[value1] = parent2
            }

            else -> {
                parentMap[value2] = parent1
                rankMap[parent1] = findRank(parent1) + 1
            }
        }
    }

    override fun print(): String {
        val map: MutableMap<T, MutableList<T>> = mutableMapOf()
        parentMap.forEach { (key, value) ->
            if (value != null) {
                if (map[value] == null) {
                    map[value] = mutableListOf()
                }
                map[value]!!.add(key)
            }
        }
        return map.printMap()
    }
}