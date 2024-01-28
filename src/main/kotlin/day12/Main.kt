package day12

import java.io.File

fun main() {
    val puzzleInput = File("day12/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    val g = Grid(puzzleInput.lines())
    return dijkstraShortestPathToEndFrom(g, g.findCells('a') + g.start())
}

fun answer(puzzleInput: String): Int {
    val g = Grid(puzzleInput.lines())
    return dijkstraShortestPathToEndFrom(g, listOf(g.start()))
}

private fun dijkstraShortestPathToEndFrom(g: Grid, starts: List<Coord>): Int {
    val shortestPaths = mutableMapOf<Coord, Int>()
    val q = mutableListOf<Coord>()
    (0 until g.height()).map { y ->
        (0 until g.width()).map { x ->
            shortestPaths[Coord(x, y)] = Int.MAX_VALUE
            q.add(Coord(x, y))
        }
    }
    starts.forEach {
        shortestPaths[it] = 0
    }
    while (q.isNotEmpty()) {
        val e = q.minBy { shortestPaths[it]!! }
        q.remove(e)
        val neighbours = g.neighbours(e).filter { q.contains(it) }
        neighbours.forEach {
            shortestPaths[it] = Math.min(shortestPaths[it]!!, shortestPaths[e]!! + 1)
        }
    }
    return shortestPaths[g.end()]!!
}

data class Coord(val x: Int, val y: Int) {
    fun neighbours() = listOf(copy(x = x+1), copy(x = x-1), copy(y = y+1), copy(y = y-1))
}

data class Grid(val lines: List<String>) {
    fun height() = lines.size
    fun width() = lines.first().length

    fun cellAt(coord: Coord) = lines[coord.y][coord.x]

    fun start() = findCell('S')
    fun end() = findCell('E')

    fun findCell(c: Char): Coord {
        return findCells(c).first()
    }

    fun findCells(c: Char): List<Coord> {
        return (0 until width()).flatMap {x ->
            (0 until height()).map { y ->
                Coord(x, y)
            }
        }.filter { cellAt(it) == c }
    }

    fun gridContains(c: Coord) = (0 until height()).contains(c.y) && (0 until width()).contains(c.x)

    fun neighbours(c: Coord) : List<Coord> {
        val heightAtC = heightAt(c)
        return c.neighbours().filter { gridContains(it) }.filter { heightAt(it) <= heightAtC+1 }
    }

    private fun heightAt(c: Coord): Int {
        return when (cellAt(c)) {
            'S' -> 0
            'E' -> 'z' - 'a'
            else -> cellAt(c) - 'a'
        }
    }

}