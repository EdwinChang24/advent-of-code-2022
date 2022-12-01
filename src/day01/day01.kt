package day01

import readInput

fun main() {
    val testInput = readInput("day01", "test")
    check(part1(testInput) == 1)

    val input = readInput("day01")
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int {
    return input.size
}

fun part2(input: List<String>): Int {
    return input.size
}
