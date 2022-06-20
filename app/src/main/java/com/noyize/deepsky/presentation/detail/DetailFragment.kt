package com.noyize.deepsky.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.core.view.doOnPreDraw
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.noyize.deepsky.R
import com.noyize.deepsky.app.util.onPageChanged
import com.noyize.deepsky.databinding.FragmentDetailBinding
import com.noyize.deepsky.domain.model.SpaceFact
import com.noyize.deepsky.presentation.facts.SpaceFactAdapter
import com.noyize.deepsky.presentation.main.MainViewModel
import com.noyize.deepsky.presentation.widget.ZoomOutPageTransformer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail),SpaceFactDetailAdapter.ClickListener {

    private lateinit var binding : FragmentDetailBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val spaceFactDetailAdapter by lazy { SpaceFactDetailAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSharedElementTransition()
        postponeEnterTransition()
        binding = FragmentDetailBinding.bind(view)
        spaceFactDetailAdapter.submitList(mainViewModel.spaceFacts)
        setUpViewPager()
        (view.parent as? ViewGroup)?.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun setUpViewPager() {
        binding.viewPager.adapter = spaceFactDetailAdapter
        binding.viewPager.setCurrentItem(mainViewModel.selectedIndex, false)
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.viewPager.onPageChanged { position -> mainViewModel.selectedIndex = position }
    }

    /**
     * Prepares the shared element transition from and back to the grid fragment.
     */
    private fun prepareSharedElementTransition() {
        val transition = TransitionInflater.from(
            requireContext()
        )
            .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition

        // A similar mapping is set at the GridFragment with a setExitSharedElementCallback.
        setEnterSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String>,
                    sharedElements: MutableMap<String, View>
                ) {
                    // Locate the image view at the primary fragment (the ImageFragment that is currently
                    // visible). To locate the fragment, call instantiateItem with the selection position.
                    // At this stage, the method will simply return the fragment at the position and will
                    // not create a new one.
                    //val currentItemView = binding.viewPager.focusedChild
                    //.instantiateItem(binding.viewPager, MainActivity.currentPosition) as Fragment
                    // val view = binding.viewPager.focusedChild ?: return

                    val selectedViewHolder =
                        (binding.viewPager[0] as RecyclerView).findViewHolderForAdapterPosition(
                            mainViewModel.selectedIndex
                        ) ?: return

                    // Map the first shared element name to the child ImageView.
                    sharedElements[names[0]] =
                        selectedViewHolder.itemView.findViewById(R.id.spaceFactDetailImage)
                }
            })
    }

    override fun onMoreDetailClick(spaceFact: SpaceFact) {
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToDetailBottomSheet(spaceFact))
    }
}