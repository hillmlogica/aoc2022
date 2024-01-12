package day6

import java.io.File

fun main() {
    val puzzleInput = File("day6/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return findCharIndexThatMakesChareGroupOfSize(puzzleInput, 14)
}

fun answer(puzzleInput: String): Int {
    return findCharIndexThatMakesChareGroupOfSize(puzzleInput, 4)
}

private fun findCharIndexThatMakesChareGroupOfSize(puzzleInput: String, numberOfChars: Int): Int {
    val charGroups = (0..puzzleInput.length - numberOfChars).map { startingChar ->
        Pair(
            String((0 until numberOfChars).map { puzzleInput[startingChar + it] }.toCharArray()),
            startingChar + numberOfChars
        )
    }
    return charGroups.first { it.first.toCharArray().toSet().size == numberOfChars }.second
}