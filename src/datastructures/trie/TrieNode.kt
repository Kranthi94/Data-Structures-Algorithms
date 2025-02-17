package datastructures.trie

class TrieNode {
    var value: Char = '\u0000'
    var prefixCount: Int = 0
    var endsWithCount: Int = 0
    val nodes: Array<TrieNode?> = Array(26) { null }
}