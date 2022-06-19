package com.noyize.deepsky.presentation.facts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noyize.deepsky.R
import com.noyize.deepsky.databinding.FragmentFactsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactsFragment : Fragment(R.layout.fragment_facts) {

    private lateinit var binding : FragmentFactsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFactsBinding.bind(view)
    }
}