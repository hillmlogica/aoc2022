package day4

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day4Test {
    val exampleInput = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo(2)
    }

    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo(4)
    }
}