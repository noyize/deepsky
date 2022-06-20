package com.noyize.deepsky.presentation.detail.sheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.noyize.deepsky.domain.model.SpaceFact

class DialogBottomSheetViewModel constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val spaceFact = savedStateHandle.getLiveData<SpaceFact>(SPACE_FACT_KEY)

    companion object{
        const val SPACE_FACT_KEY = "space_fact"
    }
}