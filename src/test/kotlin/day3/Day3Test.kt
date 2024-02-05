package day3

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day3Test {
    val exampleInput = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo(157)
    }

    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo(70)
    }
}