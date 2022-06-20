package com.noyize.deepsky.presentation.facts

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.noyize.deepsky.R
import com.noyize.deepsky.databinding.FragmentFactsBinding
import com.noyize.deepsky.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter


@AndroidEntryPoint
class FactsFragment : Fragment(R.layout.fragment_facts), SpaceFactAdapter.ClickListener {

    private lateinit var binding: FragmentFactsBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val spaceFactAdapter by lazy {
        SpaceFactAdapter(this).apply {
            submitList(mainViewModel.spaceFacts)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareTransitions()
        postponeEnterTransition()
        binding = FragmentFactsBinding.bind(view)
        binding.recyclerView.adapter = spaceFactAdapter
        scrollToPosition()
        binding.appBar.applyInsetter {
            type(statusBars = true) {
                padding()
            }
        }
    }


    /**
     * Scrolls the recycler view to show the last viewed item in the grid. This is important when
     * navigating back from the grid.
     */
    private fun scrollToPosition() {
        binding.recyclerView.apply {
            addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                override fun onLayoutChange(
                    v: View,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    oldLeft: Int,
                    oldTop: Int,
                    oldRight: Int,
                    oldBottom: Int
                ) {
                    removeOnLayoutChangeListener(this)
                    val viewAtPosition = layoutManager?.getChildAt(mainViewModel.selectedIndex)
                    // Scroll to position if the view for the current position is null (not currently part of
                    // layout manager children), or it's not completely visible.
                    if (viewAtPosition == null || layoutManager?.isViewPartiallyVisible(
                            viewAtPosition,
                            false,
                            true
                        ) == true
                    ) {
                        post {
                            layoutManager?.scrollToPosition(mainViewModel.selectedIndex)
                        }
                    }

                }
            })
        }

    }

    /**
     * Prepares the shared element transition to the pager fragment, as well as the other transitions
     * that affect the flow.
     */
    private fun prepareTransitions() {
        exitTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.grid_exit_transition)

        // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String?>,
                    sharedElements: MutableMap<String?, View?>
                ) {
                    // Locate the ViewHolder for the clicked position.
                    val selectedViewHolder: RecyclerView.ViewHolder = binding.recyclerView
                        .findViewHolderForAdapterPosition(mainViewModel.selectedIndex) ?: return

                    // Map the first shared element name to the child ImageView.
                    sharedElements[names[0]] =
                        selectedViewHolder.itemView.findViewById(R.id.thumbnail)
                }
            })
    }

    override fun onClick(position: Int, view: View) {
        mainViewModel.selectedIndex = position
        val extras =
            FragmentNavigatorExtras((view as ImageView) to view.transitionName)
        findNavController().navigate(
            FactsFragmentDirections.actionFactsFragmentToDetailFragment(),
            extras
        )
    }

    override fun onBindComplete(position: Int) {
        if (mainViewModel.selectedIndex == position)
            startPostponedEnterTransition()
    }
}