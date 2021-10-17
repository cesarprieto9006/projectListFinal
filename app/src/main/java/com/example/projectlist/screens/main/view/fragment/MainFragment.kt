package com.example.projectlist.screens.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.projectlist.R
import com.example.projectlist.databinding.FragmentMainBinding
import com.example.projectlist.screens.main.adapter.MainAdapter
import com.example.projectlist.screens.main.viewmodel.MainViewModel
import com.example.projectlist.utils.Utils


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        observeLiveData()

    }

    private fun setupListener() {
        binding.rvWords.run { adapter = mainAdapter }
        mainAdapter.contextViewAdapter(requireContext())

        binding.tvSearch.doAfterTextChanged { text ->
            if (Utils().isConnected(requireContext()))
                viewModel.search(text.toString())
            else
                Toast.makeText(
                    requireContext(),
                    getString(R.string.network_validate),
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun observeLiveData() {
        viewModel.arrayMeaningResponse.observe(
            viewLifecycleOwner,
            Observer { result ->
                when {
                    result.isNotEmpty() && binding.tvSearch.text.toString()!="" -> {
                        mainAdapter.addAll(result[0].lfs)
                        viewModel.stateError.value = false
                    }
                    else -> viewModel.stateError.value = true
                }
            })

        viewModel.errorResponse.observe(viewLifecycleOwner, Observer { message ->
            if (message != null && message != "")
                Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
        })
    }

}