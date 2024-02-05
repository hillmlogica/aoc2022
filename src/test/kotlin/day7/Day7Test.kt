package day7

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.containsExactly
import strikt.assertions.containsExactlyInAnyOrder
import strikt.assertions.isEqualTo

class Day7Test {
    val exampleInput = """${'$'} cd /
${'$'} ls
dir a
14848514 b.txt
8504156 c.dat
dir d
${'$'} cd a
${'$'} ls
dir e
29116 f
2557 g
62596 h.lst
${'$'} cd e
${'$'} ls
584 i
${'$'} cd ..
${'$'} cd ..
${'$'} cd d
${'$'} ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k"""

    @Test
    fun `check part 1 example`() {
        expectThat(answer(exampleInput)).isEqualTo(95437)
    }
    @Test
    fun `check part 2 example`() {
        expectThat(answer2(exampleInput)).isEqualTo(24933642)
    }

    @Test
    fun `check example tree`() {
        val tree = Directory(name = "/",
            children = mutableListOf(
                Directory(name = "a",
                    children = mutableListOf(
                        Directory(name = "e", children = mutableListOf(
                            File("i", 584)
                        )),
                        File("f", 29116),
                        File("g", 2557),
                        File("h.lst", 62596),
                    )
                ),
                File("b.txt", 14848514),
                File("c.dat", 8504156),
                Directory(name = "d", children = mutableListOf(
                    File("j", 4060174),
                    File("d.log", 8033020),
                    File("d.ext", 5626152),
                    File("k", 7214296),
                )),
            )
        )
        expectThat(tree.dirSizes()).containsExactlyInAnyOrder(
            Pair("e", 584L),
            Pair("a", 94853L),
            Pair("d", 24933642L),
            Pair("/", 48381165L)
        )
    }
}