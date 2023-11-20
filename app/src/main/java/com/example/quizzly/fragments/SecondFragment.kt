package com.example.quizzly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.quizzly.R
import com.example.quizzly.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonStart.setOnClickListener {
            startQuizFragment()
        }
    }

    private fun startQuizFragment() {
        val fullName = binding.edittextFirstName.text.toString() + " " + binding.edittextLastName.text.toString()
        findNavController().navigate(R.id.action_SecondFragment_to_QuizFragment,
            Bundle().apply {
                putString("fullName", fullName)
            })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}