package com.tasks
import java.io.File

private class Task3 {

    //variables
    val rawInput = readFileToList("src/resources/input_task3")
    val sumMulCalls = extractMulCalls(rawInput).sum()

    //functions
    fun extractMulCalls(inputs: List<String>): List<Int> {
        val regex = """mul\((\d+),\s*(\d+)\)""".toRegex()
        return inputs.flatMap { input ->
            regex.findAll(input).map { matchResult ->
                val (a, b) = matchResult.destructured
                a.toInt() * b.toInt()
            }.toList()
        }
    }

    fun readFileToList(filePath: String): List<String> {
        return File(filePath).readLines()
    }

    //init block
    init {
        println("Reading the input file src/resources/input_task3")
        println("File read successfully, the result is: $sumMulCalls")
    }
}

fun main() {
    Task3()
}