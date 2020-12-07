package day_one

import utils.fileReadOperations
import java.io.File
import kotlin.system.measureTimeMillis

class PuzzleOne() {

    private lateinit var lines: List<String>
    private lateinit var linesData: List<String>
    private var timesInMs = mutableMapOf<String, String>()
    private var time: Int = 0

    fun executeTimedReadOperations(fileName: String): List<String> {

        time = measureTimeMillis {
            lines = fileReadOperations().readFileLineByLineUsingForEachLine(fileName)
        }.toInt()
        timesInMs["forEachLine - count ${lines.count()}"] = "%s ms".format(time)

        time = measureTimeMillis {
            lines = fileReadOperations().readFileAsLinesUsingUseLines(fileName)
        }.toInt()
        timesInMs["useLines - count ${lines.count()}"] = "%s ms".format(time)

        time = measureTimeMillis {
            linesData = fileReadOperations().readFileAsLinesUsingBufferedReader(fileName)
        }.toInt()
        timesInMs["bufferReader - count ${lines.count()}"] = "%s ms".format(time)

        time = measureTimeMillis {
            lines = fileReadOperations().readFileAsLinesUsingReadLines(fileName)
        }.toInt()
        timesInMs["readLines - count ${lines.count()}"] = "%s ms".format(time)

        time = measureTimeMillis {
            lines = listOf(fileReadOperations().readFileAsTextUsingInputStream(fileName))
        }.toInt()
        timesInMs["inputStream - count ${lines.count()}"] = "%s ms".format(time)

        time = measureTimeMillis {
            lines = listOf(fileReadOperations().readFileDirectlyAsText(fileName))
        }.toInt()
        timesInMs["readText - count ${lines.count()}"] = "%s ms".format(time)

        println(timesInMs)
        println(linesData)
        return linesData
    }

    fun solvePuzzleOne() {
        val fileName = File("src/main/kotlin/day_one/input_one.txt").absolutePath

        if (File(fileName).exists()) {
            val target = 2020
            PuzzleOne().executeTimedReadOperations(fileName)
            val listIntValues =
                (fileReadOperations().readFileAsLinesUsingBufferedReader(fileName)).map { it.toInt() }.sorted()
            val listIterator = listIntValues.listIterator()
            var item: Int
            while (listIterator.hasNext()) {
                item = listIterator.next()
                if (listIntValues.contains(target.minus(item))) {
                    println(
                        "Values are $item and ${target.minus(item)}, product is %s".format(item * target.minus(item))
                    )
                    break
                } else {
                    // No match for $item
                }
            }
        } else {
            println("File not found!")
        }
    }
}