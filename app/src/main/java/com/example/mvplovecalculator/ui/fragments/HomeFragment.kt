package com.example.mvplovecalculator.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvplovecalculator.data.repository.LoveCalculatorRepository
import com.example.mvplovecalculator.databinding.FragmentHomeBinding
import com.example.mvplovecalculator.ui.viewModel.HomeViewModel
import com.example.mvplovecalculator.ui.viewModel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val factory = HomeViewModelFactory(LoveCalculatorRepository())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() = with(binding) {
        btnCalculate.setOnClickListener {
            viewModel.calculateLovePercentage(
                etFirstName.text.toString(),
                etSecondName.text.toString()
            )
        }
    }

    private fun setUpObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.result.observe(viewLifecycleOwner) { result ->
            val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(result)
            findNavController().navigate(action)
            binding.etFirstName.text.clear()
            binding.etSecondName.text.clear()
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}