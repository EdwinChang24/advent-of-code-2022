package day02

import readInput

fun main() {
    part1()
    part2()
}

fun part1() {
    val guide = readInput(2).map { it[0] to it[2] }
    var total = 0
    for (strategy in guide) {
        total += when (strategy) {
            'A' to 'X', 'B' to 'Y', 'C' to 'Z' -> 3
            'A' to 'Y', 'B' to 'Z', 'C' to 'X' -> 6
            else -> 0
        }
        total += when (strategy.second) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> 0
        }
    }
    println(total)
}

fun part2() {
    val guide = readInput(2).map { it[0] to it[2] }
    var total = 0
    for (strategy in guide) {
        total += when (strategy.second) {
            'X' -> 0
            'Y' -> 3
            'Z' -> 6
            else -> 0
        }
        total += when (strategy) {
            'B' to 'X', 'A' to 'Y', 'C' to 'Z' -> 1
            'C' to 'X', 'B' to 'Y', 'A' to 'Z' -> 2
            else -> 3
        }
    }
    println(total)
}
