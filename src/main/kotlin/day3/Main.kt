package day3

import java.io.File

fun main() {
    val puzzleInput = File("day3/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return puzzleInput.lines().chunked(3).map {
        rucksacks -> rucksacks[0].first { rucksacks[1].contains(it) && rucksacks[2].contains(it) }
    }.map {
        priorityFor(it)
    }.sum()
}

fun answer(puzzleInput: String): Int {
    return puzzleInput.lines().map {
        val halfLength = it.length / 2
        val left = it.substring(0, halfLength)
        val right = it.substring(halfLength)
        commonItemType(left, right)
    }.map {
        priorityFor(it)
    }.sum()
}

private fun priorityFor(it: Char): Int {
    return if (it.isUpperCase()) 27 + it.code - 'A'.code
    else 1 + it.code - 'a'.code
}

fun commonItemType(left: String, right: String): Char {
    return left.first() { right.contains(it) }
}