import day1.answer
import day1.answer2
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

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
        Assertions.assertThat(answer(exampleInput)).isEqualTo(24000)
    }
    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(45000)
    }
}