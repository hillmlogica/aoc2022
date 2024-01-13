package day8

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day8Test {
    val exampleInput = """30373
25512
65332
33549
35390"""

    @Test
    fun `check part 1 example`() {
        Assertions.assertThat(answer(exampleInput)).isEqualTo(21)
    }
    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(0)
    }

}