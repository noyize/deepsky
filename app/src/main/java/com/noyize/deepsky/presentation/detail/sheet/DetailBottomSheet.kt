package com.noyize.deepsky.presentation.detail.sheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.noyize.deepsky.databinding.DialogDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: DialogDetailBinding
    private val viewModel by viewModels<DialogBottomSheetViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomDialog = dialog as BottomSheetDialog
            val bottomSheet =
                bottomDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            BottomSheetBehavior.from(bottomSheet).state =
                BottomSheetBehavior.STATE_EXPANDED
            BottomSheetBehavior.from(bottomSheet).skipCollapsed = true
            BottomSheetBehavior.from(bottomSheet).isHideable = true
        }
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
        binding.closeButton.setOnClickListener { findNavController().navigateUp() }
    }

    private fun observeUI() {
        viewModel.spaceFact.observe(viewLifecycleOwner) { spaceFact ->
            spaceFact?.let {
                with(binding) {
                    title.text = it.title
                    date.text = it.date
                    explanation.text = it.explanation
                }
            }
        }
    }

}