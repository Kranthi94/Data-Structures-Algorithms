// Created by Kranthi on 2019-04-11.
// Question
// Problem is to find a pattern for the knight to travel the entire chess board
//
// Things to Learn
// Backtracking
//
package algorithms.backtracking

object KnightsTour {
    
    private val list: MutableList<Position> = ArrayList()
    private var iterationNumber = 1
    private const val n = 7
    
    @JvmStatic
    fun main(args: Array<String>) {
        list.add(Position(0, 0))
        val board = Array(n) { IntArray(n) }
        board[0][0] = 1
        if (makeMove(board, list[list.size - 1])) {
            println("Solution Exists")
            for (i in 0 until n) {
                for (j in 0 until n) {
                    println(board[i][j].toString() + "  ")
                }
            }
        } else {
            println("Solution Doesn't exist")
        }
    }

    private fun makeMove(board: Array<IntArray>, position: Position): Boolean {
        val possibleMoves = findPossibleMoves(board, position)
        return if (possibleMoves.isEmpty()) {
            if (iterationNumber == n * n) {
                true
            } else {
                val lastPosition = list[list.size - 1]
                board[lastPosition.x][lastPosition.y] = 0
                list.remove(lastPosition)
                iterationNumber--
                if (iterationNumber == 1 && list.size == 1 && list[0].currentIterationNumber == 2) {
                    false
                } else {
                    makeMove(board, list[list.size - 1])
                    true
                }
            }
        } else {
            val move = possibleMoves[0]
            iterationNumber++
            board[move.x][move.y] = iterationNumber
            list.add(move)
            makeMove(board, list[list.size - 1])
            true
        }
    }

    private fun findPossibleMoves(board: Array<IntArray>, position: Position): List<Position> {
        val currentX: Int = position.x
        val currentY: Int = position.y
        val possiblePositions: MutableList<Position> = ArrayList(8)
        for (i in 0..7) {
            var x = -1
            var y = -1
            if (i == 0) {
                x = currentX - 1
                y = currentY + 2
            }
            if (i == 1) {
                x = currentX + 1
                y = currentY + 2
            }
            if (i == 2) {
                x = currentX - 2
                y = currentY + 1
            }
            if (i == 3) {
                x = currentX + 2
                y = currentY + 1
            }
            if (i == 4) {
                x = currentX - 2
                y = currentY - 1
            }
            if (i == 5) {
                x = currentX + 2
                y = currentY - 1
            }
            if (i == 6) {
                x = currentX - 1
                y = currentY - 2
            }
            if (i == 7) {
                x = currentX + 1
                y = currentY - 2
            }
            if (x in 0 until n && y >= 0 && y < n && board[x][y] == 0) {
                val p = Position(x, y)
                possiblePositions.add(p)
            }
        }
        val currentIterationNumber: Int = position.currentIterationNumber
        position.currentIterationNumber = currentIterationNumber + 1
        return possiblePositions.subList(currentIterationNumber, possiblePositions.size)
    }

    private class Position(var x: Int, var y: Int) {
        var currentIterationNumber = 0
        
    }
}