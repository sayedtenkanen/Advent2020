package utils

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

fun directoryBrowser(path: Path) {
    println("\nBrowsing under the path $path")
    //Check if the Path is a directory
    if (Files.isDirectory(path)) {
        //List all items in the directory. Note that we are using Java 8 streaming API to group the entries by
        //directory and files
        val fileDirMap = Files.list(path).collect(Collectors.partitioningBy({ it -> Files.isDirectory(it) }))

        println("\nDirectories\n${"=".repeat(30)}")
        //Print out all of the directories
        fileDirMap[true]?.forEach { it -> println(it.fileName) }

        println("\nFiles\n${"=".repeat(30)}")
        println("%-20s\tRead\tWrite\tExecute".format("Name"))
        //Print out all files and attributes
        fileDirMap[false]?.forEach { it ->
            println(
                "%-20s\t%-5b\t%-5b\t%b".format(
                    it.fileName,
                    Files.isReadable(it), //Read attribute
                    Files.isWritable(it), //Write attribute
                    Files.isExecutable(it)
                )
            ) //Execute attribute
        }
    } else {
        println("Enter a directory")
    }
}

fun main(args: Array<String>) {
    //Create a Path object
    val path = Paths.get(
        if (args.isEmpty()) {
            System.getProperty("user.dir")
        } else {
            args[0]
        }
    )
    directoryBrowser(path = path)
}