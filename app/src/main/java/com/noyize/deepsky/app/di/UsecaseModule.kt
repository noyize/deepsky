package com.noyize.deepsky.app.di

import com.noyize.deepsky.domain.repository.SpaceFactRepository
import com.noyize.deepsky.domain.usecase.GetSortedSpaceFacts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UsecaseModule {

    @Provides
    fun provideGetSortedFactsUseCase(spaceFactRepository: SpaceFactRepository) = GetSortedSpaceFacts(spaceFactRepository)

}