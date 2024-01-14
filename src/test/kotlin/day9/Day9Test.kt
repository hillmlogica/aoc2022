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

    val example2Input = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20"""

    @Test
    fun `check part 1 example`() {
        Assertions.assertThat(answer(exampleInput)).isEqualTo(13)
    }

    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(example2Input)).isEqualTo(36)
    }

    @Test
    fun `move right when head over tail`() {
        val actual = Worm(Coord(0, 0), Coord(0, 0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 0), Coord(0, 0)))
    }

    @Test
    fun `move left when head over tail`() {
        val actual = Worm(Coord(0, 0), Coord(0, 0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 0), Coord(0, 0)))
    }

    @Test
    fun `move up when head over tail`() {
        val actual = Worm(Coord(0, 0), Coord(0, 0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, -1), Coord(0, 0)))
    }

    @Test
    fun `move right when head on rhs of tail`() {
        val actual = Worm(Coord(1, 0), Coord(0, 0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(2, 0), Coord(1, 0)))
    }

    @Test
    fun `move left when head on rhs of tail`() {
        val actual = Worm(Coord(1, 0), Coord(0, 0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0, 0)))
    }

    @Test
    fun `move up when head on rhs of tail`() {
        val actual = Worm(Coord(1, 0), Coord(0, 0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, -1), Coord(0, 0)))
    }
    @Test
    fun `move diag up when head on rhs of tail`() {
        val actual = Worm(Coord(1, 0), Coord(0, 0)).move(1, -1)
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(2, -1), Coord(1, -1)))
    }

    @Test
    fun `move right when head on lhs of tail`() {
        val actual = Worm(Coord(-1, 0), Coord(0, 0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0, 0)))
    }

    @Test
    fun `move left when head on lhs of tail`() {
        val actual = Worm(Coord(-1, 0), Coord(0, 0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-2, 0), Coord(-1, 0)))
    }

    @Test
    fun `move up when head on lhs of tail`() {
        val actual = Worm(Coord(-1, 0), Coord(0, 0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, -1), Coord(0, 0)))
    }

    @Test
    fun `move right when head directly above tail`() {
        val actual = Worm(Coord(0, -1), Coord(0, 0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, -1), Coord(0, 0)))
    }

    @Test
    fun `move left when head directly above tail`() {
        val actual = Worm(Coord(0, -1), Coord(0, 0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, -1), Coord(0, 0)))
    }

    @Test
    fun `move up when head directly above tail`() {
        val actual = Worm(Coord(0, -1), Coord(0, 0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, -2), Coord(0, -1)))
    }

    @Test
    fun `move right when head below tail`() {
        val actual = Worm(Coord(0, 1), Coord(0, 0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 1), Coord(0, 0)))
    }

    @Test
    fun `move left when head below tail`() {
        val actual = Worm(Coord(0, 1), Coord(0, 0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 1), Coord(0, 0)))
    }

    @Test
    fun `move up when head below tail`() {
        val actual = Worm(Coord(0, 1), Coord(0, 0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0, 0)))
    }

    @Test
    fun `move right when head up left from tail`() {
        val actual = Worm(Coord(0, 0), Coord(1, 1)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 0), Coord(1, 1)))
    }

    @Test
    fun `move left when head up left from tail`() {
        val actual = Worm(Coord(0, 0), Coord(1, 1)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 0), Coord(0, 0)))
    }

    @Test
    fun `move up when head up left from tail`() {
        val actual = Worm(Coord(0, 0), Coord(1, 1)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, -1), Coord(0, 0)))
    }

    @Test
    fun `move right when head down left from tail`() {
        val actual = Worm(Coord(0, 1), Coord(1, 0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 1), Coord(1, 0)))
    }

    @Test
    fun `move left when head down left from tail`() {
        val actual = Worm(Coord(0, 1), Coord(1, 0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(-1, 1), Coord(0, 1)))
    }

    @Test
    fun `move up when head down left from tail`() {
        val actual = Worm(Coord(0, 1), Coord(1, 0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(1, 0)))
    }

    @Test
    fun `move right when head up right from tail`() {
        val actual = Worm(Coord(1, 0), Coord(0, 1)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(2, 0), Coord(1, 0)))
    }

    @Test
    fun `move left when head up right from tail`() {
        val actual = Worm(Coord(1, 0), Coord(0, 1)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 0), Coord(0, 1)))
    }

    @Test
    fun `move up when head up right from tail`() {
        val actual = Worm(Coord(1, 0), Coord(0, 1)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, -1), Coord(1, 0)))
    }

    @Test
    fun `move right when head down right from tail`() {
        val actual = Worm(Coord(1, 1), Coord(0, 0)).moveRight()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(2, 1), Coord(1, 1)))
    }

    @Test
    fun `move left when head down right from tail`() {
        val actual = Worm(Coord(1, 1), Coord(0, 0)).moveLeft()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(0, 1), Coord(0, 0)))
    }

    @Test
    fun `move up when head down right from tail`() {
        val actual = Worm(Coord(1, 1), Coord(0, 0)).moveUp()
        Assertions.assertThat(actual).isEqualTo(Worm(Coord(1, 0), Coord(0, 0)))
    }

    @Test
    fun `debug example 2`() {
        var worm = makeWormOfSize(10)
        val tailCoords = mutableSetOf<Coord>()
        tailCoords.add(worm.tailCoord())
        worm = move(worm, tailCoords, 5, Direction.Right)
        println("\n***** after 5 RIGHT ****")
        print(worm)
        Assertions.assertThat(tailCoords).containsExactlyInAnyOrder(Coord(0, 0))
        worm = move(worm, tailCoords, 8, Direction.Up)
        println("\n***** after 8 UP ****")
        print(worm)
        Assertions.assertThat(tailCoords).containsExactlyInAnyOrder(Coord(0, 0))
        worm = move(worm, tailCoords, 8, Direction.Left)
        println("\n***** after 8 LEFT ****")
        print(worm)
        Assertions.assertThat(tailCoords).containsExactlyInAnyOrder(Coord(0, 0), Coord(1, -1), Coord(2, -2), Coord(1, -3))
    }

    fun print(worm: Worm) {

        val wormCoords = worm.coords()
        val lines = (-20..20).map { y ->
            String(
                (-20..20).map { x ->
                    if (wormCoords.contains(Coord(x, y))) {
                        val index = wormCoords.indexOf(Coord(x, y))
                        val char =
                            if (index == 0) 'H' else if (index == wormCoords.size - 1) 'T' else index.digitToChar()
                        char
                    } else {
                        '.'
                    }
                }.toCharArray()
            )
        }
        lines.forEach{println(it)}
    }

    private fun move(worm: Worm, tailCoords: MutableSet<Coord>, howMany: Int, direction: Direction): Worm {
        var worm1 = worm
        (0 until howMany).forEach {
            worm1 = worm1.move(direction)
            tailCoords.add(worm1.tailCoord())
        }
        return worm1
    }

}