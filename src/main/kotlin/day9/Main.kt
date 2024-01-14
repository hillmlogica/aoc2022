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
    val tails = mutableSetOf<Coord>()
    var worm = Worm(Coord(0, 0), Coord(0,0))
    tails.add(worm.tail)
    puzzleInput.lines().forEach {
        val direction = DIRECTIONS_BY_CHAR[it[0]]!!
        val (quantityAsString) = "\\w (\\d+)".toRegex().matchEntire(it)!!.destructured
        val quantity = quantityAsString.toInt()
        (0 until quantity).forEach{
            worm = worm.move(direction)
            tails.add(worm.tail)
        }
    }
    return tails.size
}

val DIRECTIONS_BY_CHAR = Direction.entries.associateBy { it.c }
enum class Direction(val c: Char) { Right('R'), Left('L'), Up('U'), Down('D') }

data class Coord(val x: Int, val y: Int)

data class Worm(val head: Coord, val tail: Coord) {
    fun move(direction: Direction) : Worm {
        return when(direction) {
            Direction.Right -> moveRight()
            Direction.Left -> moveLeft()
            Direction.Up -> moveUp()
            Direction.Down -> moveDown()
        }
    }

    fun moveRight(): Worm {
        return move(1, 0)
    }
    fun moveLeft(): Worm {
        return move(-1, 0)
    }
    fun moveUp(): Worm {
        return move(0, -1)
    }
    fun moveDown(): Worm {
        return move(0, 1)
    }

    private fun move(moveX: Int, moveY: Int): Worm {
        val newHead = Coord(head.x + moveX, head.y + moveY)
        val newTail = if (abs(newHead.x - tail.x) == 2) {
            Coord(tail.x + moveX, if(newHead.y == tail.y) tail.y else head.y)
        } else if (abs(newHead.y - tail.y) == 2) {
            Coord(if(newHead.x == tail.x) tail.x else head.x, tail.y + moveY)
        } else {
            tail
        }
        return Worm(newHead, newTail)
    }
}