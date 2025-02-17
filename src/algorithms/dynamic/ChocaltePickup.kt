package algorithms.dynamic

// https://www.naukri.com/code360/problems/ninja-and-his-friends_3125885

fun main() {

}

//private fun findMaxChocolates(matrix: Array<Array<Int>>): Int {
//
//}
//
//private fun findMaxChocolates(matrix: Array<Array<Int>>, aRow: Int, aColumn: Int, bRow: Int, bColumn: Int): Pair<Int, Int> {
//    val matrixIndices = matrix.indices
//    if (aRow !in matrixIndices || aColumn !in matrixIndices || bRow !in matrixIndices || bColumn !in matrixIndices) {
//        return Pair(0, 0)
//    }
//    val aLeftBottom = findMaxChocolates(matrix, aRow + 1, aColumn - 1, bRow, bColumn)
//    val aBottom = findMaxChocolates(matrix, aRow + 1, aColumn, bRow, bColumn)
//    val aRightBottom = findMaxChocolates(matrix, aRow + 1, aColumn + 1, bRow, bColumn)
//    val aBottomMax = maxOf(aLeftBottom.first, maxOf(aBottom.first, aRightBottom.first))
//    if (aBottomMax != 0) {
//        when (aBottomMax) {
//            aLeftBottom.first -> matrix[aRow + 1][aColumn - 1] == 0
//            aBottom.first -> matrix[aRow + 1][aColumn] == 0
//            aRightBottom.first -> matrix[aRow + 1][aColumn + 1] == 0
//        }
//    }
//    val aMax = matrix[aRow][aColumn] + aBottomMax
//    val bLeftBottom = findMaxChocolates(matrix, aRow, aColumn, bRow + 1, bColumn - 1)
//    val bBottom = findMaxChocolates(matrix, aRow, aColumn, bRow, bColumn)
//    val bRightBottom = findMaxChocolates(matrix, aRow, aColumn, bRow + 1, bColumn + 1)
//    val bBottomMax = maxOf(bLeftBottom.first, maxOf(bBottom.first, bRightBottom.first))
//    if (bBottomMax != 0) {
//        when (bBottomMax) {
//            bLeftBottom.first -> matrix[aRow + 1][aColumn - 1] == 0
//            bBottom.first -> matrix[aRow + 1][aColumn] == 0
//            bRightBottom.first -> matrix[aRow + 1][aColumn + 1] == 0
//        }
//    }
//    val bMax = matrix[bRow][bColumn] + bBottomMax
//    return Pair(aMax, bMax)
//}