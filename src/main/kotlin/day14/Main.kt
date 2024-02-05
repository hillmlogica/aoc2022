package day14

import java.io.File

fun main() {
    val puzzleInput = File("day14/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    val allLines = puzzleInput.lines().flatMap {
        parswLines(it)
    }
    val floorY = allLines.flatMap { listOf(it.a.y, it.b.y) }.max() + 2
    val floorLine = Line(Coord(Int.MIN_VALUE, floorY), Coord(Int.MAX_VALUE, floorY))
    return Puzzle(allLines + floorLine).addSandUntilSandDropsOffOrReachesSource()
}

fun answer(puzzleInput: String): Int {
    val allLines = puzzleInput.lines().flatMap {
        parswLines(it)
    }
    return Puzzle(allLines).addSandUntilSandDropsOffOrReachesSource()
}

private fun parswLines(it: String) : List<Line> {
    var c = readCoord(it, 0)
    var index = 0
    val lines = mutableListOf<Line>()
    while (true) {
        index = it.indexOf("-> ", index) + 3
        if (index == 2) {
            return lines
        }
        val nextC = readCoord(it, index)
        if (c.x <= nextC.x && c.y <= nextC.y) {
            lines.add(Line(c, nextC))
        } else {
            lines.add(Line(nextC, c))
        }
        c = nextC
    }
}

private fun readCoord(it: String, index: Int): Coord {
    val (x, y) = "(\\d+),(\\d+)".toRegex().matchAt(it, index)!!.destructured
    return Coord(x.toInt(), y.toInt())
}

data class Puzzle(val lines: List<Line>) {

    fun addSandUntilSandDropsOffOrReachesSource() : Int {
        val dropOffY = lines.flatMap { listOf(it.a.y, it.b.y) }.max() + 1
        val sand = Sand()
        val source = Coord(500, 0)
        var grain = doAGrainOfSand(dropOffY, sand, source)
        while (grain != null && !sand.contains(source)) {
            sand.add(grain)
            grain = doAGrainOfSand(dropOffY, sand, source)
            if (sand.size() % 1000 == 0) {
                println("${sand.size()}")
            }
        }
        return sand.size()
    }

    private fun doAGrainOfSand(dropOffY: Int, sand: Sand, source: Coord): Coord? {
        var pos = source
        while (pos.y < dropOffY) {
            val nextMoves = listOf(pos.down(), pos.downLeft(), pos.downRight())
            val nextMove = nextMoves.firstOrNull{canMoveTo(it, sand)}
            if (nextMove != null) {
                pos = nextMove
            } else {
                return pos
            }
        }
        return null
    }

    private fun canMoveTo(coord: Coord, sand: Sand): Boolean {
        if (sand.contains(coord)) {
            return false
        }
        if (lines.any { it.contains(coord) }) {
            return false
        }
        return true
    }
}

class Sand() {
    var size: Int = 0
    var sandBorder = mutableListOf<Coord>()
    fun contains(coord: Coord): Boolean {
        return sandBorder.contains(coord)
    }

    fun add(grain: Coord) {
        sandBorder.add(grain)
        val possibleMovesForGrain = listOf(grain.down(), grain.downLeft(), grain.downRight())
        possibleMovesForGrain.forEach {
            if (sandBorder.contains(it)) {
                if (sandBorder.contains(it.upLeft()) && sandBorder.contains(it.upRight()) && sandBorder.contains(it.up())) {
                    sandBorder.remove(it)
                }
            }
        }
        size++
    }

    fun size(): Int {
        return size
    }

}
data class Line(val a: Coord, val b: Coord) {
    init {
        require(b.y >= a.y) { "$a, $b" }
        require(b.x >= a.x) {"$a, $b" }
    }
    fun contains(coord: Coord): Boolean {
        if (a.y == b.y) {
            return coord.y == a.y && coord.x >= a.x && coord.x <= b.x
        }
        if (a.x == b.x) {
            return coord.x == a.x && coord.y >= a.y && coord.y <= b.y
        }
        return false
    }
}

data class Coord(val x: Int, val y: Int) {
    fun down(): Coord {
        return copy(x, y+1)
    }

    fun downLeft(): Coord {
        return copy(x-1, y+1)
    }
    fun downRight(): Coord {
        return copy(x+1, y+1)
    }

    fun upLeft(): Coord {
        return copy(x-1, y-1)
    }
    fun upRight(): Coord {
        return copy(x+1, y-1)
    }
    fun up(): Coord {
        return copy(x, y-1)
    }
}