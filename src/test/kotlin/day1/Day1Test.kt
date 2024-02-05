package day1

import day1.answer
import day1.answer2
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Day1Test {
    val exampleInput = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo(24000)
    }
    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo(45000)
    }
}