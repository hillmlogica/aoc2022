package day2

import java.io.File

fun main() {
    val puzzleInput = File("day2/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return puzzleInput.lines().map {
        val left = parseLeft(it[0])
        Game(left, parseRight(it[2], left))
    }.map { it.score() }.sum()
}

fun answer(puzzleInput: String): Int {
    return puzzleInput.lines().map {
        Game(parseLeft(it[0]), parseRight(it[2]))
    }.map { it.score() }.sum()
}

fun parseLeft(left: Char): Choice {
    return when(left) {
        'A' -> Choice.Rock
        'B' -> Choice.Paper
        'C' -> Choice.Scissors
        else -> { throw RuntimeException("Don't understand $left")}
    }
}
fun parseRight(right: Char): Choice {
    return when(right) {
        'X' -> Choice.Rock
        'Y' -> Choice.Paper
        'Z' -> Choice.Scissors
        else -> { throw RuntimeException("Don't understand $right")}
    }
}
fun parseRight(right: Char, other: Choice): Choice {
    return when(right) {
        'X' -> other.winsAgainst()
        'Y' -> other
        'Z' -> Choice.entries.first() { it.winsAgainst() == other }
        else -> { throw RuntimeException("Don't understand $right")}
    }
}

enum class Choice(val score: Int) { Rock(1) {
    override fun winsAgainst(): Choice = Scissors
}, Paper(2) {
    override fun winsAgainst(): Choice = Rock
}, Scissors(3) {
    override fun winsAgainst(): Choice = Paper
};

    abstract fun winsAgainst(): Choice
}

data class Game(val left: Choice, val right: Choice) {
    fun score(): Int {
        return if (left == right) {
            3 + right.score
        } else if (left.winsAgainst() == right) {
            0 + right.score
        } else {
            6 + right.score
        }
    }
}