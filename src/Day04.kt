fun main() {
    data class Entry(val rangeOne: IntProgression, val rangeTwo: IntProgression)

    fun String.toRange(): IntProgression {
        val (start, stop) = this.split("-")
        return start.toInt()..stop.toInt()
    }

    fun parseInput(input: List<String>) = input.map {
        val (rangeInputOne, rangeInputTwo) = it.split(",")
        val rangeOne = rangeInputOne.toRange()
        val rangeTwo = rangeInputTwo.toRange()
        if (rangeOne.first < rangeTwo.first || (rangeOne.first == rangeTwo.first && rangeOne.last > rangeTwo.last)) {
            Entry(rangeOne, rangeTwo)
        } else {
            Entry(rangeTwo, rangeOne)
        }
    }

    fun part1(input: List<String>): Int {
        val entries = parseInput(input)
        return entries.filter { (rangeOne, rangeTwo) -> rangeTwo.last <= rangeOne.last}.size
    }

    fun part2(input: List<String>): Int {
        val entries = parseInput(input)
        return entries.filter { (rangeOne, rangeTwo) -> rangeTwo.any { rangeOne.contains(it) } }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLineByLine("Day04_test")
    check(part1(testInput) == 2)

    val input = readInputLineByLine("Day04")
    println(part1(input))

    check(part2(testInput) == 4)
    println(part2(input))
}
