package day_one

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class PuzzleOneTest {
    @Test
    fun testInputFileExists() {
        val fileName = File("src/main/kotlin/day_one/input_one.txt").absolutePath
        assertTrue(File(fileName).exists())
    }
}