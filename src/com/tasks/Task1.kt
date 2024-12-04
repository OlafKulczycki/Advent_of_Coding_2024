package com.tasks

class Task1 {
    //variables
    private val list1: MutableList<Int> = mutableListOf()
    private val list2: MutableList<Int> = mutableListOf()
    var distanceList = mutableListOf<Int>()
    //on init
    init {
        readInput()
        distanceList = distanceCalculator(list1, list2)
    }
    //functions
    private fun readInput() {
        println("Enter the input (each pair of numbers on a new line, separated by spaces):")
        while (true) {
            val line = readlnOrNull() ?: break
            if (line.isBlank()) break
            val (left, right) = line.split("\\s+".toRegex()).map { it.toInt() }
            list1.add(left)
            list2.add(right)
        }
    }
    private fun listSorter(list: List<Int>): MutableList<Int> {
        return list.sorted().toMutableList()
    }
    private fun distanceCalculator(list1: List<Int>, list2: List<Int>): MutableList<Int> {
        val distanceList = mutableListOf<Int>()
        val list1AsList = listSorter(list1.toList())
        val list2AsList = listSorter(list2.toList())
        for (i in 0 until list1.size) {
            val distance = list1AsList[i] - list2AsList[i]
            distanceList.add(if (distance < 0) -distance else distance)
        }
        val disList = invertNegatives(distanceList)
        return disList
    }
    private fun invertNegatives(list: MutableList<Int>): MutableList<Int> {
        return list.map { if (it < 0) -it else it }.toMutableList()
    }
    fun sumOfDistances(list: List<Int>): Int {
        return list.sum()
    }
}
fun main() {
    val task = Task1()
    println("The total distance between the lists are ${task.sumOfDistances(task.distanceList)}")
}