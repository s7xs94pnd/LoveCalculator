package com.example.mvplovecalculator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

typealias Inflate<T> =(LayoutInflater,ViewGroup?,Boolean)-> T

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(
    private val inflate: Inflate<VB>?,
    private val viewModelClass: Class<VM>?
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected val viewModel: VM by lazy { ViewModelProvider(this)[viewModelClass!!] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate?.invoke(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupClickListeners()
        initializeRV()
    }

    protected open fun initializeRV() {

    }

    protected  open fun setupClickListeners() {
    }

    protected open fun setupObservers() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}