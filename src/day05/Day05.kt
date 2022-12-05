package day05

import readInput

fun main() {
    part1()
    part2()
}

fun part1() = common { input, stacks ->
    for (line in input) if (line.startsWith("move")) {
        val asList = line.removePrefix("move ").split(" from ", " to ").map { it.toInt() }
        repeat(asList[0]) { stacks[asList[2] - 1] += stacks[asList[1] - 1].removeLast() }
    }
}

fun part2() = common { input, stacks ->
    for (line in input) if (line.startsWith("move")) {
        val asList = line.removePrefix("move ").split(" from ", " to ").map { it.toInt() }
        val popped = mutableListOf<Char>()
        repeat(asList[0]) { popped += stacks[asList[1] - 1].removeLast() }
        popped.reverse()
        stacks[asList[2] - 1].addAll(popped)
    }
}

fun common(moveStrategy: (input: List<String>, stacks: MutableList<MutableList<Char>>) -> Unit) {
    val input = readInput(5)
    val stacks = mutableListOf<MutableList<Char>>()
    for (line in input) if (line.startsWith(" 1")) {
        repeat(line.split(" ").last().toInt()) { stacks += mutableListOf<Char>() }
    }
    for (line in input) {
        if (line.startsWith(" 1")) break
        for (index in line.indices) if (line[index].isLetter()) stacks[(index - 1) / 4] += line[index]
    }
    for (s in stacks) s.reverse()
    moveStrategy(input, stacks)
    stacks.forEach { print(it.last()) }
    println()
}
