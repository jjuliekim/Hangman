package me.julie.hangman

import java.io.File

const val MIN_LETTERS = 5
const val MAX_LETTERS = 8

fun main() {
    val words = File("C:/Users/julie/IdeaProjects/Hangman/src/main/resources/words.txt").readText().split('\n')
    val new = words.filter { it.length in (MIN_LETTERS + 1)..MAX_LETTERS }.joinToString("\n")
    File("C:/Users/julie/IdeaProjects/Hangman/src/main/resources/random.txt").writeText(new)
}