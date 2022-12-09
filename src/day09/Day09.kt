package day09

import readInput

fun main() {
    part1()
    part2()
}

data class Pos(var x: Int, var y: Int)

fun moveNext(h: Pos, t: Pos) {
    when {
        h.x == t.x && h.y - t.y == 2 -> t.y++
        h.x == t.x && t.y - h.y == 2 -> t.y--
        h.y == t.y && h.x - t.x == 2 -> t.x++
        h.y == t.y && t.x - h.x == 2 -> t.x--
        (h.y - t.y == 2 && h.x - t.x == 1) ||
            (h.x - t.x == 2 && h.y - t.y == 1) ||
            (h.x - t.x == 2 && h.y - t.y == 2) -> {
            t.x++; t.y++
        }

        (t.y - h.y == 2 && h.x - t.x == 1) ||
            (h.x - t.x == 2 && t.y - h.y == 1) ||
            (h.x - t.x == 2 && t.y - h.y == 2) -> {
            t.x++; t.y--
        }

        (t.y - h.y == 2 && t.x - h.x == 1) ||
            (t.x - h.x == 2 && t.y - h.y == 1) ||
            (t.x - h.x == 2 && t.y - h.y == 2) -> {
            t.x--; t.y--
        }

        (h.y - t.y == 2 && t.x - h.x == 1) ||
            (t.x - h.x == 2 && h.y - t.y == 1) ||
            (t.x - h.x == 2 && h.y - t.y == 2) -> {
            t.x--; t.y++
        }
    }
}

fun part1() {
    val input = readInput(9)
    val visited = mutableListOf(Pos(0, 0))
    val h = Pos(0, 0)
    val t = Pos(0, 0)
    for (line in input) {
        val direction = line.split(" ")[0]
        val count = line.split(" ")[1].toInt()
        repeat(count) {
            when (direction) {
                "U" -> h.y++
                "D" -> h.y--
                "L" -> h.x--
                "R" -> h.x++
            }
            moveNext(h, t)
            visited += t.copy()
        }
    }
    println(visited.distinctBy { it.x to it.y }.size)
}

fun part2() {
    val input = readInput(9)
    val visited = mutableListOf(Pos(0, 0))
    val snake = List(10) { Pos(0, 0) }
    for (line in input) {
        val direction = line.split(" ")[0]
        val count = line.split(" ")[1].toInt()
        repeat(count) {
            when (direction) {
                "U" -> snake[0].y++
                "D" -> snake[0].y--
                "L" -> snake[0].x--
                "R" -> snake[0].x++
            }
            for (i in 1..snake.lastIndex) {
                moveNext(snake[i - 1], snake[i])
            }
            visited += snake.last().copy()
        }
    }
    println(visited.distinctBy { it.x to it.y }.size)
}
