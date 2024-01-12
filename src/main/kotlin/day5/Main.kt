package day5

import java.io.File

fun main() {
    val puzzleInput = File("day5/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): String {
    return "boo"
}

fun answer(puzzleInput: String): String {
    val lines = puzzleInput.lines()
    val separator = lines.indexOfFirst { it.isBlank() }
    val ss = parseStacks(lines, separator)
    val finalStacks = lines.subList(separator + 1, lines.size).map { parseMove(it) }.fold(ss) { ss, move -> ss.apply(move) }
    return String(finalStacks.stacks.map { it.last() }.toCharArray())
}

private fun parseStacks(lines: List<String>, separator: Int): Stacks {
    val split = lines[separator - 1].split(" +".toRegex()).filter { it.isNotBlank() }
    val howManyStacks = split.size
    val stacks: MutableList<MutableList<Char>> = MutableList(howManyStacks) { mutableListOf() }
    (0..separator - 2).reversed().forEach { lineIndex ->
        (0 until howManyStacks).forEach {
            val crate = lines[lineIndex][(it * 4) + 1]
            if (crate != ' ') {
                stacks[it] += crate
            }
        }
    }
    return Stacks(stacks)
}

fun parseMove(s: String) : Move {
    val (quantity, from, to) = "move (\\d+) from (\\d+) to (\\d+)".toRegex().matchEntire(s)!!.destructured

    return Move(quantity.toInt(),from.toInt(),to.toInt())
}

data class Stacks(val stacks: List<List<Char>>) {
    fun apply(move: Move): Stacks {
        var newStacks = this
        (1 .. move.quantity).forEach {
            newStacks = newStacks.move(move.from - 1, move.to - 1)
        }
        return newStacks
    }

    fun move(from: Int, to: Int) : Stacks {
        val newTo = stacks[to] + stacks[from].last()
        val newFrom = stacks[from].dropLast(1)
        val newStacks = stacks.toMutableList()
        newStacks[from] = newFrom
        newStacks[to] = newTo
        return Stacks(newStacks)
    }

}

data class Move(val quantity: Int, val from: Int, val to: Int) {
}
