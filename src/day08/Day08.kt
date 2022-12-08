package day08

import readInput

fun main() {
    part1()
    part2()
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

fun part1() {
    val input = readInput(8)
    val grid = input.map { line -> line.map { it.digitToInt() } }

    fun Pair<Int, Int>.visible(direction: Direction, height: Int): Boolean =
        if (first !in 0..grid[0].lastIndex || second !in 0..grid.lastIndex) {
            true
        } else if (grid[second][first] >= height) {
            false
        } else {
            when (direction) {
                Direction.UP -> second == 0 || (first to second - 1).visible(Direction.UP, height)
                Direction.DOWN -> second == grid.lastIndex || (first to second + 1).visible(Direction.DOWN, height)
                Direction.LEFT -> first == 0 || (first - 1 to second).visible(Direction.LEFT, height)
                Direction.RIGHT -> first == grid[0].lastIndex || (first + 1 to second).visible(Direction.RIGHT, height)
            }
        }

    var total = 0
    for ((rowIndex, row) in grid.withIndex()) {
        for ((index, height) in row.withIndex()) {
            if ((index to rowIndex - 1).visible(Direction.UP, height) ||
                (index to rowIndex + 1).visible(Direction.DOWN, height) ||
                (index - 1 to rowIndex).visible(Direction.LEFT, height) ||
                (index + 1 to rowIndex).visible(Direction.RIGHT, height)
            ) {
                total++
            }
        }
    }
    println(total)
}

fun part2() {
    val grid = readInput(8).map { line -> line.map { it.digitToInt() } }
    fun Pair<Int, Int>.score(direction: Direction, height: Int, stop: Boolean): Int =
        if (stop || first !in 0..grid[0].lastIndex || second !in 0..grid.lastIndex) {
            0
        } else {
            1 + when (direction) {
                Direction.UP -> (first to second - 1).score(direction, height, grid[second][first] >= height)
                Direction.DOWN -> (first to second + 1).score(direction, height, grid[second][first] >= height)
                Direction.LEFT -> (first - 1 to second).score(direction, height, grid[second][first] >= height)
                Direction.RIGHT -> (first + 1 to second).score(direction, height, grid[second][first] >= height)
            }
        }

    var total = 0
    for ((rowIndex, row) in grid.withIndex()) {
        for ((index, height) in row.withIndex()) {
            val product = (index to rowIndex - 1).score(
                Direction.UP,
                height,
                false
            ) * (index to rowIndex + 1).score(
                Direction.DOWN,
                height,
                false
            ) * (index - 1 to rowIndex).score(
                Direction.LEFT,
                height,
                false
            ) * (index + 1 to rowIndex).score(Direction.RIGHT, height, false)
            total = maxOf(total, product)
        }
    }
    println(total)
}
