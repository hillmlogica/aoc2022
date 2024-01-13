package day8

import java.io.File

fun main() {
    val puzzleInput = File("day8/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    val grid = Grid(puzzleInput.lines())
    val viewsLeft = viewingDistanceHorizontal(0 until grid.height(), 0 until grid.width(), grid)
    return 0
}

fun answer(puzzleInput: String): Int {
    val grid = Grid(puzzleInput.lines())
    val visibles = mutableSetOf<Coord>()
    visibles.addAll(lookForVisiblesInHorizontals(0 until grid.height(), 0 until grid.width(), grid))
    visibles.addAll(lookForVisiblesInHorizontals(0 until grid.height(), (0 until grid.width()).reversed(), grid))
    visibles.addAll(lookForVisiblesInVerticals(0 until grid.width(), 0 until grid.height(), grid))
    visibles.addAll(lookForVisiblesInVerticals(0 until grid.width(), (0 until grid.height()).reversed(), grid))
    return visibles.size
}

private fun viewingDistanceHorizontal(
    yRange: IntProgression,
    xRange: IntProgression,
    grid: Grid
): Grid {
    val lines = yRange.map { y ->
        val prevCoords =
            xRange.fold(listOf(listOf())) { acc: List<List<Coord>>, x: Int -> acc + listOf(acc.last() + Coord(x, y)) }
                .dropLast(1)
        val coordsAndTheirPreviouses = xRange.map { x ->
            Coord(x, y)
        }.zip(prevCoords)
        val distances = coordsAndTheirPreviouses.map {
            val treeHeight = grid.treeAt(it.first)
            val otherTreeHeights = it.second.reversed().map { grid.treeAt(it) }
            var blocked = false
            var viewingDistance = 0
            otherTreeHeights.forEach {
                if (!blocked) {
                    viewingDistance += 1
                    if (it >= treeHeight) {
                        blocked = true
                    }
                }
            }
            viewingDistance
        }
        val line = String(distances.map { it.digitToChar() }.toCharArray())
        println(line)
        line
    }
    return Grid(lines)
}

private fun lookForVisiblesInHorizontals(
    yRange: IntProgression,
    xRange: IntProgression,
    grid: Grid
): Set<Coord> {
    val visibles = mutableSetOf<Coord>()
    yRange.forEach { y ->
        var maxSoFar = -1
        xRange.forEach { x ->
            var coord = Coord(x, y)
            if (grid.treeAt(coord) > maxSoFar) {
                visibles.add(coord)
                maxSoFar = grid.treeAt(coord)
            }
        }
    }
    return visibles
}

private fun lookForVisiblesInVerticals(
    xRange: IntProgression,
    yRange: IntProgression,
    grid: Grid
): Set<Coord> {
    val visibles = mutableSetOf<Coord>()
    xRange.forEach { x ->
        var maxSoFar = -1
        yRange.forEach { y ->
            var coord = Coord(x, y)
            if (grid.treeAt(coord) > maxSoFar) {
                visibles.add(coord)
                maxSoFar = grid.treeAt(coord)
            }
        }
    }
    return visibles
}

data class Coord(val x: Int, val y: Int) {
    fun neighbours() = listOf(copy(x = x + 1), copy(x = x - 1), copy(y = y + 1), copy(y = y - 1))
}

data class Grid(val lines: List<String>) {
    fun width() = lines.first().length
    fun height() = lines.size
    fun contains(it: Coord): Boolean = it.x >= 0 && it.x < width() && it.y >= 0 && it.y < height()
    fun treeAt(coord: Coord): Int = lines[coord.y][coord.x].digitToInt()
}