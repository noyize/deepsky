package com.noyize.deepsky.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpaceFact(
    val date: String,
    val explanation: String,
    val hdImageUrl: String,
    val title: String,
    val imageUrl: String
) : Parcelable
