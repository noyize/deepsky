package com.noyize.deepsky.domain.usecase

import com.noyize.deepsky.domain.model.SpaceFact
import com.noyize.deepsky.domain.repository.SpaceFactRepository

class GetSortedSpaceFacts(private val spaceFactRepository: SpaceFactRepository) {
    operator fun invoke(): List<SpaceFact> {
        val spaceFacts = spaceFactRepository.getSpaceFacts()
        return spaceFacts.sortedByDescending { it.date }
    }
}