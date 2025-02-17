package algorithms.recursion

fun main() {
    println(fib(2, IntArray(3) { -1 }))
    println(fib(3, IntArray(4) { -1 }))
    println(fib(4, IntArray(5) { -1 }))
}

private fun fib(n: Int): Int {
    if (n == 0 || n == 1) {
        return n
    }
    return fib(n - 1) + fib(n - 2)
}

private fun fib(n: Int, array: IntArray): Int {
    if (n == 0 || n == 1) {
        return n
    }
    if (array[n] != -1) {
        return array[n]
    }
    array[n] = fib(n - 1) + fib(n - 2)
    return array[n]
}