package day_three

import utils.fileReadOperations
import utils.runIfFileAvailable

data class SlopeDxsDys(val dX: Int, val dY: Int, var treeCount: Int = 0)

class PuzzleThree {

    fun solvePuzzleThree() {
        val filePathNameString = "src/main/kotlin/day_three/input_three.txt"

        runIfFileAvailable(filePathNameString) {
            filePathNameString.puzzleThreeActions(
                listOf(
                    SlopeDxsDys(1, 1),
                    SlopeDxsDys(3, 1),
                    SlopeDxsDys(5, 1),
                    SlopeDxsDys(7, 1),
                    SlopeDxsDys(1, 2)
                )
            )
        }
    }

    private fun String.puzzleThreeActions(slopeDxsDys: List<SlopeDxsDys>) {
        val listStringValues =
            (fileReadOperations().readFileAsLinesUsingBufferedReader(this))
                .map { it.toList() }
        slopeDxsDys.forEach { slopeIndex ->
            var index = 0
            val lineCount = listStringValues.count()
            for (itemIndex in 0 until lineCount step slopeIndex.dY) {
                val it = listStringValues[itemIndex]
                index %= it.count()
                if (it[index] == '#') {
                    slopeIndex.treeCount++
                }
                index += slopeIndex.dX
            }
            println("Number of trees encountered ${slopeIndex.treeCount} with (dX=${slopeIndex.dX}, dY=${slopeIndex.dY})")
        }

        println(
            "Product of total number of tree encountered is " +
                "${slopeDxsDys.map { it.treeCount }.reduce { acc, i -> acc * i }}"
        )
    }
}
