package day07

import readInput

fun main() {
    part1()
    part2()
}

fun part1() = common { sizes ->
    println(sizes.sumOf { if (it <= 100000) it else 0 })
}

fun part2() = common { sizes ->
    val currentFree = 70000000 - sizes.max()
    println(sizes.filter { currentFree + it >= 30000000 }.min())
}

fun common(andThen: (sizes: MutableList<Int>) -> Unit) {
    val input = readInput(7)

    class Thing(
        val dir: Boolean,
        val contents: MutableList<Thing>?,
        val outer: Thing?,
        val size: Int?
    )

    val root = Thing(true, mutableListOf(), null, null)
    var pwd = root
    var running = ""
    for (line in input) {
        fun handleNextCommand() {
            if (line.removePrefix("$ ").startsWith("cd")) {
                pwd = if (line.endsWith("..")) {
                    pwd.outer ?: pwd
                } else {
                    pwd.contents!!.add(Thing(true, mutableListOf(), pwd, null))
                    pwd.contents!!.last()
                }
            } else if (line.removePrefix("$ ").startsWith("ls")) {
                running = "ls"
            }
        }
        when (running) {
            "" -> handleNextCommand()

            "ls" -> {
                if (line.startsWith("$")) {
                    handleNextCommand()
                } else {
                    val things = line.split(" ")
                    if (things[0] == "dir") {
                        pwd.contents!!.add(Thing(true, mutableListOf(), pwd, null))
                    } else {
                        pwd.contents!!.add(Thing(false, null, pwd, things[0].toInt()))
                    }
                }
            }
        }
    }
    val sizes = mutableListOf<Int>()
    fun Thing.size(): Int = if (dir) {
        var total = 0
        for (thing in contents!!) total += thing.size()
        total
    } else {
        size!!
    }.also { if (dir) sizes += it }
    root.size()
    andThen(sizes)
}
