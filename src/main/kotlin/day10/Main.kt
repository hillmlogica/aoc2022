package day10

import java.io.File
import kotlin.math.abs

fun main() {
    val puzzleInput = File("day10/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return 0
}

fun answer(puzzleInput: String): Int {
    return puzzleInput.lines().map {
        if (it == "noop") Pair(1, 0) else {
            val (quantityAsString) = "addx (-?\\d+)".toRegex().matchEntire(it)!!.destructured
            Pair(2, quantityAsString.toInt())
        }
    }.runningFold(Cycle(0, 1, 1, 1)) {
        acc: Cycle, operation: Pair<Int, Int> -> Cycle(acc.to, acc.to + operation.first, acc.endingX, acc.endingX + operation.second)
    }.filter {
        (it.from until it.to).any{cycle -> (cycle - 20) % 40 == 0}
    }.mapIndexed({ index: Int, cycle: Cycle ->
        val cycleId = 20 + (40*index)
        val strength = cycleId * cycle.startingX
        strength
    }).sum()
}

data class Cycle(val from: Int, val to: Int, val startingX: Int, val endingX: Int)