package day7

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

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
        Assertions.assertThat(answer(exampleInput)).isEqualTo(95437)
    }
    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(0)
    }
}