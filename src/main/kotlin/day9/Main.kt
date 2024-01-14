package day9

import java.io.File
import kotlin.math.abs

fun main() {
    val puzzleInput = File("day9/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return 0
}

fun answer(puzzleInput: String): Int {
    return 0
}

data class Coord(val x: Int, val y: Int)

data class Worm(val head: Coord, val tail: Coord) {
    fun moveRight(): Worm {
        return move(1)
    }
    fun moveLeft(): Worm {
        return move(-1)
    }

    private fun move(move: Int): Worm {
        val newHead = Coord(head.x + move, head.y)
        val newTail = if (abs(newHead.x - tail.x) == 2 && newHead.y == tail.y) {
            Coord(tail.x + move, tail.y)
        } else if (abs(newHead.x - tail.x) == 2 && newHead.y != tail.y) {
            Coord(tail.x + move, head.y)
        } else {
            tail
        }
        return Worm(newHead, newTail)
    }
}