package day11

import readInput

fun main() {
    part1()
    part2()
}

fun part1() = common(20) { this / 3 }

fun part2() = common(10_000) { this % it.reduce { acc, l -> acc * l } }

fun common(rounds: Int, keptManageable: Long.(MutableList<Long>) -> Long) {
    val input = readInput(11)
    val items = mutableListOf<MutableList<Long>>()
    val operations = mutableListOf<(Long) -> Long>()
    val tests = mutableListOf<Long>()
    val ifTrue = mutableListOf<Int>()
    val ifFalse = mutableListOf<Int>()
    for (line in input) when {
        line.startsWith("  S") -> items += line.removePrefix("  Starting items: ").split(", ").map { it.toLong() }
            .toMutableList()

        line.startsWith("  O") -> with(line.removePrefix("  Operation: new = old ").split(' ')) {
            operations.add(
                if (first() == "+") {
                    { it + last().toInt() }
                } else if (line.last().isDigit()) {
                    { it * last().toInt() }
                } else {
                    { it * it }
                }
            )
        }

        line.startsWith("  T") -> tests += line.split(' ').last().toLong()
        line.startsWith("    If t") -> ifTrue += line.split(' ').last().toInt()
        line.startsWith("    If f") -> ifFalse += line.split(' ').last().toInt()
    }
    val totals = MutableList(items.size) { 0L }
    repeat(rounds) {
        repeat(items.size) { monkey ->
            repeat(items[monkey].size) {
                val worryLevel = operations[monkey](items[monkey].removeFirst()).keptManageable(tests)
                val throwTo = if (worryLevel % tests[monkey] == 0L) ifTrue[monkey] else ifFalse[monkey]
                items[throwTo] += worryLevel
                totals[monkey]++
            }
        }
    }
    val (first, second) = totals.sortedDescending()
    println(first * second)
}
