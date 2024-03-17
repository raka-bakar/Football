package com.raka.football.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

fun getAnnotatedStrings(sentence: String, markedWord: String): AnnotatedString {
    return buildAnnotatedString {
        val startIndex = sentence.indexOf(markedWord)
        val endIndex = startIndex + markedWord.length
        append(sentence)

        addStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold),
            start = startIndex,
            end = endIndex
        )
    }
}