package com.noyize.deepsky.data.repository

import android.app.Application
import com.noyize.deepsky.app.util.parseListFromAssets
import com.noyize.deepsky.data.dto.SpaceFactDto
import com.noyize.deepsky.data.mapper.toSpaceFact
import com.noyize.deepsky.domain.model.SpaceFact
import com.noyize.deepsky.domain.repository.SpaceFactRepository
import javax.inject.Inject

class SpaceFactRepositoryImpl @Inject constructor(private val application: Application): SpaceFactRepository {

    override fun getSpaceFacts(): List<SpaceFact> {
        val spaceFactDtoList = parseListFromAssets<SpaceFactDto>(
            context = application,
            filename = JSON_FILE_NAME
        )
        return  spaceFactDtoList.map { it.toSpaceFact() }
    }

    companion object {
        const val JSON_FILE_NAME = "data.json"
    }
}