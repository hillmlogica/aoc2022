package day1

import java.io.File

fun main() {
    val puzzleInput = File("day1/puzzleInput.txt").readText()
    println(answer(puzzleInput))

}

fun answer(puzzleInput: String): Int {
    val groups = puzzleInput.lines().fold(listOf(listOf())) { acc: List<List<Int>>, s: String ->
        val lists: List<List<Int>> = if (s.isBlank()) {
            acc + listOf(listOf())
        } else {
            val untouched = acc.subList(0, acc.size - 1)
            val extended = listOf(acc.last() + s.toInt())
            untouched + extended
        }
        lists
    }
    return groups.map { it.sum() }.max()
}