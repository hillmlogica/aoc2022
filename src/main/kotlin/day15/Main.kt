package day15

import java.io.File
import kotlin.math.abs

fun main() {
    val puzzleInput = File("day15/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput, 2000000))
    println("answer2: " + answer2(puzzleInput, 4000000))

}

fun answer2(puzzleInput: String, maxXY: Int): Long {
    val shapes = puzzleInput.lines().map { parse(it) }.map { shapeIncludingBeacon(it) }
    val x = compute(maxXY, shapes, ManhattanShape::xRangeFor)
    val y = compute(maxXY, shapes, ManhattanShape::yRangeFor)
    return (x.toLong() * 4000000) + y
}

private fun compute(maxXY: Int, shapes: List<ManhattanShape>, rangeFunction: (ManhattanShape, Int) -> IntRange): Int {
    val x = (0..maxXY).map { y ->
        val ranges = shapes.map { shape -> rangeFunction.invoke(shape, y) }
        val sortedRanges = ranges.sortedBy { it.first }
        val startingX = sortedRanges.first().first
        val accumulation = sortedRanges.fold(Pair(startingX, listOf()), ::gaps)
        accumulation.second
    }.first { it.isNotEmpty() }
    return x.first()
}

fun answer(puzzleInput: String, y: Int): Int {
    val ranges = puzzleInput.lines().map { parse(it) }.map { shapeIncludingBeacon(it) }.map { it.xRangeFor(y) }
    val sortedRanges = ranges.sortedBy { it.first }
    val startingX = sortedRanges.first().start
    val accumlatedCount = sortedRanges.fold(Pair(0, startingX), ::countRanges)
    return accumlatedCount.first
}

private fun countRanges(acc: Pair<Int, Int>, range: IntRange): Pair<Int, Int> {
    if (range.last > acc.second && range.first <= acc.second) {
        return Pair(acc.first + range.last - acc.second, range.last)
    } else if (range.last <= acc.second ){
        return acc
    } else {
        return Pair(acc.first + range.last - range.first, range.last)
    }
}
private fun gaps(acc: Pair<Int, List<Int>>, range: IntRange): Pair<Int, List<Int>> {
    if (range.last > acc.first && range.first <= acc.first) {
        return Pair(range.last, acc.second)
    } else if (range.last <= acc.first ){
        return acc
    } else {
        return Pair(range.last, acc.second + (acc.first+1 until range.first).toList())
    }
}

fun parse(line: String): Pair<Coord, Coord> {
    val regex = "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)".toRegex()
    val (sx, sy, bx, by) = regex.matchEntire(line)!!.destructured
    return Pair(Coord(sx.toInt(), sy.toInt()), Coord(bx.toInt(), by.toInt()))
}

fun shapeIncludingBeacon(sensorBeacon: Pair<Coord, Coord>): ManhattanShape {
    return sensorBeacon.first.manhattanShapes().takeWhile { !it.contains(sensorBeacon.second) }.last().next()
}

data class Coord(val x: Int, val y: Int) {
    fun manhattanShapes(): Sequence<ManhattanShape> {
        var shape = ManhattanShape(this, this, this, this, this)
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
        return ManhattanShape(origin, left.copy(x = left.x-1), top.copy(y = top.y-1), right.copy(x = right.x+1), bottom.copy(y = bottom.y+1))
    }

    fun contains(c: Coord): Boolean {
        val offsetx = abs(c.x - origin.x)
        val offsety = abs(c.y - origin.y)
        val size = right.x - origin.x
        return offsetx + offsety <= size
    }

    fun xRangeFor(y: Int): IntRange {
        val size = right.x - origin.x
        val offsety = abs(y - origin.y)
        if (offsety > size) return IntRange.EMPTY
        val offsetx = size - offsety
        val intRange = (origin.x - offsetx)..(origin.x + offsetx)
        return intRange
    }
    fun yRangeFor(x: Int): IntRange {
        val size = right.x - origin.x
        val offsetx = abs(x - origin.x)
        if (offsetx > size) return IntRange.EMPTY
        val offsety = size - offsetx
        val intRange = (origin.y - offsety)..(origin.y + offsety)
        return intRange
    }
}