package com.example.mvplovecalculator.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.data.repository.LoveCalculatorRepository
import com.example.mvplovecalculator.databinding.FragmentHomeBinding
import com.example.mvplovecalculator.presenter.HomePresenter
import com.example.mvplovecalculator.ui.view.HomeView

class HomeFragment : Fragment(), HomeView {

    private lateinit var presenter: HomePresenter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = LoveCalculatorRepository()
        presenter = HomePresenter(this, repository)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() = with(binding) {
        btnCalculate.setOnClickListener {
            presenter.onCalculateClicked(etFirstName.text.toString(), etSecondName.text.toString())
        }
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showResult(result: CalculatedResult) {
        val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(result)
        findNavController().navigate(action)
        binding.etFirstName.text.clear()
        binding.etSecondName.text.clear()
    }

    override fun showValidationErrors() {
        binding.etFirstName.error = "Введите данные"
        binding.etSecondName.error = "Введите данные"
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}