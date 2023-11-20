package com.example.quizzly.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quizzly.models.QuizQuestion

class QuizViewModel : ViewModel() {
    // Liste des questions du quiz
    private var quizQuestions: List<QuizQuestion> = getQuestions()

    // Index de la question actuelle
    private var currentQuestionIndex = 0
    private var score = 0
    private fun getQuestions(): List<QuizQuestion> {
        return listOf(
            QuizQuestion(
                "Quel est le nom de la capitale de la France ?",
                listOf("Lyon", "Paris", "Marseille", "Bordeaux"),
                1
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de l'Espagne ?",
                listOf("Barcelone", "Madrid", "Séville", "Valence"),
                1
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de l'Italie ?",
                listOf("Milan", "Rome", "Turin", "Venise"),
                1
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de l'Allemagne ?",
                listOf("Berlin", "Munich", "Hambourg", "Francfort"),
                0
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de la Belgique ?",
                listOf("Bruxelles", "Anvers", "Gand", "Charleroi"),
                0
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de la Suisse ?",
                listOf("Genève", "Zurich", "Bâle", "Berne"),
                3
            ),
            QuizQuestion(
                "Quel est le nom de la capitale du Portugal ?",
                listOf("Porto", "Lisbonne", "Coimbra", "Faro"),
                1
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de l'Angleterre ?",
                listOf("Manchester", "Liverpool", "Londres", "Birmingham"),
                2
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de l'Irlande ?",
                listOf("Cork", "Galway", "Limerick", "Dublin"),
                3
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de la Suède ?",
                listOf("Malmö", "Stockholm", "Uppsala", "Göteborg"),
                1
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de la Norvège ?",
                listOf("Bergen", "Oslo", "Stavanger", "Trondheim"),
                1         ),
            QuizQuestion(
                "Quel est le nom de la capitale de la Finlande ?",
                listOf("Helsinki", "Tampere", "Turku", "Oulu"),
                0
            ),
            QuizQuestion(
                "Quel est le nom de la capitale de la Russie ?",
                listOf("Saint-Pétersbourg", "Moscou", "Novossibirsk", "Ekaterinbourg"),
                1
            )
        )
    }

    fun getCurrentQuestionIndex(): Int {
        return currentQuestionIndex
    }

    // Fonction pour obtenir la question actuelle
    fun getCurrentQuestion(): QuizQuestion {
        return quizQuestions[currentQuestionIndex++]
    }

    // Fonction pour vérifier si le quiz a plus de questions
    fun hasMoreQuestions(): Boolean {
        return currentQuestionIndex < quizQuestions.size
    }

    fun checkAnswer(selectedAnswer: Int): Boolean {
        val isCorrect = selectedAnswer == quizQuestions[currentQuestionIndex-1].correctAnswerIndex
        if(isCorrect) incrementScore()
        if (isCorrect) Log.d("QuizViewModel", "Correct answer! | Score: $score | expected: ${quizQuestions[currentQuestionIndex-1].correctAnswerIndex} | selected: $selectedAnswer")
        else Log.d("QuizViewModel", "Wrong answer!  | Score: $score | expected: ${quizQuestions[currentQuestionIndex-1].correctAnswerIndex} | selected: $selectedAnswer")
        return isCorrect
    }

    fun incrementScore() {
        score += 1
    }

    fun getScore(): Int {
        return score
    }

    fun resetScore() {
        score = 0
        currentQuestionIndex = 0
    }
}
