package com.tasks
import java.io.File

private class Task3 {

    //variables
    val rawInput = readFileToList("src/resources/input_task3")
    val refinedInput = filterInstructions(rawInput.joinToString(" "))
    val sumMulCalls = extractMulCalls("src/resources/refined_input_task3").sum()

    //functions
    fun extractMulCalls(filePath: String): List<Int> {
        val regex = """mul\((\d+),\s*(\d+)\)""".toRegex()
        val result = mutableListOf<Int>()
        var addCalls = true // mul instructions are enabled at the beginning

        val inputs = File(filePath).readLines()

        for (input in inputs) {
            if (input.contains("do()")) {
                addCalls = true
            } else if (input.contains("don't()")) {
                addCalls = false
            }

            if (addCalls) {
                regex.findAll(input).forEach { matchResult ->
                    val (a, b) = matchResult.destructured
                    result.add(a.toInt() * b.toInt())
                }
            }
        }
        return result
    }

    fun readFileToList(filePath: String): List<String> {
        return File(filePath).readLines()
    }

    fun filterInstructions(input: String): List<String> {
        val regex = """(do\(\)|don't\(\)|mul\(\d+,\d+\))""".toRegex()
        return regex.findAll(input).map { it.value }.toList()
    }

    //init block
    init {
        println("Reading the input file src/resources/input_task3")
        println("File read successfully and filtered. Saving the refined input to src/resources/refined_input_task3")
        println("The result is: $sumMulCalls")
        saveRefinedInputToFile("src/resources/refined_input_task3", refinedInput)
    }

    fun saveRefinedInputToFile(filePath: String, content: List<String>): List<String> {
        File(filePath).writeText(content.joinToString("\n"))
        return content
    }
}

fun main() {
    Task3()
}