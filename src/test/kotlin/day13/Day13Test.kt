package day13

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day13Test {
    val exampleInput = """[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]"""

    @Test
    fun `check part 1 example`() {
        Assertions.assertThat(answer(exampleInput)).isEqualTo(13)
    }

    @Test
    fun `check part 2 example`() {
        Assertions.assertThat(answer2(exampleInput)).isEqualTo(140)
    }

    @Test
    fun `compare values`() {
        Assertions.assertThat(compare(ValueNode(1), ValueNode(2))).isLessThan(0)
        Assertions.assertThat(compare(ValueNode(1), ValueNode(1))).isEqualTo(0)
        Assertions.assertThat(compare(ValueNode(2), ValueNode(1))).isGreaterThan(0)
    }

    @Test
    fun `compare lists`() {
        val firstList = ListNode(listOf(ValueNode(1), ValueNode(1), ValueNode(3), ValueNode(1), ValueNode(1)))
        val secondList = ListNode(listOf(ValueNode(1), ValueNode(1), ValueNode(5), ValueNode(1), ValueNode(1)))
        Assertions.assertThat(compare(firstList, secondList)).isLessThan(0)
    }

    @Test
    fun `compare lists of different sizes`() {
        val first = ListNode(listOf())
        val second = ListNode(listOf(ValueNode(3)))
        Assertions.assertThat(compare(first, second)).isLessThan(0)
    }
    @Test
    fun `compare value to list`() {
        val first = ListNode(listOf(ValueNode(2), ValueNode(3), ValueNode(4)))
        val second = ValueNode(4)
        Assertions.assertThat(compare(first, second)).isLessThan(0)
    }

    @Test
    fun `compare lists of node lists`() {
        val first = ListNode(listOf(ListNode(listOf(ValueNode(1))), ListNode(listOf(ValueNode(2), ValueNode(3), ValueNode(4)))))
        val second = ListNode(listOf(ListNode(listOf(ValueNode(1))), ValueNode(4)))
        Assertions.assertThat(compare(first, second)).isLessThan(0)
    }
    @Test
    fun `list vs list of lists`() {
        val first = ListNode(listOf(ValueNode(9)))
        val second = ListNode(listOf(ListNode(listOf(ValueNode(8), ValueNode(7), ValueNode(6)))))
        Assertions.assertThat(compare(first, second)).isGreaterThan(0)
    }
    @Test
    fun `running out of elements on lhs`() {
        //[[4,4],4,4] vs [[4,4],4,4,4]
        val first = ListNode(listOf(ListNode(listOf(ValueNode(4), ValueNode(4))), ValueNode(4), ValueNode(4)))
        val second = ListNode(listOf(ListNode(listOf(ValueNode(4), ValueNode(4))), ValueNode(4), ValueNode(4), ValueNode(4)))
        Assertions.assertThat(compare(first, second)).isLessThan(0)
    }
    @Test
    fun `empty lists`() {
        //[[[]]] vs [[]]
        val first = ListNode(listOf(ListNode(listOf(ListNode(listOf())))))
        val second = ListNode(listOf(ListNode(listOf())))
        Assertions.assertThat(compare(first, second)).isGreaterThan(0)
    }

    @Test
    fun `parse a list`() {
        Assertions.assertThat(parse("[]")).isEqualTo(ListNode(listOf()))
        Assertions.assertThat(parse("[1]")).isEqualTo(ListNode(listOf(ValueNode(1))))
        Assertions.assertThat(parse("[1,2]")).isEqualTo(ListNode(listOf(ValueNode(1), ValueNode(2))))
        Assertions.assertThat(parse("[1,2,[]]")).isEqualTo(ListNode(listOf(ValueNode(1), ValueNode(2), ListNode(listOf()))))
        Assertions.assertThat(parse("[10]")).isEqualTo(ListNode(listOf(ValueNode(10))))
    }

}