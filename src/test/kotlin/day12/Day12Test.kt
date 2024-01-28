package day12

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day12Test {
    val exampleInput = """Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi"""

    @Test
    fun `check part 1 example`() {
        Assertions.assertThat(answer(exampleInput)).isEqualTo(31)
    }

    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(29)
    }
}