package day2

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Day2Test {
    val exampleInput = """A Y
B X
C Z"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo(15)
    }

    @Test
    fun `game scores when draw`() {
        expectThat(Game(Choice.Rock, Choice.Rock).score()).isEqualTo(4)
        expectThat(Game(Choice.Paper, Choice.Paper).score()).isEqualTo(5)
        expectThat(Game(Choice.Scissors, Choice.Scissors).score()).isEqualTo(6)
    }
    @Test
    fun `game scores when left loses`() {
        expectThat(Game(Choice.Rock, Choice.Paper).score()).isEqualTo(8)
        expectThat(Game(Choice.Paper, Choice.Scissors).score()).isEqualTo(9)
        expectThat(Game(Choice.Scissors, Choice.Rock).score()).isEqualTo(7)
    }
    @Test
    fun `game scores when right loses`() {
        expectThat(Game(Choice.Paper, Choice.Rock).score()).isEqualTo(1)
        expectThat(Game(Choice.Scissors, Choice.Paper).score()).isEqualTo(2)
        expectThat(Game(Choice.Rock, Choice.Scissors).score()).isEqualTo(3)
    }

    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo(12)
    }
}