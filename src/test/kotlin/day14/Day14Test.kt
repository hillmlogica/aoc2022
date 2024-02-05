package day14

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day14Test {
    val exampleInput = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9"""

    @Test
    fun `check part 1 example`() {
        Assertions.assertThat(answer(exampleInput)).isEqualTo(24)
    }

    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(93)
    }
}