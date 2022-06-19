package com.noyize.deepsky.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.noyize.deepsky.R
import com.noyize.deepsky.databinding.FragmentDetailBinding
import com.noyize.deepsky.presentation.facts.SpaceFactAdapter
import com.noyize.deepsky.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding : FragmentDetailBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val spaceFactDetailAdapter by lazy { SpaceFactDetailAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        spaceFactDetailAdapter.submitList(mainViewModel.spaceFacts)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.viewPager.adapter = spaceFactDetailAdapter
        binding.viewPager.setCurrentItem(mainViewModel.selectedIndex, false)
    }
}