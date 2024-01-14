package day10

import java.io.File
import kotlin.math.abs

fun main() {
    val puzzleInput = File("day10/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    val answer2 = answer2(puzzleInput)
    answer2.forEach { println(it) }

}

fun answer2(puzzleInput: String): List<String> {
    val x = spritePositions(puzzleInput).flatMap { (it.from until it.to).map { c -> Pair(c, it.startingX) } }
    val y = x.drop(1).map {
        val pos = (it.first - 1) % 40
        if ((it.second - 1..it.second + 1).contains(pos)) '#' else '.'
    }
    return y.chunked(40) { String(it.toCharArray()) }
}

fun answer(puzzleInput: String): Int {
    return spritePositions(puzzleInput).filter {
        (it.from until it.to).any{cycle -> (cycle - 20) % 40 == 0}
    }.mapIndexed({ index: Int, cycle: Cycle ->
        val cycleId = 20 + (40*index)
        val strength = cycleId * cycle.startingX
        strength
    }).sum()
}

private fun spritePositions(puzzleInput: String) = puzzleInput.lines().map {
    if (it == "noop") Pair(1, 0) else {
        val (quantityAsString) = "addx (-?\\d+)".toRegex().matchEntire(it)!!.destructured
        Pair(2, quantityAsString.toInt())
    }
}.runningFold(Cycle(0, 1, 1, 1)) { acc: Cycle, operation: Pair<Int, Int> ->
    Cycle(acc.to, acc.to + operation.first, acc.endingX, acc.endingX + operation.second)
}

data class Cycle(val from: Int, val to: Int, val startingX: Int, val endingX: Int)