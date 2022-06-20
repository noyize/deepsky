package com.noyize.deepsky.data.mapper

import com.noyize.deepsky.app.util.formatDate
import com.noyize.deepsky.data.dto.SpaceFactDto
import com.noyize.deepsky.domain.model.SpaceFact

fun SpaceFactDto.toSpaceFact(): SpaceFact {
    return SpaceFact(
        title = this.title ?: "",
        date = formatDate(this.date) ?: "",
        explanation = this.explanation ?: "",
        imageUrl = this.url ?: "",
        hdImageUrl = this.hdUrl ?: ""
    )
}