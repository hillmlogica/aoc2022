package day8

import java.io.File

fun main() {
    val puzzleInput = File("day8/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    val grid = makeGrid(puzzleInput)
    val viewsLeft = viewingDistanceHorizontal(0 until grid.height(), 0 until grid.width(), grid)
    val viewsRight = viewingDistanceHorizontal(0 until grid.height(), (0 until grid.width()).reversed(), grid).flipHorizontal()
    val transposedGrid = grid.transpose()
    val viewsUp = viewingDistanceHorizontal(0 until transposedGrid.height(), 0 until transposedGrid.width(), transposedGrid).transpose()
    val viewsDown = viewingDistanceHorizontal(0 until transposedGrid.height(), (0 until transposedGrid.width()).reversed(), transposedGrid).flipHorizontal().transpose()
    viewsLeft.multiplyWith(viewsRight)
    return 0
}

fun answer(puzzleInput: String): Int {
    val grid = makeGrid(puzzleInput)
    val visibles = mutableSetOf<Coord>()
    visibles.addAll(lookForVisiblesInHorizontals(0 until grid.height(), 0 until grid.width(), grid))
    visibles.addAll(lookForVisiblesInHorizontals(0 until grid.height(), (0 until grid.width()).reversed(), grid))
    visibles.addAll(lookForVisiblesInVerticals(0 until grid.width(), 0 until grid.height(), grid))
    visibles.addAll(lookForVisiblesInVerticals(0 until grid.width(), (0 until grid.height()).reversed(), grid))
    return visibles.size
}

fun makeGrid(puzzleInput: String) = Grid(puzzleInput.lines().map { line -> line.map { it.digitToInt() } })

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
        coordsAndTheirPreviouses.map {
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

data class Coord(val x: Int, val y: Int)

data class Grid(val lines: List<List<Int>>) {
    fun width() = lines.first().size
    fun height() = lines.size
    fun contains(it: Coord): Boolean = it.x >= 0 && it.x < width() && it.y >= 0 && it.y < height()
    fun treeAt(coord: Coord): Int = lines[coord.y][coord.x]
    fun flipHorizontal(): Grid {
        return Grid(lines.map { it.reversed() })
    }

    fun transpose(): Grid {
        return Grid((0 until width()).map { x ->
            (0 until height()).map {y ->
                lines[y][x]
            }
        })
    }

    override fun toString(): String {
        return lines.joinToString("\n")
    }

    fun multiplyWith(other: Grid): Grid {
        return Grid((0 until height()).map { y ->
            (0 until width()).map { x ->
                (treeAt(Coord(x,y)) * other.treeAt(Coord(x,y)))
            }
        })
    }
}