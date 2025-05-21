package com.example.data.di

import com.example.data.repository.FavoriteRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    fun bindsFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}