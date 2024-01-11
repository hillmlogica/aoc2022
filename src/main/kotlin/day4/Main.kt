package day4

import java.io.File

fun main() {
    val puzzleInput = File("day4/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return parseRanges(puzzleInput)
            .map { overlaps(it.first, it.second) }
            .map { if (it) 1 else 0 }
            .sum()

}

fun answer(puzzleInput: String): Int {
    return parseRanges(puzzleInput)
            .map { isFullyContained(it.first, it.second) }
            .map { if (it) 1 else 0 }
            .sum()
}

private fun parseRanges(puzzleInput: String) = puzzleInput.lines()
        .map {
            val ranges = it.split(",")
            Pair(range(ranges[0]), range(ranges[1]))
        }

fun isFullyContained(left: IntRange, right: IntRange): Boolean {
    return (right.contains(left.first) && right.contains(left.last)) ||
            (left.contains(right.first) && left.contains(right.last))
}
fun overlaps(left: IntRange, right: IntRange): Boolean {
    return right.contains(left.first) || right.contains(left.last) || left.contains(right.first) || left.contains(right.last)
}

fun range(rangeAsString: String): IntRange {
    val parts = rangeAsString.split("-")
    return IntRange(parts[0].toInt(), parts[1].toInt())
}
