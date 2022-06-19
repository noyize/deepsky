package com.noyize.deepsky.domain.usecase

import com.noyize.deepsky.domain.model.SpaceFact
import com.noyize.deepsky.domain.repository.SpaceFactRepository
import javax.inject.Inject

class GetSortedSpaceFacts @Inject constructor(
    private val spaceFactRepository: SpaceFactRepository
) {
    operator fun invoke(): List<SpaceFact> {
        val spaceFacts = spaceFactRepository.getSpaceFacts()
        return spaceFacts.sortedByDescending { it.date }
    }
}