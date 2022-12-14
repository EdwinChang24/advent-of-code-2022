package day13

import readInput

fun main() {
    part1()
    part2()
}

data class Thing(val isInt: Boolean, val int: Int = -1, val list: List<Thing> = emptyList()) : Comparable<Thing> {
    override fun compareTo(other: Thing): Int {
        if (isInt && other.isInt) return int - other.int
        if (isInt) return Thing(false, list = listOf(Thing(true, int = int))).compareTo(other)
        if (other.isInt) return compareTo(Thing(false, list = listOf(Thing(true, int = other.int))))
        for (i in 0..minOf(list.lastIndex, other.list.lastIndex)) {
            val compared = list[i].compareTo(other.list[i])
            if (compared != 0) return compared
        }
        return list.size - other.list.size
    }
}

fun getTheListOfThings(from: String): List<Thing> {
    val asList = from.toMutableList()
    var bracketCount = 0
    for ((i, c) in asList.withIndex()) {
        if (c == '[') bracketCount++
        if (c == ']') bracketCount--
        if (c == ',' && bracketCount == 1) asList[i] = '!'
    }
    return asList.joinToString("").removeSurrounding("[", "]").split("!").takeIf { !(it.size == 1 && it[0] == "") }
        ?.map { if (it.startsWith('[')) Thing(false, list = getTheListOfThings(it)) else Thing(true, int = it.toInt()) }
        ?: emptyList()
}

fun part1() {
    val input = readInput(13)
    val allTheThings = mutableListOf<List<Thing>>()
    for (line in input) if (line.isNotBlank()) allTheThings += getTheListOfThings(line)
    var total = 0
    for (i in 0 until allTheThings.size / 2) {
        val thing1 = allTheThings[i * 2]
        val thing2 = allTheThings[i * 2 + 1]
        if (Thing(false, list = thing1) < Thing(false, list = thing2)) total += i + 1
    }
    println(total)
}

fun part2() {
    val input = readInput(13)
    val allTheThings = mutableListOf(
        listOf(Thing(false, list = listOf(Thing(true, int = 2)))),
        listOf(Thing(false, list = listOf(Thing(true, int = 6))))
    )
    for (line in input) if (line.isNotBlank()) allTheThings += getTheListOfThings(line)
    var total = 1
    allTheThings.map { Thing(false, list = it) }.sorted().forEachIndexed { index, thing ->
        if ("$thing" == "${Thing(false, list = listOf(Thing(false, list = listOf(Thing(true, int = 2)))))}" ||
            "$thing" == "${Thing(false, list = listOf(Thing(false, list = listOf(Thing(true, int = 6)))))}"
        ) {
            total *= index + 1
        }
    }
    println(total)
}
