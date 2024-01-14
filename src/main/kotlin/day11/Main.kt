package day11

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val puzzleInput = File("day11/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Long {
    val monkeys = parseMonkeys(puzzleInput)
    val monkeysLcm = lcm(monkeys.map { it.divisor })
    (0 until 10000).forEach { round ->
        monkeys.forEach{
            it.takeTurn(false, monkeysLcm)
        }
    }
    return monkeys.map { it.inspectedCount }.sortedDescending().map { it.toLong() }.take(2).reduce { a, b -> a * b }
}

fun answer(puzzleInput: String): Int {
    val monkeys = parseMonkeys(puzzleInput)
    val monkeysLcm = lcm(monkeys.map { it.divisor })
    (0 until 20).forEach {
        monkeys.forEach{
            it.takeTurn(true, monkeysLcm)
        }
    }
    return monkeys.map { it.inspectedCount }.sortedDescending().take(2).reduce { a, b -> a * b }
}

private fun parseMonkeys(puzzleInput: String) : List<Monkey> {
    val monkeyDefinitions = puzzleInput.lines().fold(listOf(listOf<String>())) { acc, s ->
        if (s.isBlank()) {
            acc + listOf(listOf())
        } else {
            acc.dropLast(1) + listOf(acc.last() + s)
        }
    }
    return monkeyDefinitions.map { parseMonkey(it) }
}

fun parseMonkey(monkeyDefinition: List<String>) : Monkey {
    val (id) = "Monkey (\\d):".toRegex().matchEntire(monkeyDefinition[0])!!.destructured
    val (startingItems) = "  Starting items: (.*)".toRegex().matchEntire(monkeyDefinition[1])!!.destructured
    val items = startingItems.split(",").map { it.trim() }.map { it.toLong() }
    val (op, arg) = "  Operation: new = old (.) (.*)".toRegex().matchEntire(monkeyDefinition[2])!!.destructured
    val (divisor) = "  Test: divisible by (\\d+)".toRegex().matchEntire(monkeyDefinition[3])!!.destructured
    val (monkeyIfTrue) = "    If true: throw to monkey (\\d+)".toRegex().matchEntire(monkeyDefinition[4])!!.destructured
    val (monkeyIfFalse) = "    If false: throw to monkey (\\d+)".toRegex().matchEntire(monkeyDefinition[5])!!.destructured
    val monkey = Monkey(
        id.toInt(),
        makeOpFn(op, arg),
        { it % divisor.toLong() == 0L },
        monkeyIfTrue.toInt(),
        monkeyIfFalse.toInt(),
        divisor.toInt()
    )
    items.forEach { monkey.addItem(it) }
    return monkey
}

fun makeOpFn(op: String, arg: String): (Long) -> Long {
    when(op) {
        "*" -> return { it -> it * arg(it, arg) }
        "+" -> return { it -> it + arg(it, arg) }
        else -> throw RuntimeException("Don't know how to handle $op")
    }
}

fun arg(it: Long, arg: String): Long {
    return if (arg == "old") it else arg.toLong()
}

fun lcm(numbers: List<Int>) : Int {
    var runningLcm = numbers[0]
    (1 until numbers.size).forEach {
        runningLcm = lcm(runningLcm, numbers[it])
    }
    return runningLcm
}

fun lcm(a: Int, b: Int) : Int {
    val larger = max(a, b)
    var smaller = min(a, b)
    val maxLCM = a * b
    var guess = larger
    while (guess <= maxLCM) {
        if (guess % smaller == 0) {
            return guess
        }
        guess += larger
    }
    throw RuntimeException("Not expecting to get here!")
}

val monkeysById = mutableMapOf<Int, Monkey>()
class Monkey(val id: Int, val op: (Long) -> Long, val test: (Long) -> Boolean, val idIfTrue: Int, val idIfFalse: Int, val divisor: Int) {
    val items = mutableListOf<Long>()
    var inspectedCount = 0
    init {
        monkeysById[id] = this
    }

    fun takeTurn(worryDivisor: Boolean, monkeysLcm: Int) {
        items.forEach {
            val level = (if (worryDivisor) op.invoke(it) / 3 else op.invoke(it)) % monkeysLcm
            if (test.invoke(level)) monkeysById[idIfTrue]!!.addItem(level) else monkeysById[idIfFalse]!!.addItem(level)
            inspectedCount++
        }
        items.clear()
    }

    fun addItem(item: Long) {
        items += item
    }
}