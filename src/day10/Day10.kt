package day10

import readInput
import kotlin.math.absoluteValue

fun main() {
    part1()
    part2()
}

fun part1() = common { cycles -> println(listOf(20, 60, 100, 140, 180, 220).sumOf { cycles[it - 1] * it }) }

fun part2() = common { cycles ->
    val image = mutableListOf<Char>()
    cycles.forEachIndexed { index, i ->
        image += if ((((index + 400) % 40) - ((i + 400) % 40)).absoluteValue <= 1) '#' else '.'
    }
    image.forEachIndexed { index, c -> if (index % 40 == 39) println(c) else print(c) }
}

fun common(andThen: (cycles: MutableList<Int>) -> Unit) {
    val input = readInput(10)
    val cycles = mutableListOf(1)
    for (line in input) {
        cycles += cycles.last()
        if (line.split(' ')[0] == "addx") cycles += cycles.last() + line.split(' ')[1].toInt()
    }
    andThen(cycles)
}
