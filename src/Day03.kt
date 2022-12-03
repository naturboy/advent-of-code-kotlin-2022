import java.lang.IllegalArgumentException

fun main() {

    fun scoreOf(char: Char): Int {
        return when (char) {
            in 'a'..'z' -> char - 'a' + 1
            in 'A'..'Z' -> char - 'A' + 27
            else -> throw IllegalArgumentException("Invalid Char '$char'")
        }
    }

    fun part1(input: List<String>): Int {
        val intersectingChars = input.map {
            val (first, second) = it.chunked(it.length / 2)
            val firstSet = first.toSortedSet()
            val secondSet = second.toSortedSet()
            firstSet.intersect(secondSet)
        }
        return intersectingChars.sumOf { chars -> chars.sumOf { char -> scoreOf(char) } }
    }


    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .sumOf { group ->
                group.map { it.toSortedSet() }
                    .reduce { first, second -> first.intersect(second).toSortedSet() }.sumOf { scoreOf(it) }
            }
    }

    val testInput = readInputLineByLine("Day03_test")
    check(part1(testInput) == 157)

    val input = readInputLineByLine("Day03")
    println(part1(input))

    check(part2(testInput) == 70)
    println(part2(input))
}