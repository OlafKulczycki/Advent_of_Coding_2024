package com.tasks
import java.io.File

class Task2(private val filePath: String = "src/resources/input_task2") {

    // variables
    private val result = readInputTask2()
    private val checkedResult = checkDifferences(result)
    private val countedSaves = countSaves(checkedResult)
    private val dampenedResult = problemDampener(result, checkedResult)

    // functions
    private fun readInputTask2(): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        File(filePath).forEachLine { line ->
            val numbers = line.split("\\s+".toRegex()).map { it.toInt() }
            result.add(numbers)
        }
        return result
    }

    private fun checkDifferences(result: List<List<Int>>): List<String> {
        val checkedResult = mutableListOf<String>()

        for (line in result) {
            var isSafe = true
            val isIncreasing = line[1] > line[0]

            for (i in 0..<line.size - 1) {
                val difference = kotlin.math.abs(line[i] - line[i + 1])
                if (difference !in 1..3 || (line[i + 1] > line[i]) != isIncreasing) {
                    isSafe = false
                    break
                }
            }

            checkedResult.add(if (isSafe) "safe" else "unsafe")
        }
        return checkedResult
    }

    private fun countSaves(checkedSaves: List<String>): Int {
        return checkedSaves.count { it == "safe" }
    }

    private fun problemDampener(result: List<List<Int>>, checkedResult: List<String>): List<String> {
        val newCheckedResult = checkedResult.toMutableList()

        for (lineIndex in result.indices) {
            if (checkedResult[lineIndex] == "unsafe") {
                val line = result[lineIndex]
                for (i in line.indices) {
                    val modifiedLine = line.toMutableList().apply { removeAt(i) }
                    if (isLineSafe(modifiedLine)) {
                        newCheckedResult[lineIndex] = "safe"
                        break
                    }
                }
            }
        }

        return newCheckedResult
    }

    private fun isLineSafe(line: List<Int>): Boolean {
        if (line.size < 2) return false
        val isIncreasing = line[1] > line[0]

        for (i in 0..<line.size - 1) {
            val difference = kotlin.math.abs(line[i] - line[i + 1])
            if (difference !in 1..3 || (line[i + 1] > line[i]) != isIncreasing) {
                return false
            }
        }

        return true
    }

    // on init
    init {
        println("The input of task 2 is: $filePath")
        println("input read correctly, starting to count the safe routes...")
        println("The amount of safe routes are: $countedSaves")
        println("Using the Problem Dampener to check the unsafe reports")
        println("New amount of safe reports are ${countSaves(dampenedResult)}")
    }
}

fun main() {
    Task2()
}