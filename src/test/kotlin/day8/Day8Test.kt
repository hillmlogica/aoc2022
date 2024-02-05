package day8

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day8Test {
    val exampleInput = """30373
25512
65332
33549
35390"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo(21)
    }
    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo(8)
    }

    @Test
    fun `can tranpose`() {
        val grid = makeGrid(exampleInput).transpose()
        val expected = makeGrid(
            """32633
05535
35353
71349
32290"""
        )
        expectThat(grid).isEqualTo(expected)
    }

}