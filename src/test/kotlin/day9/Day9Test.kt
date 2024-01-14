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

    @Test
    fun `move right when head over tail`() {
        val actual =  Worm(Coord(0,0), Coord(0,0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 0), Coord(0,0)))
    }

    @Test
    fun `move left when head over tail`() {
        val actual =  Worm(Coord(0,0), Coord(0,0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 0), Coord(0,0)))
    }

    @Test
    fun `move up when head over tail`() {
        val actual =  Worm(Coord(0,0), Coord(0,0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, -1), Coord(0,0)))
    }

    @Test
    fun `move right when head on rhs of tail`() {
        val actual =  Worm(Coord(1,0), Coord(0,0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(2, 0), Coord(1,0)))
    }

    @Test
    fun `move left when head on rhs of tail`() {
        val actual =  Worm(Coord(1,0), Coord(0,0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0,0)))
    }
    @Test
    fun `move up when head on rhs of tail`() {
        val actual =  Worm(Coord(1,0), Coord(0,0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, -1), Coord(0,0)))
    }

    @Test
    fun `move right when head on lhs of tail`() {
        val actual =  Worm(Coord(-1,0), Coord(0,0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0,0)))
    }
    @Test
    fun `move left when head on lhs of tail`() {
        val actual =  Worm(Coord(-1,0), Coord(0,0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-2, 0), Coord(-1,0)))
    }
    @Test
    fun `move up when head on lhs of tail`() {
        val actual =  Worm(Coord(-1,0), Coord(0,0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, -1), Coord(0,0)))
    }
    @Test
    fun `move right when head directly above tail`() {
        val actual =  Worm(Coord(0,-1), Coord(0,0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, -1), Coord(0,0)))
    }
    @Test
    fun `move left when head directly above tail`() {
        val actual =  Worm(Coord(0,-1), Coord(0,0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, -1), Coord(0,0)))
    }
    @Test
    fun `move up when head directly above tail`() {
        val actual =  Worm(Coord(0,-1), Coord(0,0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, -2), Coord(0,-1)))
    }

    @Test
    fun `move right when head below tail`() {
        val actual =  Worm(Coord(0,1), Coord(0,0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 1), Coord(0,0)))
    }
    @Test
    fun `move left when head below tail`() {
        val actual =  Worm(Coord(0,1), Coord(0,0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 1), Coord(0,0)))
    }
    @Test
    fun `move up when head below tail`() {
        val actual =  Worm(Coord(0,1), Coord(0,0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0,0)))
    }
    @Test
    fun `move right when head up left from tail`() {
        val actual =  Worm(Coord(0,0), Coord(1,1)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 0), Coord(1,1)))
    }
    @Test
    fun `move left when head up left from tail`() {
        val actual =  Worm(Coord(0,0), Coord(1,1)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 0), Coord(0,0)))
    }
    @Test
    fun `move up when head up left from tail`() {
        val actual =  Worm(Coord(0,0), Coord(1,1)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, -1), Coord(0,0)))
    }
    @Test
    fun `move right when head down left from tail`() {
        val actual =  Worm(Coord(0,1), Coord(1,0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 1), Coord(1,0)))
    }
    @Test
    fun `move left when head down left from tail`() {
        val actual =  Worm(Coord(0,1), Coord(1,0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 1), Coord(0,1)))
    }
    @Test
    fun `move up when head down left from tail`() {
        val actual =  Worm(Coord(0,1), Coord(1,0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(1,0)))
    }
    @Test
    fun `move right when head up right from tail`() {
        val actual =  Worm(Coord(1,0), Coord(0,1)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(2, 0), Coord(1,0)))
    }
    @Test
    fun `move left when head up right from tail`() {
        val actual =  Worm(Coord(1,0), Coord(0,1)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0,1)))
    }
    @Test
    fun `move up when head up right from tail`() {
        val actual =  Worm(Coord(1,0), Coord(0,1)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, -1), Coord(1,0)))
    }
    @Test
    fun `move right when head down right from tail`() {
        val actual =  Worm(Coord(1,1), Coord(0,0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(2, 1), Coord(1,1)))
    }
    @Test
    fun `move left when head down right from tail`() {
        val actual =  Worm(Coord(1,1), Coord(0,0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 1), Coord(0,0)))
    }
    @Test
    fun `move up when head down right from tail`() {
        val actual =  Worm(Coord(1,1), Coord(0,0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 0), Coord(0,0)))
    }

}