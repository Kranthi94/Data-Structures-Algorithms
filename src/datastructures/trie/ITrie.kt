package datastructures.trie

interface ITrie {
    fun getRootNode(): TrieNode
    fun insertWord(word: String)
    fun searchWord(word: String): Boolean
    fun deleteWord(word: String): Boolean
    fun getCountWithPrefix(prefix: String): Int
}