package algorithms.hashmaps

import utils.printValue

fun main() {
    val huffmanEncoding = HuffmanEncoding()
    val encodedString = huffmanEncoding.encodeString("ababcddc")
    encodedString.printValue()
    val decodedString = huffmanEncoding.decodeString(encodedString)
    decodedString.printValue()
}

class HuffmanEncoding {

    private var frequencyMap: MutableMap<Char, Int> = mutableMapOf()
    private var encodedMap: MutableMap<Char, String> = mutableMapOf()
    private var decodedMap: MutableMap<String, Char> = mutableMapOf()

    fun encodeString(string: String): String {
        if (string.isEmpty()) {
            return string
        }
        frequencyMap.clear()
        encodedMap.clear()
        decodedMap.clear()
        frequencyMap = getFrequencyMap(string)
        encodedMap = getEncodingMap(frequencyMap)
        decodedMap = getDecodedMap(encodedMap)
        return getEncodedString(string, encodedMap)
    }

    fun decodeString(encodedString: String): String {
        val stringBuilder = StringBuilder()
        encodedString.split("|").forEach { string ->
            if (string.isNotEmpty()) {
                stringBuilder.append("${decodedMap[string]}")
            }
        }
        return stringBuilder.toString()
    }

    private fun getEncodedString(string: String, encodedMap: MutableMap<Char, String>): String {
        val stringBuilder = StringBuilder()
        string.forEach { char ->
            stringBuilder.append("${encodedMap[char]}|")
        }
        return stringBuilder.toString()
    }

    private fun getDecodedMap(encodedMap: MutableMap<Char, String>): MutableMap<String, Char> {
        return encodedMap.map { it.value to it.key }.toMap().toMutableMap()
    }

    private fun getEncodingMap(frequencyMap: MutableMap<Char, Int>): MutableMap<Char, String> {
        val encodedMap: HashMap<Char, String> = hashMapOf()
        var previousValue = "0"
        frequencyMap.forEach { (char, _) ->
            if (encodedMap.isEmpty()) {
                encodedMap[char] = previousValue
            } else if (encodedMap.size == frequencyMap.size - 1) {
                encodedMap[char] = previousValue.replace('0', '1')
            } else {
                encodedMap[char] = "1$previousValue"
            }
            previousValue = encodedMap[char]!!
        }
        return encodedMap.toList().sortedBy { it.second }.toMap().toMutableMap()
    }

    private fun getFrequencyMap(string: String): MutableMap<Char, Int> {
        val hashMap: HashMap<Char, Int> = hashMapOf()
        string.forEach { char ->
            if (!hashMap.containsKey(char)) {
                hashMap[char] = 0
            }
            hashMap[char] = hashMap[char]!! + 1
        }
        return hashMap.toList().sortedByDescending { it.second }.toMap().toMutableMap()
    }
}

