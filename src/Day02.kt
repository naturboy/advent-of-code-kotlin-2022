enum class Selection {
    ROCK,
    PAPER,
    SCISSOR;

    fun getSelectionScore(): Int {
        return this.ordinal + 1
    }

    fun getWinningSelection(): Selection {
        return Selection.values()[(ordinal + 2) % 3]
    }

    fun getLoosingSelection(): Selection {
        return Selection.values()[(ordinal + 1) % 3]
    }
}

fun main() {

    data class Entry(val first: Int, val second: Int)
    data class Game(val playerOne: Selection, val playerTwo: Selection)

    fun Selection.getScoreVs(selection: Selection): Int {
        if (this == selection) {
            return 3
        }
        val loosingAgainstSelection = this.getWinningSelection()
        if (selection == loosingAgainstSelection) {
            return 6
        }
        return 0
    }

    fun Int.toSelection(): Selection {
        return Selection.values()[this]
    }

    fun parseEntries(input: List<String>): List<Entry> {
        val entries = input.map { gameInput ->
            val selectionOne = gameInput.first() - 'A'
            val selectionTwo = gameInput.last() - 'X'
            Entry(selectionOne, selectionTwo)
        }
        return entries
    }

    fun calculateScore(games: List<Game>): Int {
        return games.sumOf { it.playerTwo.getScoreVs(it.playerOne) + it.playerTwo.getSelectionScore() }
    }

    fun part1(input: List<String>): Int {
        val entries = parseEntries(input)
        val games = entries.map { Game(it.first.toSelection(), it.second.toSelection()) }
        return calculateScore(games)
    }

    fun part2(input: List<String>): Int {
        fun Entry.toGameWithStrategy(): Game {
            val firstSelection = this.first.toSelection()
            val selectionByStrategy = if (this.second == 1) {
                firstSelection
            } else {
                if (this.second == 2) {
                    firstSelection.getLoosingSelection()
                } else {
                    firstSelection.getWinningSelection()
                }
            }
            return Game(firstSelection, selectionByStrategy)
        }

        val entries = parseEntries(input)
        val games = entries.map { it.toGameWithStrategy() }
        return calculateScore(games)
    }


    val testInput = readInputLineByLine("Day02_test")
    check(part1(testInput) == 15)

    val input = readInputLineByLine("Day02")
    println(part1(input))

    check(part2(testInput) == 12)
    println(part2(input))
}