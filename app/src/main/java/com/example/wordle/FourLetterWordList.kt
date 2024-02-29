package com.example.wordle

import kotlin.random.Random

class FourLetterWordList {
    val wordsList = arrayOf("Cats", "Puss", "Meow")
    fun getRandomFourLetterWord(): String{
        var chosenId = Random.nextInt(0,wordsList.size)
        return wordsList[chosenId].uppercase()
    }
}