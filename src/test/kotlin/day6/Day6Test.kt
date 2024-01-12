package day6

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day6Test {
    val exampleInput1 = """mjqjpqmgbljsphdztnvjfqwrcgsmlb"""
    val exampleInput2 = """bvwbjplbgvbhsrlpgdmjqwftvncz"""
    val exampleInput3 = """nppdvjthqldpwncqszvftbrmjlhg"""
    val exampleInput4 = """nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"""
    val exampleInput5 = """zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"""

    @Test
    fun `check part 1 example 1`() {
        Assertions.assertThat(answer(exampleInput1)).isEqualTo(7)
    }
    @Test
    fun `check part 1 example 2`() {
        Assertions.assertThat(answer(exampleInput2)).isEqualTo(5)
    }
    @Test
    fun `check part 1 example 3`() {
        Assertions.assertThat(answer(exampleInput3)).isEqualTo(6)
    }
    @Test
    fun `check part 1 example 4`() {
        Assertions.assertThat(answer(exampleInput4)).isEqualTo(10)
    }
    @Test
    fun `check part 1 example 5`() {
        Assertions.assertThat(answer(exampleInput5)).isEqualTo(11)
    }
    @Test
    fun `check part 2 example 1`() {
        Assertions.assertThat(answer2(exampleInput1)).isEqualTo(19)
    }
    @Test
    fun `check part 2 example 2`() {
        Assertions.assertThat(answer2(exampleInput2)).isEqualTo(23)
    }
    @Test
    fun `check part 2 example 3`() {
        Assertions.assertThat(answer2(exampleInput3)).isEqualTo(23)
    }
    @Test
    fun `check part 2 example 4`() {
        Assertions.assertThat(answer2(exampleInput4)).isEqualTo(29)
    }
    @Test
    fun `check part 2 example 5`() {
        Assertions.assertThat(answer2(exampleInput5)).isEqualTo(26)
    }
}