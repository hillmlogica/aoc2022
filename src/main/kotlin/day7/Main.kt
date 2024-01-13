package day7

import java.io.File

fun main() {
    val puzzleInput = File("day7/puzzleInput.txt").readText()
    println("answer1: " + answer(puzzleInput))
    println("answer2: " + answer2(puzzleInput))

}

fun answer2(puzzleInput: String): Long {
    val root = parse(puzzleInput)
    val free = 70_000_000 - root.dirSizes().first { it.first == "/" }.second
    val additionalFree = 30_000_000 - free
    return root.dirSizes().filter { it.second >= additionalFree }.minBy { it.second }.second
}

fun answer(puzzleInput: String): Long {
    val root = parse(puzzleInput)
    return root.dirSizes().filter { it.second <= 100000 }.map { it.second }.sum()
}

private fun parse(puzzleInput: String): Directory {
    val root = Directory(name = "/", parent = null)
    var currentDirectory: Directory = root
    var lsMode = false
    puzzleInput.lines().forEach {
        if (it.startsWith("$ cd")) {
            lsMode = false
            val (dirName) = "\\$ cd (.*)".toRegex().matchEntire(it)!!.destructured
            println("\"$dirName\"")
            if (dirName == "/") {
                currentDirectory = root
            } else if (dirName == "..") {
                currentDirectory = currentDirectory.parent!!
            } else {
                currentDirectory = currentDirectory.children.find { it.name() == dirName } as Directory
            }
        } else if (it.equals("$ ls")) {
            lsMode = true
        } else if (lsMode && !it.startsWith("$")) {
            if (it.startsWith("dir")) {
                val (dirName) = "dir (.*)".toRegex().matchEntire(it)!!.destructured
                currentDirectory = currentDirectory.withChild(Directory(dirName, currentDirectory))
            } else {
                val (size, fileName) = "(\\d+) (.*)".toRegex().matchEntire(it)!!.destructured
                currentDirectory = currentDirectory.withChild(File(fileName, size.toLong()))
            }
        }
    }
    return root
}

interface Node {
    fun dirSizes(): List<Pair<String, Long>>
    fun size(): Long
    fun name(): String

}

class Directory(val name: String, val parent: Directory? = null, val children: MutableList<Node> = mutableListOf()) : Node {
    override fun dirSizes(): List<Pair<String, Long>> {
        return children.flatMap { it.dirSizes() } + Pair(name, size())
    }

    override fun size(): Long {
        return children.sumOf { it.size() }
    }

    override fun name(): String {
        return name
    }

    fun withChild(node: Node): Directory {
        children += node
        return this
    }
}


class File(val name: String, val size: Long) : Node {
    override fun dirSizes(): List<Pair<String, Long>> {
        return listOf()
    }

    override fun size(): Long {
        return size
    }

    override fun name(): String {
        return name
    }
}