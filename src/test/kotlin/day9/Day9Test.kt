package day9

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {
    val exampleInput = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2"""

    @Test
    fun `check part 1 example`() {
        Assertions.assertThat(answer(exampleInput)).isEqualTo(13)
    }
    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(0)
    }

}