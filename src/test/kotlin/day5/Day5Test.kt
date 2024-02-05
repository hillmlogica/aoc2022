package day5

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Day5Test {
    val exampleInput = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo("CMZ")
    }

    @Test
    fun `parse move`() {
        expectThat(parseMove("move 1 from 2 to 1")).isEqualTo(Move(1, 2, 1))
    }

    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo("MCD")
    }
}