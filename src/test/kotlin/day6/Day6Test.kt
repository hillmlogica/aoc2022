package day6

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Day6Test {
    val exampleInput1 = """mjqjpqmgbljsphdztnvjfqwrcgsmlb"""
    val exampleInput2 = """bvwbjplbgvbhsrlpgdmjqwftvncz"""
    val exampleInput3 = """nppdvjthqldpwncqszvftbrmjlhg"""
    val exampleInput4 = """nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"""
    val exampleInput5 = """zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"""

    @Test
    fun `check part 1 example 1`() {
        expectThat(answer(exampleInput1)).isEqualTo(7)
    }
    @Test
    fun `check part 1 example 2`() {
        expectThat(answer(exampleInput2)).isEqualTo(5)
    }
    @Test
    fun `check part 1 example 3`() {
        expectThat(answer(exampleInput3)).isEqualTo(6)
    }
    @Test
    fun `check part 1 example 4`() {
        expectThat(answer(exampleInput4)).isEqualTo(10)
    }
    @Test
    fun `check part 1 example 5`() {
        expectThat(answer(exampleInput5)).isEqualTo(11)
    }
    @Test
    fun `check part 2 example 1`() {
        expectThat(answer2(exampleInput1)).isEqualTo(19)
    }
    @Test
    fun `check part 2 example 2`() {
        expectThat(answer2(exampleInput2)).isEqualTo(23)
    }
    @Test
    fun `check part 2 example 3`() {
        expectThat(answer2(exampleInput3)).isEqualTo(23)
    }
    @Test
    fun `check part 2 example 4`() {
        expectThat(answer2(exampleInput4)).isEqualTo(29)
    }
    @Test
    fun `check part 2 example 5`() {
        expectThat(answer2(exampleInput5)).isEqualTo(26)
    }
}