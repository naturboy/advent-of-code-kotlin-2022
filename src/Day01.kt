fun main() {
    fun part1(input: List<String>): Int {
        return input.maxOf { calories -> calories.split("\n").sumOf { it.toInt() } }
    }

    fun part2(input: List<String>): Int {
        return input.map { calories -> calories.split("\n").sumOf { it.toInt() } }.sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputWithDelimiter("Day01_test", "\n\n")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInputWithDelimiter("Day01", "\n\n")
    println(part1(input))
    println(part2(input))
}
