package algorithms.dynamic

// https://leetcode.com/problems/climbing-stairs/submissions/1389566622/

fun main() {
    println(climbStairs(4, mutableMapOf()))
    println(climbStairs(5, mutableMapOf()))
}

private fun climbStairs(n: Int, hashMap: MutableMap<Int, Int>): Int {
    if (n == 1 || n == 2) {
        return n
    }
    if (hashMap[n] != null) {
        return hashMap[n]!!
    }
    val result = climbStairs(n - 1, hashMap) + climbStairs(n - 2, hashMap)
    hashMap[n] = result
    return result
}