package datastructures.trie

class Trie: ITrie {
    private var rootNode = TrieNode()

    override fun getRootNode(): TrieNode {
        return rootNode
    }

    override fun insertWord(word: String) {
        insertWord(word, 0, rootNode)
    }

    private fun insertWord(word: String, currentIndex: Int, currentNode: TrieNode) {
        if (currentIndex >= word.length) {
            return
        }
        val currentChar = word[currentIndex]
        val trieNode = currentNode.nodes[currentChar - 'a'] ?: TrieNode()
        trieNode.value = currentChar
        trieNode.prefixCount += 1
        if (currentIndex == word.length - 1) {
            trieNode.endsWithCount += 1
        }
        currentNode.nodes[currentChar - 'a'] = trieNode
        insertWord(word, currentIndex + 1, trieNode)
    }

    override fun searchWord(word: String): Boolean {
        return searchWord(word, 0, rootNode)
    }

    private fun searchWord(word: String, currentIndex: Int, currentNode: TrieNode): Boolean {
        val currentChar = word[currentIndex]
        val nextTrieNode = currentNode.nodes[currentChar - 'a'] ?: return false
        if (currentIndex == word.length - 1) {
            return nextTrieNode.endsWithCount > 0
        }
        return searchWord(word, currentIndex + 1, nextTrieNode)
    }

    override fun deleteWord(word: String): Boolean {
        return deleteWord(word, 0, rootNode)
    }

    private fun deleteWord(word: String, currentIndex: Int, currentNode: TrieNode): Boolean {
        val currentChar = word[currentIndex]
        val nextTrieNode = currentNode.nodes[currentChar - 'a'] ?: return false
        if (currentIndex == word.length - 1) {
            nextTrieNode.prefixCount -= 1
            nextTrieNode.endsWithCount -= 1
            return true
        }
        val result = deleteWord(word, currentIndex + 1, nextTrieNode)
        if (result) {
            nextTrieNode.prefixCount -= 1
        }
        return result
    }

    override fun getCountWithPrefix(prefix: String): Int {
        return searchWithPrefix(prefix, 0, rootNode)
    }

    private fun searchWithPrefix(word: String, currentIndex: Int, currentNode: TrieNode): Int {
        val currentChar = word[currentIndex]
        if (currentIndex == word.length - 1) {
            return currentNode.nodes[currentChar - 'a']?.prefixCount ?: 0
        }
        val nextNode = currentNode.nodes[currentChar - 'a'] ?: return 0
        return searchWithPrefix(word, currentIndex + 1, nextNode)
    }
}