package com.tasks
import java.io.File

private class Task4 {

    //variables
    val rawInput = readInputTask4("src/resources/input_task4")
    val horizontalCount = countHorizontal(rawInput)
    val diagonalCount = countDiagonal(rawInput)
    val verticalCount = countVertical(rawInput)
    val masCount = countXMasPattern(rawInput)
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

    fun countXMasPattern(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        var count = 0

        for (i in 0 until n - 2) {
            for (j in 0 until m - 2) {
                if ((input[i][j] == 'S' && input[i][j + 2] == 'S' && input[i + 1][j + 1] == 'A' &&
                            input[i + 2][j] == 'M' && input[i + 2][j + 2] == 'M')) {
                    count++
                } else if ((input[i][j] == 'M' && input[i][j + 2] == 'M' && input[i + 1][j + 1] == 'A' &&
                            input[i + 2][j] == 'S' && input[i + 2][j + 2] == 'S')) {
                    count++
                } else if ((input[i][j] == 'S' && input[i][j + 2] == 'M' && input[i + 1][j + 1] == 'A' &&
                            input[i + 2][j] == 'S' && input[i + 2][j + 2] == 'M')) {
                    count++
                } else if ((input[i][j] == 'M' && input[i][j + 2] == 'S' && input[i + 1][j + 1] == 'A' &&
                            input[i + 2][j] == 'M' && input[i + 2][j + 2] == 'S')) {
                    count++
                }
            }
        }

        return count
    }
}

fun main() {
    val task = Task4()
    println("Reading the input file src/resources/input_task4")
    println("Reading successful, counting the occurrences of 'XMAS' and 'SAMX' in all directions & X-Mas pattern")
    println("Total count of 'xmas' or 'samx' in all directions: ${task.maxCounts}")
    println("Total count of X-Mas patterns: ${task.masCount}")
}