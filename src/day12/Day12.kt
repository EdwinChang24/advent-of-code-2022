package day12

import readInput

fun main() {
    part1()
    part2()
}

fun part1() = common { s, _ -> setOf(s) }

fun part2() = common { _, aSet -> aSet }

fun common(startingPoints: (s: Pair<Int, Int>, aSet: Set<Pair<Int, Int>>) -> Set<Pair<Int, Int>>) {
    val input = readInput(12)
    val grid = input.map {
        it.map { c ->
            when (c) {
                'S' -> -1
                'E' -> -2
                else -> c - 'a'
            }
        }.toMutableList()
    }

    operator fun <T> List<List<T>>.get(pair: Pair<Int, Int>) = get(pair.second)[pair.first]
    operator fun <T> List<MutableList<T>>.set(pair: Pair<Int, Int>, value: T) {
        get(pair.second)[pair.first] = value
    }

    var s: Pair<Int, Int>? = null
    var e: Pair<Int, Int>? = null
    val aSet = mutableSetOf<Pair<Int, Int>>()
    grid.forEachIndexed { y, ints ->
        ints.forEachIndexed { x, i ->
            if (i == 0) aSet += x to y
            if (i == -1) s = (x to y).also { grid[it] = 0 } else if (i == -2) e = (x to y).also { grid[it] = 26 }
        }
    }

    fun Pair<Int, Int>.surrounding() = listOf(
        first + 1 to second,
        first - 1 to second,
        first to second + 1,
        first to second - 1
    ).filter { it.first in 0..grid[0].lastIndex && it.second in 0..grid.lastIndex }.toSet()

    var min = Int.MAX_VALUE
    for (start in startingPoints(s!!, aSet)) {
        val stepsGrid: List<MutableList<Int?>> = grid.map { it.map { null }.toMutableList() }
        var justVisited = setOf(start)
        var steps = 0

        var actuallyWorked = true
        while (true) {
            if (e in justVisited) break
            val newJustVisited = mutableSetOf<Pair<Int, Int>>()
            actuallyWorked = false
            for (visited in justVisited) {
                for (new in visited.surrounding().filter { stepsGrid[it] == null && grid[it] - grid[visited] <= 1 }) {
                    stepsGrid[new] = steps
                    newJustVisited += new
                    actuallyWorked = true
                }
            }
            if (!actuallyWorked) break
            justVisited = newJustVisited
            steps++
        }
        if (actuallyWorked) min = minOf(min, steps)
    }
    println(min)
}
