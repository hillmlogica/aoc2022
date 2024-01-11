package day5

import java.io.File

fun main() {
    val puzzleInput = File("day5/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): String {
    return ""
}

fun answer(puzzleInput: String): String {
    val lines = puzzleInput.lines()
    val separator = lines.indexOfFirst { it.isBlank() }
    val split = lines[separator - 1].split(" +".toRegex()).filter { it.isNotBlank() }
    val howManyStacks = split.size
    val stacks : MutableList<MutableList<Char>> = MutableList(howManyStacks) { mutableListOf() }
    (0 .. separator-2).reversed().forEach{lineIndex ->
        (0 until howManyStacks).forEach {
            val crate = lines[lineIndex][(it * 4) + 1]
            if (crate != ' ') {
                stacks[it] += crate
            }
        }
    }
    println(Stacks(stacks))
    return ""
}

data class Stacks(val stacks: List<List<Char>>)
