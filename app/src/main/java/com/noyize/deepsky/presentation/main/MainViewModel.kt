package com.noyize.deepsky.presentation.main

import androidx.lifecycle.ViewModel
import com.noyize.deepsky.domain.usecase.GetSortedSpaceFacts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getSortedSpaceFacts: GetSortedSpaceFacts) : ViewModel() {

    val spaceFacts = getSortedSpaceFacts()

}