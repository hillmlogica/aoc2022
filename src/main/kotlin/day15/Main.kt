package day15

import java.io.File
import kotlin.math.abs

fun main() {
    val puzzleInput = File("day15/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return 0
}

fun answer(puzzleInput: String): Int {
    return 0
}

data class Coord(val x: Int, val y: Int) {
    fun manhattanShapes(): Sequence<ManhattanShape> {
        var shape = ManhattanShape(this, copy(x-1), copy(y-1), copy(x+1), copy(y+1))
        return sequence {
            while(true) {
                yield(shape)
                shape = shape.next()
            }
        }
    }
}

data class ManhattanShape(val origin: Coord, val left: Coord, val top: Coord, val right: Coord, val bottom: Coord) {
    fun next(): ManhattanShape {
        return ManhattanShape(origin, left.copy(left.x-1), top.copy(top.y-1), right.copy(right.x+1), bottom.copy(bottom.y+1))
    }

    fun contains(c: Coord): Boolean {
        val offsetx = abs(c.x - origin.x)
        val offsety = abs(c.y - origin.y)
        val size = right.x - origin.x
        return offsetx + offsety <= size
    }
}