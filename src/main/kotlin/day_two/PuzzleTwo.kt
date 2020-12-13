package day_two

import utils.fileReadOperations
import utils.runIfFileAvailable

class PuzzleTwo() {

    fun solvePuzzleTwo() {
        val filePathNameString = "src/main/kotlin/day_two/input_two.txt"
        runIfFileAvailable(filePathNameString) { puzzleTwoActions(filePathNameString) }
    }

    private fun puzzleTwoActions(fileNameInfo: String) {
        val listStringValues =
            (fileReadOperations().readFileAsLinesUsingBufferedReader(fileNameInfo)).map { it.split(" ") }

        solvePuzzleTwoProblems(listStringValues, 1)
        solvePuzzleTwoProblems(listStringValues, 2)
    }

    private fun solvePuzzleTwoProblems(listStringValues: List<List<String>>, problemNo: Int) {
        var countValidPasswords: Int = 0
        listStringValues.forEach { it ->
            countValidPasswords.plus(handleSingleItemPasswordPolicyEntry(it, problemNo))
        }
        println("Puzzle #2 part $problemNo. valid password count is $countValidPasswords")
    }

    fun handleSingleItemPasswordPolicyEntry(
        it: List<String>,
        problemNo: Int
    ): Int {
        var minMaxCount = it[0].split("-").map { it.toInt() }
        var matchFor = it[1].replace(":", "")
        val count = it[2].count { matchFor.contains(it) }
        when (problemNo) {
            1 -> if ((minMaxCount[0] <= count) && (count <= minMaxCount[1])) return 1
            2 -> if (it[2].count { matchFor.contains(it) } > 0) {
                val lookInString = it[2].map { it.toString() }
                if (!((lookInString[minMaxCount[0] - 1] == matchFor) && (lookInString[minMaxCount[1] - 1] == matchFor))) {
                    if ((lookInString[minMaxCount[0] - 1] == matchFor) || (lookInString[minMaxCount[1] - 1] == matchFor)) return 1
                }
            }
        }
        return 0
    }
}
