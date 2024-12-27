package com.example.mvplovecalculator.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mvplovecalculator.R
import com.example.mvplovecalculator.databinding.FragmentHomeBinding
import com.example.mvplovecalculator.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(FragmentHomeBinding::inflate,HomeViewModel::class.java) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() = with(binding) {
        btnCalculate.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val secondName = etSecondName.text.toString()

            if (firstName.isNotEmpty() && secondName.isNotEmpty()) {
                viewModel.calculateLovePercentage(firstName, secondName)
            } else {
                Toast.makeText(requireContext(), "Введите оба имени", Toast.LENGTH_SHORT).show()
            }
        }

        btnHistory.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
    }

    private fun setUpObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.etFirstName.isEnabled = !isLoading
            binding.etSecondName.isEnabled = !isLoading
            binding.btnCalculate.isEnabled = !isLoading
        }

        viewModel.result.observe(viewLifecycleOwner) { _result ->
            _result?.let { result ->
                val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(result)
                findNavController().navigate(action)
                binding.etFirstName.text.clear()
                binding.etSecondName.text.clear()

                viewModel.clearResult()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

}