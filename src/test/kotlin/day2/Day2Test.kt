package day2

import day2.answer
import day2.answer2
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day2Test {
    val exampleInput = """A Y
B X
C Z"""

    @Test
    fun `check part 1 example`() {
        Assertions.assertThat(answer(exampleInput)).isEqualTo(15)
    }

    @Test
    fun `game scores when draw`() {
        Assertions.assertThat(Game(Choice.Rock, Choice.Rock).score()).isEqualTo(4)
        Assertions.assertThat(Game(Choice.Paper, Choice.Paper).score()).isEqualTo(5)
        Assertions.assertThat(Game(Choice.Scissors, Choice.Scissors).score()).isEqualTo(6)
    }
    @Test
    fun `game scores when left loses`() {
        Assertions.assertThat(Game(Choice.Rock, Choice.Paper).score()).isEqualTo(8)
        Assertions.assertThat(Game(Choice.Paper, Choice.Scissors).score()).isEqualTo(9)
        Assertions.assertThat(Game(Choice.Scissors, Choice.Rock).score()).isEqualTo(7)
    }
    @Test
    fun `game scores when right loses`() {
        Assertions.assertThat(Game(Choice.Paper, Choice.Rock).score()).isEqualTo(1)
        Assertions.assertThat(Game(Choice.Scissors, Choice.Paper).score()).isEqualTo(2)
        Assertions.assertThat(Game(Choice.Rock, Choice.Scissors).score()).isEqualTo(3)
    }

    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(12)
    }
}