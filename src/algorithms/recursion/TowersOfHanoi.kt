// Created by Kranthi on 2019-04-08.
// Question
// There are three rods. In one rods disks will be arranged in sorted order from lowest to highest. We have to move this disks from one rod to
// another.
// Constraints
// 1) Always keep a lower disk on the higher disk.
// 2) Should move only one disk at a time.
// Things to Learn
// Simple Recursion
//
package algorithms.recursion

object TowersOfHanoi {
    @JvmStatic
    fun main(args: Array<String>) {
        move(3, "A", "B", "C")
    }

    private fun move(n: Int, fromRod: String, apexRod: String, toRod: String) {
        if (n == 1) {
            println("Moving disk from $fromRod $toRod")
            return
        }
        move(n - 1, fromRod, toRod, apexRod)
        println("Moving disk from $fromRod $toRod")
        move(n - 1, apexRod, fromRod, toRod)
    }
}