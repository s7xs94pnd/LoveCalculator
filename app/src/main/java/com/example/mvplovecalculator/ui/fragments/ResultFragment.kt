package com.example.mvplovecalculator.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.databinding.FragmentHomeBinding
import com.example.mvplovecalculator.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var calculatedResult: CalculatedResult
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calculatedResult = ResultFragmentArgs.fromBundle(requireArguments()).resultModel
        displayResult()
    }

    private fun displayResult() {
        binding.tvFirstName.text = calculatedResult.firstName
        binding.tvSecondName.text = calculatedResult.secondName
        binding.tvPercent.text = "Compatibility: ${calculatedResult.percentage}%"
        binding.tvResult.text = calculatedResult.result

        binding.btnTryAgain.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}