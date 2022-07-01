package com.noyize.deepsky.data.repository

import com.noyize.deepsky.data.dto.SpaceFactDto
import com.noyize.deepsky.data.mapper.toSpaceFact
import com.noyize.deepsky.domain.model.SpaceFact
import com.noyize.deepsky.domain.repository.SpaceFactRepository
import com.noyize.deepsky.util.getSpaceFactsJson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FakeSpaceFactRepository : SpaceFactRepository {

    override fun getSpaceFacts(): List<SpaceFact> {
        val jsonData = getSpaceFactsJson()
        val json = Json {
            ignoreUnknownKeys = true
        }

        return json.decodeFromString<List<SpaceFactDto>>(jsonData).map { it.toSpaceFact() }
    }
}