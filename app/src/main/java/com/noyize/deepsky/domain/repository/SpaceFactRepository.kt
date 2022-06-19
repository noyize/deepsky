package com.noyize.deepsky.domain.repository

import com.noyize.deepsky.domain.model.SpaceFact

interface SpaceFactRepository {
    fun getSpaceFacts(): List<SpaceFact>
}