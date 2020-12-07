package utils

import java.io.File

class fileReadOperations {

    fun readFileLineByLineUsingForEachLine(fileName: String): MutableList<String> {
        var lines = mutableListOf<String>()
        File(fileName).forEachLine {
            lines.add(it)
        }
        return lines
    }

    fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName)
        .useLines { it.toList() }

    fun readFileAsLinesUsingBufferedReader(fileName: String): List<String> = File(fileName).bufferedReader().readLines()

    fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()

    fun readFileAsTextUsingInputStream(fileName: String) =
        File(fileName).inputStream().readBytes().toString(Charsets.UTF_8)

    fun readFileDirectlyAsText(fileName: String): String = File(fileName).readText(Charsets.UTF_8)

    fun readFileUsingGetResource(fileName: String) {
        var fileNameResource = this::class.java.getResource(fileName)
        fileNameResource.readText(Charsets.UTF_8)
        println(fileNameResource)
    }

    fun readFileAsLinesUsingGetResourceAsStream(fileName: String) =
        this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
}

class fileWriteOperations {

    fun writeFileUsingPrintWriter(fileName: String, fileContent: String) =
        File(fileName).printWriter().use { out -> out.print(fileContent) }

    fun writeFileUsingBufferedWriter(fileName: String, fileContent: String) =
        File(fileName).bufferedWriter().use { out -> out.write(fileContent) }

    fun writeFileDirectly(fileName: String, fileContent: String) =
        File(fileName).writeText(fileContent)

    fun writeFileDirectlyAsBytes(fileName: String, fileContent: String) =
        File(fileName).writeBytes(fileContent.toByteArray())
}