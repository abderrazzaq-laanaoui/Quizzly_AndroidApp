package com.example.quizzly.models

data class QuizQuestion (
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
