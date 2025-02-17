package algorithms.trie

import datastructures.trie.Trie
import utils.printValue

fun main() {
    val trie = Trie()
//    trie.insertWord("abc")
//    trie.deleteWord("abc").printValue()
    trie.insertWord("ad")
    trie.insertWord("ade")
    trie.searchWord("ade").printValue()
    trie.searchWord("ad").printValue()
    trie.deleteWord("ad").printValue()
    trie.searchWord("ad").printValue()
//    trie.insertWord("ade")
//    trie.getCountWithPrefix("ab").printValue()
//    trie.getCountWithPrefix("ad").printValue()
//    trie.searchWord("abc").printValue()
//    trie.searchWord("ade").printValue()
//    trie.searchWord("ad").printValue()
//    trie.deleteWord("ad").printValue()
}
