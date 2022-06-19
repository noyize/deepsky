package com.noyize.deepsky.presentation.facts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.noyize.deepsky.R
import com.noyize.deepsky.databinding.FragmentFactsBinding
import com.noyize.deepsky.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactsFragment : Fragment(R.layout.fragment_facts),SpaceFactAdapter.ClickListener {

    private lateinit var binding : FragmentFactsBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val spaceFactAdapter by lazy { SpaceFactAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFactsBinding.bind(view)
        binding.recyclerView.adapter = spaceFactAdapter
        spaceFactAdapter.submitList(mainViewModel.spaceFacts)
    }

    override fun onClick(position: Int) {
        findNavController().navigate(FactsFragmentDirections.actionFactsFragmentToDetailFragment())
    }
}