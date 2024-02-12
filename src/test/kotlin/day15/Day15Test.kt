package day15

import org.junit.jupiter.api.Test
import strikt.api.Assertion
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class Day15Test {
    val exampleInput = """Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo(0)
    }

    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo(0)
    }

    @Test
    fun `check manhattan shapes`() {
        val shapes = Coord(0,0).manhattanShapes()
        val shape1 = shapes.elementAt(0)
        val shape2 = shapes.elementAt(1)
        expectThat(shape1.contains(Coord(-1, 0))).isTrue()
        expectThat(shape1.contains(Coord(0, 0))).isTrue()
        expectThat(shape1.contains(Coord(1, 0))).isTrue()
        expectThat(shape1.contains(Coord(0, 1))).isTrue()
        expectThat(shape1.contains(Coord(0, -1))).isTrue()
        expectThat(shape1.contains(Coord(-1, -1))).isFalse()
        expectThat(shape1.contains(Coord(1, 1))).isFalse()
        expectThat(shape1.contains(Coord(1, -1))).isFalse()
        expectThat(shape1.contains(Coord(-1, 1))).isFalse()

        expectThat(shape2.contains(Coord(-2, -2))).isFalse()
        expectThat(shape2.contains(Coord(-2, -1))).isFalse()
        expectThat(shape2.contains(Coord(-2, 0))).isTrue()
        expectThat(shape2.contains(Coord(-2, 1))).isFalse()
        expectThat(shape2.contains(Coord(-2, 2))).isFalse()
        expectThat(shape2.contains(Coord(-1, -2))).isFalse()
        expectThat(shape2.contains(Coord(-1, -1))).isTrue()
        expectThat(shape2.contains(Coord(-1, 0))).isTrue()
        expectThat(shape2.contains(Coord(-1, 1))).isTrue()
        expectThat(shape2.contains(Coord(-1, 2))).isFalse()
        expectThat(shape2.contains(Coord(0, -2))).isTrue()
        expectThat(shape2.contains(Coord(0, -1))).isTrue()
        expectThat(shape2.contains(Coord(0, 0))).isTrue()
        expectThat(shape2.contains(Coord(0, 1))).isTrue()
        expectThat(shape2.contains(Coord(0, 2))).isTrue()
        expectThat(shape2.contains(Coord(1, -2))).isFalse()
        expectThat(shape2.contains(Coord(1, -1))).isTrue()
        expectThat(shape2.contains(Coord(1, 0))).isTrue()
        expectThat(shape2.contains(Coord(1, 1))).isTrue()
        expectThat(shape2.contains(Coord(1, 2))).isFalse()
        expectThat(shape2.contains(Coord(2, -2))).isFalse()
        expectThat(shape2.contains(Coord(2, -1))).isFalse()
        expectThat(shape2.contains(Coord(2, 0))).isTrue()
        expectThat(shape2.contains(Coord(2, 1))).isFalse()
        expectThat(shape2.contains(Coord(2, 2))).isFalse()
        expectThat(shape2.contains(Coord(200, 200))).isFalse()
    }
}