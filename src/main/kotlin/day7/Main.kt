package day7

import java.io.File

fun main() {
    val puzzleInput = File("day7/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Int {
    return 0
}

fun answer(puzzleInput: String): Int {
    return 0
}

interface Node {
    fun dirSizes(): List<Pair<String, Long>>
    fun size(): Long

}

data class Directory(val name: String, val children: List<Node>) : Node {
    override fun dirSizes(): List<Pair<String, Long>> {
        return children.flatMap { it.dirSizes() } + Pair(name, size())
    }

    override fun size(): Long {
        return children.sumOf { it.size() }
    }
}


data class File(val name: String, val size: Long) : Node {
    override fun dirSizes(): List<Pair<String, Long>> {
        return listOf()
    }

    override fun size(): Long {
        return size
    }
}