package day04

import readInput

fun main() {
    part1()
    part2()
}

fun part1() {
    val input = readInput(4)
    var total = 0
    for (line in input) {
        val ranges = line.split(',')
        val r1 = ranges[0].split('-')[0].toInt() to ranges[0].split('-')[1].toInt()
        val r2 = ranges[1].split('-')[0].toInt() to ranges[1].split('-')[1].toInt()
        if ((r1.first >= r2.first && r1.second <= r2.second) || (r2.first >= r1.first && r2.second <= r1.second)) {
            total++
        }
    }
    println(total)
}

fun part2() {
    val input = readInput(4)
    var total = 0
    for (line in input) {
        val ranges = line.split(',')
        val r1 = ranges[0].split('-')[0].toInt() to ranges[0].split('-')[1].toInt()
        val r2 = ranges[1].split('-')[0].toInt() to ranges[1].split('-')[1].toInt()
        if (r1.first in r2.first..r2.second ||
            r1.second in r2.first..r2.second ||
            r2.first in r1.first..r1.second ||
            r2.second in r1.first..r1.second
        ) {
            total++
        }
    }
    println(total)
}
