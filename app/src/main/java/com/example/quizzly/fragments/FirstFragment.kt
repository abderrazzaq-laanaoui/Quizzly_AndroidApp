package com.example.quizzly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizzly.databinding.FragmentFirstBinding
import android.widget.ArrayAdapter
import com.example.quizzly.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        // populate the list of top 5 scores, array of {firstNames + lastNames, score}
        val top5 = arrayOf(
            "John Doe 100",
            "Jane Doe 90",
            "John Smith 80",
            "Jane Smith 70",
            "John Doe 60"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, top5)
        binding.listviewTopScores.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}