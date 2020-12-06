import day_one.PuzzleOne
import utils.fileReadOperations
import java.io.File

fun main() {
    println("Executing from main:")
    val fileName = File("src/main/resources/input_one.txt").absolutePath

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