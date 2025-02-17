package algorithms.patterns

import kotlin.math.abs

// https://github.com/kunal-kushwaha/DSA-Bootcamp-Java/blob/main/assignments/09-patterns.md
// https://takeuforward.org/strivers-a2z-dsa-course/must-do-pattern-problems-before-starting-dsa/

fun main() {
    pattern1(5)
    pattern2(5)
    pattern3(5)
    pattern4(5)
    pattern5(5)
    pattern6(5)
    pattern7(5)
    pattern8(5)
    pattern9(5)
    pattern10(5)
    pattern11(5)
    pattern12(5)
    pattern13(5)
    pattern14(5)
    pattern15(5)
    pattern16(5)
    pattern17(5)
    pattern18(5)
    pattern19(5)
    pattern20(5)
    pattern21(5)
    pattern22(5)
    pattern23(5)
    pattern24(5)
    pattern25(5)
    pattern26(5)
    pattern27(5)
    pattern28(5)
    pattern29(5)
    pattern30(5)
    pattern31(5)
    pattern32(5)
    pattern33(5)
    pattern34(5)
    pattern35(5)
}

private fun pattern1(size: Int) {
    println("!---------Pattern-1------------!")
    (0 until size).forEach {
        (0 until size).forEach {
            print("*")
        }
        println()
    }
    println("!---------Pattern-1------------!")
}

private fun pattern2(size: Int) {
    println("!---------Pattern-2------------!")
    for (i in IntRange(1, size)) {
        IntRange(1, i).forEach { j ->
            print("*")
        }
        println()
    }
    println("!---------Pattern-2------------!")
}

private fun pattern3(size: Int) {
    println("!---------Pattern-3------------!")
    for (i in IntRange(1, size)) {
        IntRange(1, size - i + 1).forEach { j ->
            print("*")
        }
        println()
    }
    println("!---------Pattern-3------------!")
}

private fun pattern4(size: Int) {
    println("!---------Pattern-4------------!")
    for (i in IntRange(1, size)) {
        for (j in IntRange(1, i)) {
            print("$j ")
        }
        println()
    }
    println("!---------Pattern-4------------!")
}

private fun pattern5(size: Int) {
    println("!---------Pattern-5------------!")
    for (i in IntRange(1, 2 * size - 1)) {
        val columns = if (i <= size) i else 2 * size - i
        IntRange(1, columns).forEach { j ->
            print("* ")
        }
        println()
    }
    println("!---------Pattern-5------------!")
}

private fun pattern6(size: Int) {
    println("!---------Pattern-6------------!")
    for (i in IntRange(1, size)) {
        var s = size - i
        while (s >= 0) {
            print(" ")
            s--
        }
        IntRange(1, i).forEach { j ->
            print("*")
        }
        println()
    }
    println("!---------Pattern-6------------!")
}

private fun pattern7(size: Int) {
    println("!---------Pattern-7------------!")
    for (i in IntRange(1, size)) {
        IntRange(0, i - 1).forEach { s ->
            print(" ")
        }
        IntRange(i, size).forEach { j ->
            print("*")
        }
        println()
    }
    println("!---------Pattern-7------------!")
}

private fun pattern8(size: Int) {
    println("!---------Pattern-8------------!")
    for (i in IntRange(1, size)) {
        IntRange(0, size - i).forEach { s ->
            print(" ")
        }
        IntRange(1, 2 * i - 1).forEach { j ->
            print("*")
        }
        println()
    }
    println("!---------Pattern-8------------!")
}

private fun pattern9(size: Int) {
    println("!---------Pattern-9------------!")
    for (i in IntRange(1, size)) {
        IntRange(0, i - 1).forEach { s ->
            print(" ")
        }
        IntRange(1, 2 * (size - i) + 1).forEach { j ->
            print("*")
        }
        println()
    }
    println("!---------Pattern-9------------!")
}

private fun pattern10(size: Int) {
    println("!---------Pattern-10------------!")
    for (i in IntRange(1, size)) {
        IntRange(0, size - i).forEach { s ->
            print(" ")
        }
        IntRange(1, i).forEach { j ->
            print("* ")
        }
        println()
    }
    println("!---------Pattern-10------------!")
}

private fun pattern11(size: Int) {
    println("!---------Pattern-11------------!")
    for (i in IntRange(1, size)) {
        IntRange(0, i).forEach { s ->
            print(" ")
        }
        IntRange(1, size - i + 1).forEach { j ->
            print("* ")
        }
        println()
    }
    println("!---------Pattern-11------------!")
}

private fun pattern12(size: Int) {
    println("!---------Pattern-12------------!")
    for (i in IntRange(1, 2 * size)) {
        if (i <= size) {
            IntRange(0, i).forEach { s ->
                print(" ")
            }
            IntRange(1, size - i + 1).forEach { j ->
                print("* ")
            }
        } else {
            IntRange(0, 2 * size - i + 1).forEach { s ->
                print(" ")
            }
            IntRange(1, i - size).forEach { j ->
                print("* ")
            }
        }
        println()
    }
    println("!---------Pattern-12------------!")
}

private fun pattern13(size: Int) {
    println("!---------Pattern-13------------!")
    for (i in IntRange(1, size)) {
        IntRange(0, size - i).forEach { s ->
            print(" ")
        }
        if (i == 1 || i == size) {
            IntRange(1, 2 * i - 1).forEach { j ->
                print("*")
            }
        } else if (i < size) {
            print("*")
            IntRange(1, 2 * i - 3).forEach { k ->
                print(" ")
            }
            print("*")
        }
        println()
    }
    println("!---------Pattern-13------------!")
}

private fun pattern14(size: Int) {
    println("!---------Pattern-14------------!")
    for (i in IntRange(1, size)) {
        IntRange(0, i - 1).forEach { s ->
            print(" ")
        }
        if (i == 1 || i == size) {
            IntRange(1, 2 * (size - i) + 1).forEach { j ->
                print("*")
            }
        } else if (i < size) {
            print("*")
            IntRange(1, 2 * (size - i) - 1).forEach { k ->
                print(" ")
            }
            print("*")
        }
        println()
    }
    println("!---------Pattern-14------------!")
}

private fun pattern15(size: Int) {
    println("!---------Pattern-15------------!")
    for (i in IntRange(1, 2 * size - 1)) {
        if (i <= size) {
            IntRange(0, size - i).forEach { s ->
                print(" ")
            }
            print("*")
            if (i > 1) {
                IntRange(1, 2 * i - 3).forEach { k ->
                    print(" ")
                }
                print("*")
            }
            println()
        } else {
            IntRange(0, i - size).forEach { s ->
                print(" ")
            }
            print("*")
            if (i < 2 * size - 1) {
                IntRange(1, 4 * size - 2 * i - 3).forEach { k ->
                    print(" ")
                }
                print("*")
            }
            println()
        }

    }
    println("!---------Pattern-15------------!")
}

private fun pattern16(size: Int) {
    val s= "0101010101"
    println(s[0] == '1')
}

private fun  pattern17(size: Int) {
    println("!---------Pattern-17------------!")
    for (i in IntRange(1, 2 * size - 1)) {
        if (i <= size) {
            IntRange(0, size - i).forEach { j ->
                print(" ")
            }
            for (k in IntRange(1, 2 * i - 1)) {
                print(abs(i - k) + 1)
            }
        } else {
            IntRange(0, i - size).forEach { j ->
                print(" ")
            }
            for (k in IntRange(1, 4 * size - 2 * i - 1)) {
                print(abs(2 * size - i - k) + 1)
            }
        }
        println()
    }
    println("!---------Pattern-17------------!")
}

private fun pattern18(size: Int) {
    println("!---------Pattern-18------------!")
    for (i in IntRange(1, 2 * size)) {
        if (i <= size) {
            IntRange(1, size - i + 1).forEach { k ->
                print("*")
            }
            IntRange(1, 2 * (i - 1)).forEach { k ->
                print(" ")
            }
            IntRange(1, size - i + 1).forEach { k ->
                print("*")
            }
        } else {
            IntRange(1, i - size).forEach { k ->
                print("*")
            }
            IntRange(1, 2 * (2 * size - i)).forEach { k ->
                print(" ")
            }
            IntRange(1, i - size).forEach { k ->
                print("*")
            }
        }
        println()
    }
    println("!---------Pattern-18------------!")
}

private fun pattern19(size: Int) {
    println("!---------Pattern-19------------!")
    for (i in IntRange(1, 2 * size - 1)) {
        if (i <= size) {
            IntRange(1, i).forEach { j ->
                print("*")
            }
            IntRange(1, 2 * (size - i)).forEach { k ->
                print(" ")
            }
            IntRange(1, i).forEach { j ->
                print("*")
            }
        } else {
            IntRange(1, 2 * size - i).forEach { j ->
                print("*")
            }
            IntRange(1, 2 * (i - size)).forEach { k ->
                print(" ")
            }
            IntRange(1, 2 * size - i).forEach { j ->
                print("*")
            }
        }
        println()
    }
    println("!---------Pattern-19------------!")
}

private fun pattern20(size: Int) {
    println("!---------Pattern-20------------!")
    for (i in IntRange(1, size)) {
        if (i == 1 || i == size) {
            IntRange(1, size - 1).forEach { k ->
                print("*")
            }
        } else {
            print("*")
            IntRange(2, size - 2).forEach { k ->
                print(" ")
            }
            print("*")
        }
        println()
    }
    println("!---------Pattern-20------------!")
}

private fun pattern21(size: Int) {
    println("!---------Pattern-21------------!")
    for (i in IntRange(1, size)) {
        val start = (i * (i -1)) / 2 + 1
        for (j in IntRange(start, start + i - 1)) {
            print("$j ")
        }
        println()
    }
    println("!---------Pattern-21------------!")
}

private fun pattern22(size: Int) {
    println("!---------Pattern-22------------!")
    for (i in IntRange(1, size)) {
        for (j in IntRange(1, i)) {
            val iEven = i % 2 == 0
            val jEven = j % 2 == 0
            if ((iEven && jEven) || (!iEven && !jEven)) {
                print("1 ")
            } else {
                print("0 ")
            }
        }
        println()
    }
    println("!---------Pattern-22------------!")
}

private fun pattern23(size: Int) {
    println("!---------Pattern-23------------!")

    println("!---------Pattern-23------------!")
}

private fun pattern24(size: Int) {
    println("!---------Pattern-24------------!")
    for (i in IntRange(1, 2 * size)) {
        if (i == 1 || i == 2 * size) {
            print("*")
            IntRange(1, 2 * (size - 1)).forEach { j ->
                print(" ")
            }
            print("*")
        } else {
            if (i <= size) {
                print("*")
                IntRange(1, i - 2).forEach { j ->
                    print(" ")
                }
                print("*")
                IntRange(1, size - 2 * i + 5).forEach { j ->
                    print(" ")
                }
                print("*")
                IntRange(1, i - 2).forEach { j ->
                    print(" ")
                }
                print("*")
            } else {
                print("*")
                IntRange(1, 2 * size - i - 1).forEach { j ->
                    print(" ")
                }
                print("*")
                IntRange(1, 2 * i - 3 * size + 3).forEach { j ->
                    print(" ")
                }
                print("*")
                IntRange(1, 2 * size - i - 1).forEach { j ->
                    print(" ")
                }
                print("*")
            }
        }
        println()
    }
    println("!---------Pattern-24------------!")
}

private fun pattern25(size: Int) {
    println("!---------Pattern-25------------!")
    for (i in IntRange(1, size)) {
        IntRange(1, size - i).forEach { j ->
            print(" ")
        }
        if (i == 1 || i == size) {
            IntRange(1, size).forEach { k ->
                print("*")
            }
        } else {
            print("*")
            IntRange(1, size - 2).forEach { k ->
                print(" ")
            }
            print("*")
        }
        println()
    }
    println("!---------Pattern-25------------!")
}

private fun pattern26(size: Int) {
    println("!---------Pattern-26------------!")
    for (i in IntRange(1, size)) {
        IntRange(1, size - i + 1).forEach { j ->
            print("$i ")
        }
        println()
    }
    println("!---------Pattern-26------------!")
}

private fun pattern27(size: Int) {
    println("!---------Pattern-27------------!")
    for (i in IntRange(1, size)) {
        IntRange(1, i - 1).forEach { j ->
            print(" ")
            print(" ")
        }
        val elements = size - i + 1
        val firstStart = 1 + (size * (size + 1)) / 2 - (elements * (elements + 1)) / 2
        var firstEnd: Int = -1
        for (j in IntRange(1, elements)) {
            firstEnd = firstStart + j - 1
            print("$firstEnd ")
        }
        print("     ")
        var nextStart = firstEnd
        for (k in IntRange(i + 1, size)) {
            nextStart += 2 * (size - k + 1)
        }
        for (j in IntRange(1, size - i + 1)) {
            val nextEnd = nextStart + j
            print("$nextEnd ")
        }
        println()
    }
    println("!---------Pattern-27------------!")
}

private fun pattern28(size: Int) {
    println("!---------Pattern-28------------!")
    for (i in IntRange(1, 2 * size - 1)) {
        if (i <= size) {
            IntRange(1, size - i).forEach { j ->
                print(" ")
            }
            IntRange(1, i).forEach { k ->
                print("* ")
            }
        } else {
            IntRange(1, i - size).forEach { j ->
                print(" ")
            }
            IntRange(1, 2 * size - i).forEach { k ->
                print("* ")
            }
        }
        println()
    }
    println("!---------Pattern-28------------!")
}

private fun pattern29(size: Int) {
    println("!---------Pattern-29------------!")
    for (i in IntRange(1, 2 * size - 1)) {
        if (i <= size) {
            IntRange(1, i).forEach { j ->
                print("*")
            }
            IntRange(1, 2 * (size - i)).forEach { j ->
                print(" ")
            }
            IntRange(1, i).forEach { j ->
                print("*")
            }
        } else {
            IntRange(1, 2 * size - i).forEach { j ->
                print("*")
            }
            IntRange(1, 2 * (i - size)).forEach { j ->
                print(" ")
            }
            IntRange(1, 2 * size - i).forEach { j ->
                print("*")
            }
        }
        println()
    }
    println("!---------Pattern-29------------!")
}

private fun pattern30(size : Int) {
    println("!---------Pattern-30------------!")
    for (i in IntRange(1, size)) {
        IntRange(1, size - i).forEach { j ->
            print(" ")
        }
        for (j in IntRange(1, 2 * i - 1)) {
            print("${abs(i - j) + 1} ")
        }
        println()
    }
    println("!---------Pattern-30------------!")
}

private fun pattern31(size : Int) {
    println("!---------Pattern-31------------!")
    for (i in 1 until  2 * size) {
        for (j in 1 until  2 * size) {
            val leftBorder = i - 1
            val topBorder = j - 1
            val rightBorder = 2 * size - 1 - i
            val bottomBorder = 2 * size - 1 - j
            val min = size - (leftBorder.coerceAtMost(rightBorder).coerceAtMost(topBorder.coerceAtMost(bottomBorder)))
            print("$min ")
        }
        println()
    }
    println("!---------Pattern-31------------!")
}

private fun pattern32(size: Int) {
    println("!---------Pattern-32------------!")
    for (i in IntRange(1, size)) {
        for (j in IntRange(1, i)) {
            val char = ('a' + (size - (i - j)) - 1).toString().replaceFirstChar {
                it.titlecaseChar()
            }
            print("$char ")
        }
        println()
    }
    println("!---------Pattern-32------------!")
}

private fun pattern33(size: Int) {
    println("!---------Pattern-33------------!")
    for (i in IntRange(1, size)) {
        for (j in IntRange(1, i)) {
            val start = i * (i - 1) / 2 - 1
            val char = ('a' + j + start).toString().replaceFirstChar {
                if ((j + start) % 2 == 1) {
                    it.titlecaseChar()
                } else {
                    it
                }
            }
            print("$char ")
        }
        println()
    }
    println("!---------Pattern-33------------!")
}

private fun pattern34(size: Int) {
    println("!---------Pattern-34------------!")
    for (i in IntRange(1, size)) {
        for (j in size - i + 1 downTo  1) {
            val char = ('a' + j - 1).toString().replaceFirstChar { it.titlecaseChar() }
            print("$char ")
        }
        println()
    }
    println("!---------Pattern-34------------!")
}

private fun pattern35(size: Int) {
    println("!---------Pattern-35------------!")
    for (i in IntRange(1, size)) {
        for (j in IntRange(1, 2 * size)) {
            if (j <= i) {
                print("$j ")
            } else if (j >= (2 * size - i + 1)) {
                print("${2 * size - j + 1} ")
            } else {
                print("  ")
            }
        }
        println()
    }
    println("!---------Pattern-35------------!")
}