package com.tasks
import java.io.File

private class Task4 {

    //variables
    val rawInput = readInputTask4("src/resources/input_task4")
    val horizontalCount = countHorizontal(rawInput)
    val diagonalCount = countDiagonal(rawInput)
    val verticalCount = countVertical(rawInput)
    val maxCounts = horizontalCount + diagonalCount + verticalCount

    //functions
    fun readInputTask4(filePath: String): List<String> {
        return File(filePath).readLines()
    }

    fun countHorizontal(input: List<String>): Int {
        val targetWords = listOf("XMAS", "SAMX")
        var totalCount = 0
        input.forEach { line ->
            totalCount += targetWords.sumOf { word ->
                line.windowed(word.length, 1).count { it == word }
            }
        }
        return totalCount
    }

    fun countDiagonal(input: List<String>): Int {
        val targetWords = listOf("XMAS", "SAMX")
        val n = input.size
        var totalCount = 0

        // Check diagonals from top-left to bottom-right
        for (k in 0 until 2 * n - 1) {
            val sb = StringBuilder()
            for (j in 0..k) {
                val i = k - j
                if (i < n && j < n) {
                    sb.append(input[i][j])
                }
            }
            val diagonal = sb.toString()
            totalCount += targetWords.sumOf { word -> diagonal.windowed(word.length, 1).count { it == word } }
        }

        // Check diagonals from top-right to bottom-left
        for (k in 0 until 2 * n - 1) {
            val sb = StringBuilder()
            for (j in 0..k) {
                val i = k - j
                if (i < n && j < n) {
                    sb.append(input[i][n - j - 1])
                }
            }
            val diagonal = sb.toString()
            totalCount += targetWords.sumOf { word -> diagonal.windowed(word.length, 1).count { it == word } }
        }

        return totalCount
    }

    fun countVertical(input: List<String>): Int {
        val targetWords = listOf("XMAS", "SAMX")
        val n = input.size
        val m = input[0].length
        var totalCount = 0

        for (col in 0 until m) {
            val sb = StringBuilder()
            for (row in 0 until n) {
                sb.append(input[row][col])
            }
            val vertical = sb.toString()
            totalCount += targetWords.sumOf { word ->
                vertical.windowed(word.length, 1).count { it == word }
            }
        }

        return totalCount
    }

    //init block
    init {
        println("Number of lines in rawInput: ${rawInput.size}")
    }
}

fun main() {
    val task = Task4()
    println("Total count of horizontal 'xmas' or 'samx': ${task.horizontalCount}")
    println("Total count of vertical 'xmas' or 'samx': ${task.verticalCount}")
    println("Total count of diagonal 'xmas' or 'samx': ${task.diagonalCount}")
    println("Total count of 'xmas' or 'samx' in all directions: ${task.maxCounts}")
}