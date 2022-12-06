package day06

import readInput

fun main() {
    part1()
    part2()
}

fun part1() = common(4)

fun part2() = common(14)

fun common(markerSize: Int) {
    val signal = readInput(6)[0]
    val potentialMarker = mutableListOf<Char>()
    var i = 1
    for (char in signal) {
        if (potentialMarker.size == markerSize) potentialMarker.removeFirst()
        potentialMarker += char
        if (potentialMarker.size == potentialMarker.toSet().size && potentialMarker.size == markerSize) break
        i++
    }
    println(i)
}
