package day13

import java.io.File

fun main() {
    val puzzleInput = File("day13/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    val sorted = (listOf(parse("[[2]]"), parse("[[6]]")) + puzzleInput.lines().filter { it.isNotBlank() }.map { parse(it) }).sortedWith { o1, o2 -> compare(o1, o2) }
    return (sorted.indexOf(parse("[[2]]")) + 1) * (sorted.indexOf(parse("[[6]]")) + 1)
}

fun answer(puzzleInput: String): Int {
    val pairs = puzzleInput.lines().filter { it.isNotBlank() }.chunked(2)
    return pairs.map { Pair(parse(it[0]), parse(it[1])) }.mapIndexed { index, pair ->
        if (compare(
                pair.first,
                pair.second
            ) < 0
        ) { println(index+1)
            index + 1} else 0
    }.sum()
}

//[1,[2,[3,[4,[5,6,7]]]],8,9]
fun parse(line: String): Node {
    return parseList(line, 0).first
}

fun parseList(line: String, index: Int): Pair<Node, Int> {
    if (line[index] != '[') {
        throw RuntimeException("Not a list!")
    }

    val myList = mutableListOf<Node>()
    var i = index + 1
    while (line[i] != ']') {
        if (line[i] == '[') {
            val (n, nextI) = parseList(line, i)
            myList += n
            i = nextI
        } else if (line[i].isDigit()) {
            val (n, nextI) = parseNumber(line, i)
            myList += n
            i = nextI
        } else if (line[i] == ',') {
            i++
        }
    }
    return Pair(ListNode(myList), i + 1)
}

fun parseNumber(line: String, index: Int): Pair<Node, Int> {
    if (!line[index].isDigit()) {
        throw RuntimeException("Not a number")
    }
    var i = index
    val listDigits = mutableListOf<Char>()
    while (line[i].isDigit()) {
        listDigits += line[i]
        i++
    }
    return Pair(ValueNode(listDigits.joinToString("").toInt()), i)
}



fun compare(left: Node, right: Node): Int {
    if (left is ValueNode && right is ValueNode) {
        return left.compareTo(right)
    }
    if (left is ListNode && right is ListNode) {
        return left.compareTo(right)
    }
    if (left is ValueNode) {
        return compare(ListNode(listOf(left)), right)
    }
    if (right is ValueNode) {
        return compare(left, ListNode(listOf(right)))
    }
    return 0
}

interface Node

data class ListNode(val nodes: List<Node>) : Node {
    fun compareTo(right: ListNode): Int {
        val comparisons = nodes.zip(right.nodes).map { compare(it.first, it.second) }
        val firstNonZero = comparisons.firstOrNull() { it != 0 }
        if (firstNonZero == null) {
            return nodes.size - right.nodes.size
        } else {
            return firstNonZero
        }
    }
}

data class ValueNode(val value: Int) : Node {
    fun compareTo(valueNode: ValueNode): Int {
        return value - valueNode.value
    }
}