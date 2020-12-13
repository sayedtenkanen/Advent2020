package day_two

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class PuzzleTwoTest {
    @Test
    fun testInputFileExists() {
        val fileName = File("src/main/kotlin/day_two/input_two.txt").absolutePath
        assertTrue(File(fileName).exists())
    }

    @Test
    fun checkPolicyWorksOnPasswordEntryString() {
        assertEquals(
            1,
            PuzzleTwo().handleSingleItemPasswordPolicyEntry(
                listOf("17-18", "z:", "zzzzzzzzdzzzzzzgzr"), 2
            )
        )
    }
}