package com.example.androidtestapplication.util

class TextFormator {

    fun firstLetterCapitalAndRestAreSmall(text: String): String {
        val firstLetter = text.substring(0, 1).trim()
        val lastLetters = text.substring(1).trim()
        return "${firstLetter.uppercase()}${lastLetters.lowercase()}"
    }
}