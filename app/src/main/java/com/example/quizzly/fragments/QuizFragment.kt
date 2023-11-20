package com.example.quizzly.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizzly.R
import com.example.quizzly.databinding.FragmentQuizBinding
import com.example.quizzly.viewmodels.QuizViewModel
import com.example.quizzly.models.QuizQuestion

class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        if (viewModel.hasMoreQuestions()) {
            val question = viewModel.getCurrentQuestion()
            updateView(question)
        }

        // Set button_submit_answer listener after view creation
        binding.buttonSubmitAnswer.setOnClickListener {
            // get the selected answer
            val selectedAnswer = when (binding.optionsRadioGroup.checkedRadioButtonId) {
                binding.buttonAnswer1.id -> 0
                binding.buttonAnswer2.id -> 1
                binding.buttonAnswer3.id -> 2
                binding.buttonAnswer4.id -> 3
                else -> -1
            }
            // check if the answer is correct
            viewModel.checkAnswer(selectedAnswer)
            if (viewModel.hasMoreQuestions()) {
                // go to the next question
                // reset the radio button
                binding.optionsRadioGroup.clearCheck()
                val question = viewModel.getCurrentQuestion()
                updateView(question)
            } else {
                // navigate to the result fragment passing the score
                val score = viewModel.getScore()
                val fullName = arguments?.getString("fullName") ?: ""
                findNavController().navigate(
                    R.id.action_QuizFragment_to_ResultFragment,
                    Bundle().apply {
                        putInt("score", score)
                        putString("fullName", fullName)
                    }
                    // how to get the score in the ResultFragment
                    // val score =
                )


            }
        }
    }

    private fun updateView(question: QuizQuestion) {
        binding.textviewQuestionNumber.text = "Question ${viewModel.getCurrentQuestionIndex()}"
        binding.textviewQuestionText.text = question.question
        binding.buttonAnswer1.text = question.answers[0]
        binding.buttonAnswer2.text = question.answers[1]
        binding.buttonAnswer3.text = question.answers[2]
        binding.buttonAnswer4.text = question.answers[3]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
