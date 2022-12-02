package day01

import readInput

fun main() {
    part1()
    part2()
}

fun part1() {
    val input = readInput("day01", "Day01")
    val elves = mutableListOf(0)
    input.forEach { if (it == "") elves += 0 else elves[elves.size - 1] += it.toInt() }
    println(elves.max())
}

fun part2() {
    val input = readInput("day01", "Day01")
    val elves = mutableListOf(0)
    input.forEach { if (it == "") elves += 0 else elves[elves.size - 1] += it.toInt() }
    elves.sortDescending()
    println(elves[0] + elves[1] + elves[2])
}
