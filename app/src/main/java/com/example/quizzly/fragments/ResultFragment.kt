package com.example.quizzly.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizzly.R
import com.example.quizzly.databinding.FragmentQuizBinding
import com.example.quizzly.databinding.FragmentResultBinding
import com.example.quizzly.viewmodels.QuizViewModel


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ResultFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // score from the QuizViewModel
        val score = arguments?.getInt("score") ?: 0

//       binding.scoreTextview.text = score.toString()
        Log.d("ResultFragment", "score: $score")
        // display the score in the textview
        binding.scoreTextview.text = score.toString()
        binding.textviewFullName.text = buildString {
        append("Hello ")
        append(arguments?.getString("fullName") ?: "")
        append(" !")
    }

        // set the button listener
        binding.finishButton.setOnClickListener {
            // reset the score
            findNavController().navigate(R.id.action_ResultFragment_to_FirstFragment)
        }
    }

}