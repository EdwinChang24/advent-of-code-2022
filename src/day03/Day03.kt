package day03

import readInput

fun main() {
    part1()
    part2()
}

fun part1() {
    val input = readInput(3)
    var total = 0
    for (string in input) {
        val first = string.subSequence(0, string.length / 2)
        val second = string.subSequence(string.length / 2, string.length)
        val intersect = first.toSet() intersect second.toSet()
        for (char in intersect) total += if (char.isLowerCase()) char - 'a' + 1 else char - 'A' + 27
    }
    println(total)
}

fun part2() {
    val input = readInput(3)
    val processed = mutableListOf<MutableList<String>>()
    var total = 0
    input.forEachIndexed { index, s ->
        when (index % 3) {
            0 -> processed += mutableListOf(s)
            1, 2 -> processed[processed.size - 1] += s
        }
    }
    for (i in processed) {
        val intersect = i[0].toSet() intersect i[1].toSet() intersect i[2].toSet()
        for (c in intersect) total += if (c.isLowerCase()) c - 'a' + 1 else c - 'A' + 27
    }
    println(total)
}
