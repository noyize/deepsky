package com.noyize.deepsky.app.di

import com.noyize.deepsky.data.repository.SpaceFactRepositoryImpl
import com.noyize.deepsky.domain.repository.SpaceFactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSpaceFactRepository(spaceFactRepositoryImpl: SpaceFactRepositoryImpl): SpaceFactRepository
}