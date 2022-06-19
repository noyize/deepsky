package com.noyize.deepsky.presentation.main

import androidx.lifecycle.ViewModel
import com.noyize.deepsky.domain.usecase.GetSortedSpaceFacts
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainViewModel @Inject constructor(getSortedSpaceFacts: GetSortedSpaceFacts) : ViewModel() {

    val spaceFacts = getSortedSpaceFacts()

}