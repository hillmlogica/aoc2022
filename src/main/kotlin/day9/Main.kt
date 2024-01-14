package day9

import java.io.File
import kotlin.math.abs

fun main() {
    val puzzleInput = File("day9/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    var worm = makeWormOfSize(10)
    return applyInstructionsTo(worm, parseInstructions(puzzleInput))
}

fun makeWormOfSize(size: Int): Worm {
    var worm = Worm(Coord(0, 0), Coord(0,0))
    (2 until size).forEach {
        worm = Worm(Coord(0, 0), worm)
    }
    return worm
}

fun answer(puzzleInput: String): Int {
    var worm = makeWormOfSize(2)
    return applyInstructionsTo(worm, parseInstructions(puzzleInput))
}

private fun applyInstructionsTo(worm: Worm, instructions: Sequence<Direction>): Int {
    var worm1 = worm
    val tails = mutableSetOf<Coord>()
    tails.add(worm1.tailCoord())
    instructions.forEach {
        worm1 = worm1.move(it)
        tails.add(worm1.tailCoord())
    }
    return tails.size
}

fun parseInstructions(puzzleInput: String): Sequence<Direction> {
    val lines = puzzleInput.lines()
    return lines.flatMap {
        val direction = DIRECTIONS_BY_CHAR[it[0]]!!
        val (quantityAsString) = "\\w (\\d+)".toRegex().matchEntire(it)!!.destructured
        val quantity = quantityAsString.toInt()
        List(quantity) { direction }
    }.asSequence()
}

val DIRECTIONS_BY_CHAR = Direction.entries.associateBy { it.c }
enum class Direction(val c: Char) { Right('R'), Left('L'), Up('U'), Down('D') }

data class Coord(val x: Int, val y: Int) : Tail {
    override fun x(): Int = x
    override fun y(): Int = y
    override fun move(moveX: Int, moveY: Int): Tail = Coord(x + moveX, y + moveY)
    override fun tailCoord(): Coord = this
    override fun coords(): List<Coord> {
        return listOf(this)
    }
}

interface Tail {
    fun x(): Int
    fun y(): Int

    fun move(moveX: Int, moveY: Int): Tail

    fun tailCoord(): Coord
    fun coords(): List<Coord>
}

data class Worm(val head: Coord, val tail: Tail) : Tail {
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

    override fun move(moveX: Int, moveY: Int): Worm {
        val newHead = Coord(head.x + moveX, head.y + moveY)
        val tailXMove: Int
        val tailYMove: Int
        if ((abs(newHead.x - tail.x()) == 2 && newHead.y != tail.y()) || (abs(newHead.y - tail.y()) == 2 && newHead.x != tail.x())) {
            tailXMove = if (newHead.x - tail.x() == 2) 1 else if (newHead.x - tail.x() == -2) -1 else newHead.x - tail.x()
            tailYMove = if (newHead.y - tail.y() == 2) 1 else if (newHead.y - tail.y() == -2) -1 else newHead.y - tail.y()
        } else if ((abs(newHead.x - tail.x()) == 2) || (abs(newHead.y - tail.y()) == 2)) {
            tailXMove = if (newHead.x - tail.x() == 2) 1 else if (newHead.x - tail.x() == -2) -1 else 0
            tailYMove = if (newHead.y - tail.y() == 2) 1 else if (newHead.y - tail.y() == -2) -1 else 0
        } else {
            tailXMove = 0
            tailYMove = 0
        }
        return Worm(newHead, tail.move(tailXMove, tailYMove))
    }

    override fun tailCoord(): Coord = tail.tailCoord()
    override fun coords(): List<Coord> {
        return listOf(head) + tail.coords()
    }

    override fun x(): Int = head.x

    override fun y(): Int = head.y
}